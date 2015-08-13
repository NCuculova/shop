package mk.ukim.finki.emk.shop.service.impl;

import mk.ukim.finki.emk.shop.model.Product;
import mk.ukim.finki.emk.shop.repository.CategoryRepository;
import mk.ukim.finki.emk.shop.repository.ProductRepository;
import mk.ukim.finki.emk.shop.service.ProductService;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Nadica-PC on 6/4/2015.
 */
@Service
public class ProductServiceImpl extends BaseEntityCrudServiceImpl<Product, ProductRepository> implements ProductService {

    @Autowired
    private ProductRepository repository;

    // Spring will inject here the entity manager object
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected ProductRepository getRepository() {
        return repository;
    }

    @Transactional
    public List search(String text) {
        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager = Search.
                getFullTextEntityManager(entityManager);

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(Product.class).get();

        // a very basic query by keywords
        org.apache.lucene.search.Query query =
                queryBuilder
                        .keyword()
                        .onFields("name", "description", "category.name")
                        .matching(text)
                        .createQuery();

        // wrap Lucene query in an Hibernate Query object
        org.hibernate.search.jpa.FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(query, Product.class);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List results = jpaQuery.getResultList();

        return results;
    }
}

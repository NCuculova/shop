package mk.ukim.finki.emk.shop.service.impl;

import mk.ukim.finki.emk.shop.model.Product;
import mk.ukim.finki.emk.shop.repository.CategoryRepository;
import mk.ukim.finki.emk.shop.repository.ProductRepository;
import mk.ukim.finki.emk.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nadica-PC on 6/4/2015.
 */
@Service
public class ProductServiceImpl extends BaseEntityCrudServiceImpl<Product,ProductRepository> implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    protected ProductRepository getRepository() {
        return repository;
    }
}

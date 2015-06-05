package mk.ukim.finki.emk.shop.specifications;

import mk.ukim.finki.emk.shop.model.Product;
import mk.ukim.finki.emk.shop.model.ProductImage;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Nadica-PC on 6/5/2015.
 */
public class Specifications {

    public static Specification<ProductImage> product(final Long id) {
        return new Specification<ProductImage>() {
            @Override
            public Predicate toPredicate(Root<ProductImage> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<Product>get("product").<Long>get("id"), id);
            }
        };
    }
}

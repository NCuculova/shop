package mk.ukim.finki.emk.shop.specifications;

import mk.ukim.finki.emk.shop.model.Category;
import mk.ukim.finki.emk.shop.model.Product;
import mk.ukim.finki.emk.shop.model.ProductImage;
import mk.ukim.finki.emk.shop.model.ShoppingCartItem;
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
        return (root, criteriaQuery, cb) -> cb.equal(root.<Product>get("product").<Long>get("id"), id);
    }

    public static Specification<Product> category(final Long id) {
        return (root, criteriaQuery, cb) -> cb.equal(root.<Category>get("category").<Long>get("id"), id);
    }

    public static Specification<ShoppingCartItem> productItem(final Long id, final String token) {
        return (root, criteriaQuery, cb) -> cb.and(cb.equal(root.<Product>get("product").<Long>get("id"), id),
                cb.equal(root.<String>get("token"), token));
    }

    public static Specification<ShoppingCartItem> token(final String guid) {
        return (root, criteriaQuery, cb) -> cb.equal(root.<String>get("token"), guid);
    }
}

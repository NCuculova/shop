package mk.ukim.finki.emk.shop.repository;

import mk.ukim.finki.emk.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Nadica-PC on 6/1/2015.
 */
public interface ProductRepository  extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

}

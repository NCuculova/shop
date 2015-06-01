package mk.ukim.finki.emk.shop.repository;

import mk.ukim.finki.emk.shop.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Nadica-PC on 6/1/2015.
 */
public interface ProductImageRepository  extends JpaRepository<ProductImage, Long>,
        JpaSpecificationExecutor<ProductImage> {

}

package mk.ukim.finki.emk.shop.web;

import mk.ukim.finki.emk.shop.model.Product;
import mk.ukim.finki.emk.shop.service.ProductService;
import mk.ukim.finki.emk.shop.specifications.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Nadica-PC on 6/4/2015.
 */

@RestController
@RequestMapping("/api/product")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Product create(@RequestBody @Valid Product entity) {
        productService.save(entity);
        return entity;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Product> getAll() {
        List<Product> productList = productService
                .findAll();
        return productList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Product getProduct(@PathVariable Long id,
                              HttpServletResponse response) {
        Product product = productService.findOne(id);
        if (product == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return product;
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<Product> getProductsByCategoryId(@PathVariable Long id,
                                           HttpServletResponse response) {
        List<Product> products = productService.findAll(Specifications.category(id));
        return products;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable Long id, HttpServletRequest request,
                       HttpServletResponse response) {
        productService.delete(id);
    }
}

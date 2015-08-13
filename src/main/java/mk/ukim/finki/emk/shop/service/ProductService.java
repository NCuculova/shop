package mk.ukim.finki.emk.shop.service;

import mk.ukim.finki.emk.shop.model.Product;

import java.util.List;

/**
 * Created by Nadica-PC on 6/4/2015.
 */
public interface ProductService extends BaseEntityCrudService<Product>{

    public List search(String text);

}

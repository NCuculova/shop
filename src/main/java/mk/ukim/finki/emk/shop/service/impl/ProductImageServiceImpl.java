package mk.ukim.finki.emk.shop.service.impl;

import mk.ukim.finki.emk.shop.model.ProductImage;
import mk.ukim.finki.emk.shop.repository.ProductImageRepository;
import mk.ukim.finki.emk.shop.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nadica-PC on 6/4/2015.
 */
@Service
public class ProductImageServiceImpl extends BaseEntityCrudServiceImpl<ProductImage, ProductImageRepository> implements ProductImageService {

    @Autowired
    private ProductImageRepository repository;

    @Override
    protected ProductImageRepository getRepository() {
        return repository;
    }
}

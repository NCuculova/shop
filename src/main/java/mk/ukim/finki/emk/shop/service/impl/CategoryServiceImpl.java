package mk.ukim.finki.emk.shop.service.impl;

import mk.ukim.finki.emk.shop.model.Category;
import mk.ukim.finki.emk.shop.repository.CategoryRepository;
import mk.ukim.finki.emk.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nadica-PC on 6/4/2015.
 */
@Service
public class CategoryServiceImpl extends BaseEntityCrudServiceImpl<Category,CategoryRepository> implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    protected CategoryRepository getRepository() {
        return repository;
    }
}

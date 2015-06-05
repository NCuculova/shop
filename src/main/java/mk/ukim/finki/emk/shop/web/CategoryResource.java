package mk.ukim.finki.emk.shop.web;

import mk.ukim.finki.emk.shop.model.Category;
import mk.ukim.finki.emk.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Nadica-PC on 6/4/2015.
 */

@RestController
@RequestMapping("/api/category")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Category create(@RequestBody @Valid Category entity,
                        HttpServletRequest request, HttpServletResponse response) {
        categoryService.save(entity);
        return entity;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Category> getAll() {
        List<Category> categoryList =categoryService
                .findAll();
        return categoryList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Category getProduct(@PathVariable Long id,
                              HttpServletResponse response) {
        Category category = categoryService.findOne(id);
        if (category == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return category;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable Long id, HttpServletRequest request,
                       HttpServletResponse response) {
        categoryService.delete(id);
    }
}

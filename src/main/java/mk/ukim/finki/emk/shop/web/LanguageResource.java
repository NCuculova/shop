package mk.ukim.finki.emk.shop.web;

import mk.ukim.finki.emk.shop.model.Category;
import mk.ukim.finki.emk.shop.model.Language;
import mk.ukim.finki.emk.shop.service.CategoryService;
import mk.ukim.finki.emk.shop.service.LanguageService;
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
@RequestMapping("/api/language")
public class LanguageResource {

    @Autowired
    private LanguageService languageService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Language> getAll() {
        List<Language> languagesList = languageService
                .findAll();
        return languagesList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Language getLanguage(@PathVariable Long id,
                              HttpServletResponse response) {
        Language language = languageService.findOne(id);
        if (language == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return language;
    }




}

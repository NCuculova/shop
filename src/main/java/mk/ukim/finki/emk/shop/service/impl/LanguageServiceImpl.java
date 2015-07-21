package mk.ukim.finki.emk.shop.service.impl;

import mk.ukim.finki.emk.shop.model.Language;
import mk.ukim.finki.emk.shop.repository.LanguageRepository;
import mk.ukim.finki.emk.shop.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nadica-PC on 6/4/2015.
 */
@Service
public class LanguageServiceImpl extends BaseEntityCrudServiceImpl<Language,LanguageRepository> implements LanguageService {

    @Autowired
    private LanguageRepository repository;

    @Override
    protected LanguageRepository getRepository() {
        return repository;
    }
}

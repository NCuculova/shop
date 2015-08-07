package mk.ukim.finki.emk.shop.service.impl;

import mk.ukim.finki.emk.shop.model.TransactionProduct;
import mk.ukim.finki.emk.shop.repository.TransactionProductRepository;
import mk.ukim.finki.emk.shop.service.TransactionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nadica-PC on 6/4/2015.
 */
@Service
public class TransactionProductImpl extends BaseEntityCrudServiceImpl<TransactionProduct,TransactionProductRepository> implements TransactionProductService {

    @Autowired
    private TransactionProductRepository repository;

    @Override
    protected TransactionProductRepository getRepository() {
        return repository;
    }
}

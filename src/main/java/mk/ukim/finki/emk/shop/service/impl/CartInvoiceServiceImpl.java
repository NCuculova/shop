package mk.ukim.finki.emk.shop.service.impl;

import mk.ukim.finki.emk.shop.model.CartInvoice;
import mk.ukim.finki.emk.shop.repository.CartInvoiceRepository;
import mk.ukim.finki.emk.shop.service.CartInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nadica-PC on 6/4/2015.
 */
@Service
public class CartInvoiceServiceImpl extends BaseEntityCrudServiceImpl<CartInvoice,CartInvoiceRepository> implements CartInvoiceService {

    @Autowired
    private CartInvoiceRepository repository;

    @Override
    protected CartInvoiceRepository getRepository() {
        return repository;
    }
}

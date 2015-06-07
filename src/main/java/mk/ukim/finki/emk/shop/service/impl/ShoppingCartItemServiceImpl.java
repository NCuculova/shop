package mk.ukim.finki.emk.shop.service.impl;

import mk.ukim.finki.emk.shop.model.ShoppingCartItem;
import mk.ukim.finki.emk.shop.repository.ShoppingCartItemRepository;
import mk.ukim.finki.emk.shop.service.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nadica-PC on 6/4/2015.
 */
@Service
public class ShoppingCartItemServiceImpl extends BaseEntityCrudServiceImpl<ShoppingCartItem, ShoppingCartItemRepository>
        implements ShoppingCartItemService {

    @Autowired
    private ShoppingCartItemRepository repository;

    @Override
    protected ShoppingCartItemRepository getRepository() {
        return repository;
    }
}

package mk.ukim.finki.emk.shop.service.impl;

import mk.ukim.finki.emk.shop.model.ShoppingCartItem;
import mk.ukim.finki.emk.shop.repository.ShoppingCartItemRepository;
import mk.ukim.finki.emk.shop.service.ShoppingCartItemService;
import mk.ukim.finki.emk.shop.specifications.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void clearCart(String token) {
        List<ShoppingCartItem> items = repository.findAll(Specifications.token(token));
        repository.delete(items);
    }
}

package mk.ukim.finki.emk.shop.web;

import mk.ukim.finki.emk.shop.model.ShoppingCartItem;
import mk.ukim.finki.emk.shop.service.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.CookieStore;
import java.util.List;

/**
 * Created by Nadica-PC on 6/4/2015.
 */

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartResource {

    @Autowired
    private ShoppingCartItemService shoppingCartService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ShoppingCartItem create(@RequestBody @Valid ShoppingCartItem entity,
                                   HttpServletRequest request, HttpServletResponse response) {
        shoppingCartService.save(entity);
        return entity;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<ShoppingCartItem> getAll() {
        List<ShoppingCartItem> cartList = shoppingCartService
                .findAll();
        return cartList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ShoppingCartItem getCartItem(@PathVariable Long id,
                                        HttpServletResponse response) {
        ShoppingCartItem cart = shoppingCartService.findOne(id);
        if (cart == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return cart;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable Long id, HttpServletRequest request,
                       HttpServletResponse response) {
        shoppingCartService.delete(id);
    }

    @RequestMapping(value ="items/{id}", method = RequestMethod.POST, produces = "application/json")
    public void addToCart(@RequestParam Long id, @RequestParam Double quantity, HttpServletRequest request,
                                      HttpServletResponse response) {
        Cookie [] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            System.out.println("Cookies FOUND:" + cookie.getName());
        }
    }
}

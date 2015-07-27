package mk.ukim.finki.emk.shop.web;

import mk.ukim.finki.emk.shop.model.Product;
import mk.ukim.finki.emk.shop.model.ShoppingCartItem;
import mk.ukim.finki.emk.shop.service.ProductService;
import mk.ukim.finki.emk.shop.service.ShoppingCartItemService;
import mk.ukim.finki.emk.shop.specifications.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Created by Nadica-PC on 6/4/2015.
 */

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartResource {

    @Autowired
    private ShoppingCartItemService shoppingCartService;

    @Autowired
    private ProductService productService;

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

    private String tokenUtil(HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies = request.getCookies();
        String guid = null;

        boolean tokenFound = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token_guid")) {
                    guid = cookie.getValue();
                    tokenFound = true;
                    //cookie duration is extended for another month
                    response.addCookie(cookie);
                }
            }
        }
        if (!tokenFound) {
            Cookie tokenGuid = new Cookie("token_guid", UUID.randomUUID().toString());
            guid = tokenGuid.getValue();
            tokenGuid.setPath(request.getContextPath() + "/");
            tokenGuid.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(tokenGuid);
        }

        return guid;
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST, produces = "application/json")
    public ShoppingCartItem addToCart(@RequestParam Long id, @RequestParam Double quantity, HttpServletRequest request,
                                      HttpServletResponse response) {
        Product product = productService.findOne(id);

        String guid = tokenUtil(request, response);

        ShoppingCartItem cartItem = shoppingCartService.findOne(Specifications.productItem(id, guid));
        if (cartItem != null) {
            cartItem.setQuantity(quantity + cartItem.getQuantity());
        } else {
            cartItem = new ShoppingCartItem();
            cartItem.setToken(guid);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }
        shoppingCartService.save(cartItem);

        return cartItem;
    }

    @RequestMapping(value = "/get_cart", method = RequestMethod.GET, produces = "application/json")
    public List<ShoppingCartItem> getCart(HttpServletRequest request, HttpServletResponse response) {
        String guid = tokenUtil(request, response);
        List<ShoppingCartItem> cartItems = shoppingCartService.findAll(Specifications.token(guid));
        return cartItems;
    }

    @RequestMapping(value = "/clear", method = RequestMethod.POST, produces = "application/json")
    public void clearCart(HttpServletRequest request, HttpServletResponse response) {
        String token = tokenUtil(request, response);
        shoppingCartService.clearCart(token);
    }

}

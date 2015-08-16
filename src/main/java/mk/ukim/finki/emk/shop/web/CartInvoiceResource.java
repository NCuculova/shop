package mk.ukim.finki.emk.shop.web;

import mk.ukim.finki.emk.shop.model.CartInvoice;
import mk.ukim.finki.emk.shop.service.CartInvoiceService;
import mk.ukim.finki.emk.shop.specifications.Specifications;
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
@RequestMapping("/api/cart_invoice")
public class CartInvoiceResource {

    @Autowired
    private CartInvoiceService cartInvoiceService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public CartInvoice create(@RequestBody @Valid CartInvoice entity,
                        HttpServletRequest request, HttpServletResponse response) {
        cartInvoiceService.save(entity);
        return entity;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<CartInvoice> getAll() {
        List<CartInvoice> cartInvoices = cartInvoiceService
                .findAll();
        return cartInvoices;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public CartInvoice getOne(@PathVariable Long id,
                              HttpServletResponse response) {
        CartInvoice cartInvoice = cartInvoiceService.findOne(id);
        if (cartInvoice == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return cartInvoice;
    }

    @RequestMapping(value = "transaction/{id}", method = RequestMethod.GET, produces = "application/json")
    public CartInvoice getOneByTransactionId(@PathVariable String id,
                              HttpServletResponse response) {
        CartInvoice cartInvoice = cartInvoiceService.findOne(Specifications.transaction(id));
        if (cartInvoice == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return cartInvoice;
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<CartInvoice> getOrdersByUserId(@PathVariable Long id,
                                             HttpServletResponse response) {
        List<CartInvoice> orders = cartInvoiceService.findAll(Specifications.user(id));
        return orders;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable Long id, HttpServletRequest request,
                       HttpServletResponse response) {
        cartInvoiceService.delete(id);
    }
}

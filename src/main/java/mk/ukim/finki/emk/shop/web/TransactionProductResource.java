package mk.ukim.finki.emk.shop.web;

import mk.ukim.finki.emk.shop.model.TransactionProduct;
import mk.ukim.finki.emk.shop.service.TransactionProductService;
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
@RequestMapping("/api/transaction_products")
public class TransactionProductResource {

    @Autowired
    private TransactionProductService transactionProductService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public TransactionProduct create(@RequestBody @Valid TransactionProduct entity,
                        HttpServletRequest request, HttpServletResponse response) {
        transactionProductService.save(entity);
        return entity;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<TransactionProduct> getAll() {
        List<TransactionProduct> products = transactionProductService
                .findAll();
        return products;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public TransactionProduct getOne(@PathVariable Long id,
                              HttpServletResponse response) {
        TransactionProduct transactionProduct = transactionProductService.findOne(id);
        if (transactionProduct == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return transactionProduct;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable Long id, HttpServletRequest request,
                       HttpServletResponse response) {
        transactionProductService.delete(id);
    }

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<TransactionProduct> getCartProducts(@PathVariable Long id,
                                     HttpServletResponse response) {
        List<TransactionProduct> products = transactionProductService.findAll(Specifications.cart(id));
        return products;
    }
}

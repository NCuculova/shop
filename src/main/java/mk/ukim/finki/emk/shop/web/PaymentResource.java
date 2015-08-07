package mk.ukim.finki.emk.shop.web;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import mk.ukim.finki.emk.shop.model.CartInvoice;
import mk.ukim.finki.emk.shop.model.ShoppingCartItem;
import mk.ukim.finki.emk.shop.model.TransactionDetails;
import mk.ukim.finki.emk.shop.model.TransactionProduct;
import mk.ukim.finki.emk.shop.service.CartInvoiceService;
import mk.ukim.finki.emk.shop.service.ShoppingCartItemService;
import mk.ukim.finki.emk.shop.service.TransactionProductService;
import mk.ukim.finki.emk.shop.service.payment.PaymentService;
import mk.ukim.finki.emk.shop.specifications.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by Nadica-PC on 7/28/2015.
 */

@RestController
@RequestMapping("/api/payment")
public class PaymentResource {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @Autowired
    private CartInvoiceService cartInvoiceService;

    @Autowired
    private TransactionProductService transactionProductService;

    private void saveCartInvoice(Payment payment, List<ShoppingCartItem> items){
        Transaction transaction = payment.getTransactions().get(0);
        Date date = new Date();
        CartInvoice cartInvoice = new CartInvoice();
        Amount amount = transaction.getAmount();
        cartInvoice.setTotalAmount(Double.parseDouble(amount.getTotal()));
        Details details = amount.getDetails();
        cartInvoice.setSubTotal(Double.parseDouble(details.getSubtotal()));
        cartInvoice.setTaks(Double.parseDouble(details.getTax()));
        cartInvoice.setDateInserted(date);
        cartInvoice.setTransactionId(payment.getId());
        cartInvoiceService.save(cartInvoice);

        for (ShoppingCartItem item : items) {
            TransactionProduct transactionProduct = new TransactionProduct();
            transactionProduct.setProduct(item.getProduct());
            transactionProduct.setUnitPrice(item.getProduct().getPrice());
            transactionProduct.setQuantity(item.getQuantity());
            transactionProduct.setCartInvoice(cartInvoice);
            transactionProductService.save(transactionProduct);
        }

    }

    @RequestMapping(value = "/credit-card", method = RequestMethod.POST, produces = "application/json")
    public Payment executePayment(@RequestBody TransactionDetails transactionDetails, HttpServletRequest request,
                                  HttpServletResponse response) throws PayPalRESTException {

        // ###Address
        // Base Address object used as shipping or billing
        // address in a payment. [Optional]
        Address billingAddress = new Address();
        billingAddress.setCity(transactionDetails.getCity());
        billingAddress.setCountryCode("US");
        billingAddress.setLine1(transactionDetails.getAddress());
        billingAddress.setPostalCode(transactionDetails.getPostalCode());
        billingAddress.setState(transactionDetails.getCountry());

        // ###CreditCard
        // A resource representing a credit card that can be
        // used to fund a payment.
        CreditCard creditCard = new CreditCard();
        creditCard.setBillingAddress(billingAddress);
        creditCard.setCvv2(transactionDetails.getCvv());
        creditCard.setExpireMonth(transactionDetails.getExpireMonth());
        creditCard.setExpireYear(transactionDetails.getExpireYear());
        creditCard.setFirstName(transactionDetails.getName());
        creditCard.setLastName(transactionDetails.getSurname());
        creditCard.setNumber(transactionDetails.getCardNumber());
        creditCard.setType(transactionDetails.getCardType());

        String userToken = ShoppingCartResource.tokenUtil(request, response);
        List<ShoppingCartItem> items = shoppingCartItemService.findAll(Specifications.token(userToken));

        Payment payment = null;
            payment = paymentService.executeCreditCardPayment(billingAddress,
                    creditCard, items);

        saveCartInvoice(payment, items);
        shoppingCartItemService.clearCart(userToken);
        return payment;
    }

    @RequestMapping(value = "/paypal", method = RequestMethod.POST, produces = "application/json")
    public Payment approvePayPalPayment(HttpServletRequest request,
                                        HttpServletResponse response) {

        String userToken = ShoppingCartResource.tokenUtil(request, response);
        List<ShoppingCartItem> items = shoppingCartItemService.findAll(Specifications.token(userToken));
        Payment payment = paymentService.approvePayPalPayment(items);
        return payment;
    }

    @RequestMapping(value = "/paypal-execute", method = RequestMethod.POST, produces = "application/json")
    public Payment executePayPalPayment(@RequestParam String payerId, @RequestParam String paymentId, HttpServletRequest request,
                                        HttpServletResponse response) {

        Payment payment = paymentService.executePayPalPayment(paymentId, payerId);
        String userToken = ShoppingCartResource.tokenUtil(request, response);
        List<ShoppingCartItem> items = shoppingCartItemService.findAll(Specifications.token(userToken));
        saveCartInvoice(payment, items);
        shoppingCartItemService.clearCart(userToken);
        return payment;
    }
}
package mk.ukim.finki.emk.shop.web;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Payment;
import mk.ukim.finki.emk.shop.model.ShoppingCartItem;
import mk.ukim.finki.emk.shop.model.TransactionDetails;
import mk.ukim.finki.emk.shop.service.ShoppingCartItemService;
import mk.ukim.finki.emk.shop.service.payment.PaymentService;
import mk.ukim.finki.emk.shop.specifications.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "application/json")
    public Payment executePayment(@RequestBody TransactionDetails transactionDetails, HttpServletRequest request,
                                  HttpServletResponse response) {
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
        //  creditCard.setFirstName("Joe");
        // creditCard.setLastName("Shopper");
        creditCard.setNumber(transactionDetails.getCardNumber());
        creditCard.setType(transactionDetails.getCardType());

        List<ShoppingCartItem> items = shoppingCartItemService.findAll(Specifications.token(ShoppingCartResource.tokenUtil(request, response)));
        return paymentService.executeCreditCardPayment(billingAddress,
                creditCard, items);
    }
}

package mk.ukim.finki.emk.shop.web;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Payment;
import mk.ukim.finki.emk.shop.model.ShoppingCartItem;
import mk.ukim.finki.emk.shop.service.ShoppingCartItemService;
import mk.ukim.finki.emk.shop.service.payment.PaymentService;
import mk.ukim.finki.emk.shop.specifications.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
    public Payment executePayment() {
        // ###Address
        // Base Address object used as shipping or billing
        // address in a payment. [Optional]
        Address billingAddress = new Address();
        billingAddress.setCity("Johnstown");
        billingAddress.setCountryCode("US");
        billingAddress.setLine1("52 N Main ST");
        billingAddress.setPostalCode("43210");
        billingAddress.setState("OH");

        // ###CreditCard
        // A resource representing a credit card that can be
        // used to fund a payment.
        CreditCard creditCard = new CreditCard();
        creditCard.setBillingAddress(billingAddress);
        creditCard.setCvv2(111);
        creditCard.setExpireMonth(11);
        creditCard.setExpireYear(2018);
        creditCard.setFirstName("Joe");
        creditCard.setLastName("Shopper");
        creditCard.setNumber("5500005555555559");
        creditCard.setType("mastercard");

        List<ShoppingCartItem> items = shoppingCartItemService.findAll(Specifications.token("deab2198-de3e-4b35-b26a-0581fd0f4cbf"));
        System.out.println("ITEMSSSS--->" + items.size());
        return paymentService.executeCreditCardPayment(billingAddress,
                creditCard, items);
    }
}

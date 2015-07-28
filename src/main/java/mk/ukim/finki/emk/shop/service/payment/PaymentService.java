package mk.ukim.finki.emk.shop.service.payment;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Payment;
import mk.ukim.finki.emk.shop.model.ShoppingCartItem;

import java.util.List;

/**
 * Created by Nadica-PC on 7/27/2015.
 */
public interface PaymentService {

    public Payment executeCreditCardPayment(Address billingAddress,
                                            CreditCard creditCard,
                                            List<ShoppingCartItem> items);
}

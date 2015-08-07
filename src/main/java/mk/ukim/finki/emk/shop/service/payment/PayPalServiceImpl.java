package mk.ukim.finki.emk.shop.service.payment;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.rest.PayPalResource;
import mk.ukim.finki.emk.shop.model.ShoppingCartItem;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NADICA :) on 6.5.15.
 */
@Service
public class PayPalServiceImpl implements PaymentService {

    private APIContext apiContext;

    private String accessToken;

    @PostConstruct
    public void config() {
        InputStream is = PayPalServiceImpl.class.getResourceAsStream("/sdk_config.properties");
        try {
            OAuthTokenCredential res = PayPalResource.initConfig(is);
            accessToken = res.getAccessToken();

            apiContext = new APIContext(accessToken);

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Payment executeCreditCardPayment(Address billingAddress, CreditCard creditCard, List<ShoppingCartItem> items) throws PayPalRESTException {

        List<Transaction> transactions = createTransactions(items);

        // ###FundingInstrument
        // A resource representing a Payeer's funding instrument.
        // Use a Payer ID (A unique identifier of the payer generated
        // and provided by the facilitator. This is required when
        // creating or using a tokenized funding instrument)
        // and the `CreditCardDetails`
        FundingInstrument fundingInstrument = new FundingInstrument();
        fundingInstrument.setCreditCard(creditCard);


        // The Payment creation API requires a list of
        // FundingInstrument; add the created `FundingInstrument`
        // to a List
        List<FundingInstrument> fundingInstrumentList = new ArrayList<FundingInstrument>();
        fundingInstrumentList.add(fundingInstrument);

        // ###Payer
        // A resource representing a Payer that funds a payment
        // Use the List of `FundingInstrument` and the Payment Method
        // as 'credit_card'
        Payer payer = new Payer();
        payer.setFundingInstruments(fundingInstrumentList);
        payer.setPaymentMethod("credit_card");

        // ###Payment
        // A Payment Resource; create one using
        // the above types and intent as 'sale'
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        Payment createdPayment = null;
            // Use this variant if you want to pass in a request id
            // that is meaningful in your application, ideally
            // a order id.

        String requestId = Long.toString(System.nanoTime());
        APIContext apiContext = new APIContext(accessToken, requestId);


            // Create a payment by posting to the APIService
            // using a valid AccessToken
            // The return object contains the status;
            createdPayment = payment.create(apiContext);
           return createdPayment;
    }

    private List<Transaction> createTransactions(List<ShoppingCartItem> items) {
        // The Payment creation API requires a list of
        // Transaction; add the created `Transaction`
        // to a List
        List<Transaction> transactions = new ArrayList<Transaction>();
        int subTotal = 0;
        for (ShoppingCartItem item : items) {
            subTotal += (int) item.getPrice();
        }
        // ###Details
        // Let's you specify details of a payment amount.
        Details details = new Details();
        details.setSubtotal(subTotal + "");
        details.setTax((int) (subTotal * 0.18) + "");

        // ###Amount
        // Let's you specify a payment amount.
        Amount amount = new Amount();
        amount.setCurrency("USD");
        // Total must be equal to sum of shipping, tax and subtotal.
        amount.setTotal((int) (subTotal * 1.18) + "");
        amount.setDetails(details);
        // ###Transaction
        // A transaction defines the contract of a
        // payment - what is the payment for and who
        // is fulfilling it. Transaction is created with
        // a `Payee` and `Amount` types
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("eShop checkout");
        transactions.add(transaction);
        return transactions;
    }

    @Override
    public Payment approvePayPalPayment(List<ShoppingCartItem> items) {

        List<Transaction> transactions = createTransactions(items);
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/#/pay_pal?success=false");
        redirectUrls.setReturnUrl("http://localhost:8080/#/pay_pal?success=true");
        payment.setRedirectUrls(redirectUrls);

        try {
            Payment createdPayment = payment.create(apiContext);
            return createdPayment;
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Payment executePayPalPayment(String paymentId, String payerId) {

        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        try {
            payment = payment.execute(apiContext, paymentExecute);
                return payment;
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package mk.ukim.finki.emk.shop.web;

import com.paypal.base.rest.PayPalRESTException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nadica-PC on 8/7/2015.
 */

@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(PayPalRESTException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> payPalError(PayPalRESTException e){
        Map<String,Object> result = new HashMap<>();
        System.out.println("Exception: " + e.getMessage());
        result.put("message", e.getMessage());
        return result;
    }
}

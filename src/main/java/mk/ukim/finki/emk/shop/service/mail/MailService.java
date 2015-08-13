package mk.ukim.finki.emk.shop.service.mail;

import com.paypal.base.codec.CharEncoding;
import mk.ukim.finki.emk.shop.model.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nadica-PC on 8/8/2015.
 */
@Service
public class MailService {

    @Autowired
    private Environment environment;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    private String globalFrom;

    @PostConstruct
    public  void init(){
        globalFrom = environment.getProperty("mail.from");
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(globalFrom);
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            System.out.println("Email sent");
            System.out.println("TO: " + to);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendPaymentEmail(String email, List<ShoppingCartItem> items){
        Locale locale = Locale.getDefault();
        Context context = new Context(locale);
        context.setVariable("items", items);
        String content = templateEngine.process("invoice_mail", context);
        sendEmail(email,"Order confirmation", content, false, true);
    }
}

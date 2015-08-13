package mk.ukim.finki.emk.shop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;

/**
 * Mock implementation of mail sender interface used in dev profile
 */
public class MockJavaMailSenderImpl implements JavaMailSender {

  private final Logger log = LoggerFactory.getLogger(MockJavaMailSenderImpl.class);

  @Override
  public MimeMessage createMimeMessage() {
    return new MimeMessage((Session) null);
  }

  @Override
  public MimeMessage createMimeMessage(InputStream inputStream) throws MailException {
    try {
      return new MimeMessage(null, inputStream);
    } catch (MessagingException e) {
    }
    return null;
  }

  @Override
  public void send(MimeMessage mimeMessage) throws MailException {
    log.debug("Sending MimeMessage mail");
  }

  @Override
  public void send(MimeMessage... mimeMessages) throws MailException {
    log.debug("Sending MimeMessage mails");
  }

  @Override
  public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
    log.debug("Sending MimeMessagePreparator mimeMessagePreparator");
  }

  @Override
  public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
    log.debug("Sending MimeMessagePreparator mimeMessagePreparators");
  }

  @Override
  public void send(SimpleMailMessage simpleMailMessage) throws MailException {
    log.debug("Sending SimpleMailMessage simpleMailMessage");
  }

  @Override
  public void send(SimpleMailMessage... simpleMailMessages) throws MailException {
    log.debug("Sending SimpleMailMessage simpleMailMessages");
  }
}
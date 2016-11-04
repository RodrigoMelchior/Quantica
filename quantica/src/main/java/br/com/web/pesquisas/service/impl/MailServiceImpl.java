package br.com.web.pesquisas.service.impl;
import java.util.Locale;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import br.com.web.pesquisas.configuration.ApplicationProperties;
import br.com.web.pesquisas.domain.Usuario;
import br.com.web.pesquisas.service.MailService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SpringTemplateEngine templateEngine;

    

    /* (non-Javadoc)
	 * @see br.com.ctis.proserv.service.impl.MailService#sendEmail(java.lang.String, java.lang.String, java.lang.String, boolean, boolean)
	 */
    @Override
	@Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(applicationProperties.getMail().getFrom());
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.warn("E-mail could not be sent to user '{}', exception is: {}", to, e.getMessage());
        }
    }

    /* (non-Javadoc)
	 * @see br.com.ctis.proserv.service.impl.MailService#sendActivationEmail(br.com.ctis.proserv.domain.Usuario, java.lang.String)
	 */
    @Override
	@Async
    public void sendActivationEmail(Usuario usuario, String baseUrl) {
        /*log.debug("Sending activation e-mail to '{}'", usuario.getPessoaFisica().getEmailComercial());
        Locale locale = Locale.getDefault();
        Context context = new Context(locale);
        context.setVariable("user", usuario);
        context.setVariable("baseUrl", baseUrl);
        String content = templateEngine.process("activationEmail", context);
        String subject = messageSource.getMessage("email.activation.title", null, locale);
        sendEmail(usuario.getPessoaFisica().getEmailComercial(), subject, content, false, true);*/
    }

    /* (non-Javadoc)
	 * @see br.com.ctis.proserv.service.impl.MailService#sendCreationEmail(br.com.ctis.proserv.domain.Usuario, java.lang.String)
	 */
    @Override
	@Async
    public void sendCreationEmail(Usuario usuario, String baseUrl) {
       /* log.debug("Sending creation e-mail to '{}'", usuario.getPessoaFisica().getEmailComercial());
        Locale locale = Locale.getDefault();
        Context context = new Context(locale);
        context.setVariable("user", usuario);
        context.setVariable("baseUrl", baseUrl);
        String content = templateEngine.process("creationEmail", context);
        String subject = messageSource.getMessage("email.activation.title", null, locale);
        sendEmail(usuario.getPessoaFisica().getEmailComercial(), subject, content, false, true);*/
    }

    /* (non-Javadoc)
	 * @see br.com.ctis.proserv.service.impl.MailService#sendPasswordResetMail(br.com.ctis.proserv.domain.Usuario, java.lang.String)
	 */
    @Override
	@Async
    public void sendPasswordResetMail(Usuario usuario, String baseUrl) {
      /*  log.debug("Sending password reset e-mail to '{}'", usuario.getPessoaFisica().getEmailComercial());
        Locale locale = Locale.getDefault();
        Context context = new Context(locale);
        context.setVariable("user", usuario);
        context.setVariable("baseUrl", baseUrl);
        String content = templateEngine.process("passwordResetEmail", context);
        String subject = messageSource.getMessage("email.reset.title", null, locale);
        sendEmail(usuario.getPessoaFisica().getEmailComercial(), subject, content, false, true);*/
    }
    
}

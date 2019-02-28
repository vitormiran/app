package br.com.app.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import br.com.app.model.db.Usuario;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class MailService extends BaseService {

	private static final Logger log = LoggerFactory.getLogger(MailService.class);
	
	private Configuration freemarkerConfig;
	private JavaMailSender sender;
	private String emailOrigem;

	public MailService(@Value("${app.email.to}") String emailOrigem, JavaMailSender sender, Configuration freemarkerConfig) {
		this.emailOrigem = emailOrigem;
		this.sender = sender;
		this.freemarkerConfig = freemarkerConfig;
	}

	public void notificarNovoUsuario(String linkAtivacao, Usuario usuario) {

		HashMap<String, Object> model = new HashMap<>();
		model.put("nome", usuario.getNome());
		model.put("email", usuario.getEmail());
		model.put("link", linkAtivacao);

		doSend("novo-usuario.ftl", model, usuario.getEmail(), "INOVA CONTABIL - Cadastro de novo usuário!");
	}
	
	private void doSend(String template, Map<String, Object> model, String para, String assunto) {
		String text = "";
		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
			Template t = freemarkerConfig.getTemplate(template);

			text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setFrom(emailOrigem);
			helper.setTo(para);
			helper.setText(text, true);
			helper.setSubject(assunto);

			sender.send(message);
			
		} catch (Exception e) {
			log.info("Armazenando para fila");
			e.printStackTrace();
		}
	}

}
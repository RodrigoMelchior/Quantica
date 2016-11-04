package br.com.web.pesquisas.service;

import br.com.web.pesquisas.domain.Usuario;

public interface MailService {

	void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);

	void sendActivationEmail(Usuario usuario, String baseUrl);

	void sendCreationEmail(Usuario usuario, String baseUrl);

	void sendPasswordResetMail(Usuario usuario, String baseUrl);

}
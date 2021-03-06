package ru.akapich.invest_portfolio.model.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import ru.akapich.invest_portfolio.validator.login.custom_interfaces.ExistingEmail;
import ru.akapich.invest_portfolio.validator.login.custom_interfaces.ExistingLogin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationFrom {

	@Value("${valid.size.login}")//TODO doesn't work
	private final String a = "test";

	//TODO Valid only Askii symbols
	@NotBlank(message = "valid.empty.login")
	@Size(min = 5, max = 32, message = a)
	@ExistingLogin
	private String login;

	@NotBlank(message = "valid.empty.email")
	@Email(message = "valid.email")
	@ExistingEmail
	private String email;

	@NotBlank(message = "valid.empty.password")
	@Size(min = 5, max = 32, message = "valid.size.password")
	private String password;

	@NotBlank(message = "valid.empty.password")
	@Size(min = 5, max = 32, message = "valid.size.password")
	private String confirmPassword;
}
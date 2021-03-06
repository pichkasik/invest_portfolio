package ru.akapich.invest_portfolio.controller.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.login.RegistrationForm;
import ru.akapich.invest_portfolio.model.user.User;
import ru.akapich.invest_portfolio.service.user.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for {@link User}'s pages.
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:3000/*", allowedHeaders = "*", maxAge = 3600)
@PropertySource("classpath:message.properties")
public class UserController {

	@Autowired
	private UserService userService;

	@CrossOrigin(origins = "http://localhost:3000/signup")
	@PostMapping("/api/auth/signup")
	public BaseResponseForm registration(@Valid @RequestBody RegistrationForm form,
											BindingResult bindingResult, Model model) {

		BaseResponseForm baseResponseForm = userService.getResponseRegistration(bindingResult, model);
		if ("".equals(baseResponseForm.getError())){
			userService.saveNewUser(form);
		}
		else{
			log.info(String.format("[-] User '%s' try to register and didn't pass validation. Error message: %s",
					form.getName(), baseResponseForm.getError()));
		}
		return baseResponseForm;
	}

	@GetMapping("/api/auth/token")
	public Map<String,String> token(HttpSession session) {
		Map<String, String> result = new HashMap<>();
		User user = userService.getUserInCurrentSession();
		result.put("name", user.getName());
		result.put("token", session.getId());
		return result;
	}
}


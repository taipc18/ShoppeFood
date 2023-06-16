package Main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Main.dao.AccountDAO;
import Main.entity.Account;
import Main.service.UserServices;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Controller
public class SecurityCtrl {
	@Autowired
	private UserServices userServices;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	AccountDAO dao;
	
	@RequestMapping("/security/changepass/form")
	public String changePass() {
		return "security/forgetpassword";
	}
	
	@RequestMapping("/security/signup/form")
	public String signUp() {
		return "security/signup";
	}
	
	@RequestMapping("/security/login/form")
	public String loginform(Model md) {
		md.addAttribute("ms", "Please Login");
		return "security/login";
	}
	
	@RequestMapping("/security/login/success")
	public String loginSuccess(Model md) {
		md.addAttribute("ms", "Login Successful");
		return "security/login";
	}
	
	@RequestMapping("/security/login/error")
	public String loginError(Model md) {
		md.addAttribute("ms", "Login error");
		return "security/login";
	}
	
	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model md) {
		md.addAttribute("ms", "Get out");
		return "security/login";
	}
	
	@RequestMapping("/security/logoff/success")
	public String logoutSuccess(Model md) {
		md.addAttribute("ms", "Logout Successful");
		return "security/login";
	}
	
	@PostMapping("/security/changepass/form")
	public String processForgotPassword(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");

		try {
			userServices.getPass(email);
			sendEmail(email);
			model.addAttribute("message",
					"Mật khẩu đã gửi đến email của bạn. Vui lòng kiểm tra hộp thư.");

		} catch (UsernameNotFoundException ex) {
			model.addAttribute("error", ex.getMessage());
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Đã xảy ra lỗi khi gửi email");
		}

		return "security/forgetpassword";
	}

	public void sendEmail(String recipientEmail) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("quangdodps15144@fpt.edu.vn");
		helper.setTo(recipientEmail);
		Account account = dao.findByEmail(recipientEmail);
		String subject = "Mật khẩu của bạn";

		String content = "<p>Xin chào,</p>" + "<p>Bạn đã yêu cầu gửi mật khẩu qua email</p>"
				+ "\">Mật khẩu của bạn là</a></h3>"+ account.getPassword()
				+ "Cảm ơn bạn đã mua hàng tại Website của chúng tôi<br>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}
}

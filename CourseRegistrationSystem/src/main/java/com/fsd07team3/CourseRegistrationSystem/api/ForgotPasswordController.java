package com.fsd07team3.CourseRegistrationSystem.api;

import com.fsd07team3.CourseRegistrationSystem.config.UserNotFoundException;
import com.fsd07team3.CourseRegistrationSystem.config.Utility;
import com.fsd07team3.CourseRegistrationSystem.entity.User;
import com.fsd07team3.CourseRegistrationSystem.repository.UserRepository;
import com.fsd07team3.CourseRegistrationSystem.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Controller
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserRepository userRepo;


    // Forgot Password page - check valid email and send it with the rest link
    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(Model model) {
        return "user_forgotPassword";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String resetPasswordToken = UUID.randomUUID().toString();
        try {
            customUserDetailsService.updateResetPasswordToken(resetPasswordToken, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + resetPasswordToken;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email.");
        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }
        return "user_forgotPassword";
    }
    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("playroomfsd07@gmail.com", "FSD07");
        helper.setTo(recipientEmail);
        String subject = "Here's the link to reset your password";
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password. Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }


    // Reset Password page
    @GetMapping("/reset_password")
    public String showResetPasswordForm(@RequestParam("token")  String resetPasswordToken, Model model) {
        User user = customUserDetailsService.findByResetPasswordToken(resetPasswordToken);
        model.addAttribute("resetPasswordToken", resetPasswordToken);
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }
        return "user_resetPassword";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request,
                                       RedirectAttributes redirectAttributes,
                                       Model model) {
        String resetPasswordToken = request.getParameter("resetPasswordToken");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userRepo.findByEmail(email);
        if (user == null) {
            model.addAttribute("message", "Invalid reset password token.");
            return "user_resetPassword";
        }
        model.addAttribute("title", "Reset your password");
        customUserDetailsService.updatePassword(user, password);
        redirectAttributes.addFlashAttribute("flashMessage", "You have successfully reset your password.");
        return "redirect:/login";
    }



}

package com.cmr.covid.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cmr.covid.mail.Mail;
import com.cmr.covid.mail.MailService;
import com.cmr.covid.model.DoctorPojo;
import com.cmr.covid.model.PatientPojo;
import com.cmr.covid.model.VerifyOtp;
import com.cmr.covid.service.DoctorService;
import com.cmr.covid.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	DoctorService doctorService;

	@Autowired
	MailService mailService;

	String token = "111111";

	static String email = "";

	@RequestMapping(value = { "/login" })
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String logoutDo(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		for (Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
		}

		return "login";
	}

	@RequestMapping(value = { "/signup" })
	public ModelAndView signup(PatientPojo patientPojo, RedirectAttributes redirectAttributes)
			throws MessagingException {
		ModelAndView model = new ModelAndView();

		if (patientPojo != null && patientPojo.getpEmail() != null && !patientPojo.getpEmail().isEmpty()) {

			email = patientPojo.getpEmail();

			String pwd = patientPojo.getpPwd();
			patientPojo.setpPwd("{noop}" + pwd);
			String s = loginService.addNewPatient(patientPojo);

			if (!s.isEmpty()) {

				model.addObject("emailError", "Email already Exists");
				model.setViewName("signup");

			} else {

				/*
				 * Mail mail = new Mail(); mail.setTo(patientPojo.getpEmail());
				 * mail.setSubject("Registration password code"); mail.setToken(token);
				 * mail.setFrom("vyshnavisindhe12@gmail.com");
				 * 
				 * VerifyOtp verifyOtp = new VerifyOtp();
				 * 
				 * verifyOtp.setEmail(patientPojo.getpEmail()); verifyOtp.setPassword(token);
				 * 
				 * // mailService.sendEmail(mail);
				 * 
				 * // loginService.addVerifyOtp(verifyOtp);
				 * 
				 * // redirectAttributes.addAttribute("otp", verifyOtp.getEmail());
				 */
				model.setViewName("redirect:/login");
			}

		} else {
			model.setViewName("signup");
		}

		return model;
	}

	@RequestMapping(value = { "/forgotpassword" })
	public ModelAndView forgotpassword(VerifyOtp verifyOtp, RedirectAttributes redirectAttributes)
			throws MessagingException {
		ModelAndView model = new ModelAndView();
		if (verifyOtp != null) {

			if (verifyOtp.getEmail() != null && verifyOtp.getPassword() != null) {

				email = verifyOtp.getEmail();

				/*
				 * Mail mail = new Mail(); mail.setTo(verifyOtp.getEmail());
				 * mail.setSubject("Registration password code"); mail.setToken(token);
				 * mail.setFrom("vyshnavisindhe12@gmail.com");
				 */
				/*
				 * VerifyOtp VerifyOtp1 = new VerifyOtp();
				 * 
				 * VerifyOtp1.setEmail(verifyOtp.getEmail()); VerifyOtp1.setPassword(token);
				 */

				VerifyOtp forgotPwd = new VerifyOtp();

				forgotPwd.setEmail(verifyOtp.getEmail());
				forgotPwd.setPassword("{noop}" + verifyOtp.getPassword());

				 //loginService.addVerifyOtp(VerifyOtp1);

				loginService.forgotPwd(forgotPwd);

			//	redirectAttributes.addAttribute("otp", verifyOtp.getEmail());

				model.setViewName("redirect:/login");
			}

		} else {
			model.setViewName("forgotpassword");
		}

		return model;
	}

	@ModelAttribute("doctors")
	public List<String> initializedoctors() {

		List<DoctorPojo> doctors = doctorService.getDoctorList();

		List<String> doctorsList = new ArrayList<String>();
		for (DoctorPojo doctor : doctors) {
			doctorsList.add(doctor.getdEmail());
		}
		return doctorsList;
	}

	@RequestMapping("/otp")
	public ModelAndView indexPage(ModelAndView model, @RequestParam String otp) throws Exception {

		if (!email.isEmpty()) {

			if (otp != null && !otp.isEmpty()) {

				if (otp.equals(token)) {

					loginService.setUserActive(email);

					email = "";

					model.setViewName("redirect:/login");
				} else { //
					model.addObject("otpmistch", "Please Enter Correct OTP");
					model.setViewName("otp");
				}

			} else {
				model.setViewName("otp");
			}

		}

		return model;
	}

}

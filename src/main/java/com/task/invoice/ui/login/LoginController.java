package com.task.invoice.ui.login;


import com.task.invoice.core.dtos.UserDto;
import com.task.invoice.core.services.CaptchaService;
import com.task.invoice.core.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private int failCount = 0;
    private final UserService userService;

    private final CaptchaService captchaService;

    private UserDto userDto = UserDto.empty();

    @GetMapping("/")
    public String showLoginPage(Model model){
        model.addAttribute("userDto", userDto);
        return "redirect:/login";
    }

    @PostMapping("/goToRegister")
    public String goToRegister(Model model){
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("userDto", userDto);
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute("userDto") UserDto userDto1,
                        BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                        HttpServletRequest request){
        boolean captchaPassed = true;
        if(failCount == 3){
            failCount = 0;
            String captchaResponse = request.getParameter("g-recaptcha-response");
            captchaPassed = captchaService.verifyCaptcha(captchaResponse);
        }
        if(captchaPassed){
            Optional<UserDto> user = userService.login(userDto1.getUsername(), userDto1.getPassword());
            if (user.isEmpty()){
                failCount++;
                userDto = UserDto.empty();
                model.addAttribute("userDto", userDto);
                model.addAttribute("failCount",failCount);
                return "login";
            } else{
                failCount = 0;
                userDto = user.get();
                redirectAttributes.addFlashAttribute("userDto",userDto);
                return "redirect:/home";
            }
        } else{
            failCount = 0;
            model.addAttribute("userDto",UserDto.empty());
            model.addAttribute("failCount",failCount);
            return "login";
        }
    }
}

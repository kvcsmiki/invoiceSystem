package com.task.invoice.ui;

import com.task.invoice.core.dtos.InvoiceDto;
import com.task.invoice.core.dtos.UserDto;
import com.task.invoice.core.entities.User;
import com.task.invoice.core.services.InvoiceService;
import com.task.invoice.core.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final InvoiceService invoiceService;
    private final UserService userService;

    private InvoiceDto invoiceDto = new InvoiceDto();

    @GetMapping("/home")
    public String landing(Model model){
        UserDto userDto = (UserDto) model.getAttribute("userDto");
        if(userDto == null){
            return "redirect:/";
        }
        model.addAttribute("userDto",userDto);
        model.addAttribute("invoiceDto",invoiceDto);
        return "home";
    }

    @PostMapping(value = "/goToAdmin")
    public String goToAdmin(@RequestParam("username") String username,
            @ModelAttribute("userDto") UserDto userDto1,
                            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        UserDto userDto = userService.getUser(username).get();
        redirectAttributes.addFlashAttribute("userDto",userDto);
        return "redirect:/admin";
    }

}

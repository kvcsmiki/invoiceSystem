package com.task.invoice.ui.register;

import com.task.invoice.core.dtos.UserDto;
import com.task.invoice.core.services.RoleService;
import com.task.invoice.core.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final RoleService roleService;

    private String errorMessage;

    private UserDto userDto = UserDto.empty();

    @GetMapping("/register")
    public String landing(Model model){
        model.addAttribute("userDto",userDto);
        model.addAttribute("errorMessage","");
        model.addAttribute("roles",roleService.getAllRolesExceptAdmin());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userDto") UserDto userDto1, Model model, HttpServletRequest request){
        String role = request.getParameter("selectedRole");
        if(userDto1.getUsername().equals("") || userDto1.getPassword().equals("") || role.equals("")){
            errorMessage = "A mezők kitöltése kötelező!";
            model.addAttribute("errorMessage",errorMessage);
            model.addAttribute("roles",roleService.getAllRolesExceptAdmin());
            return "register";
        } else{
            if(userService.registerUser(userDto1.getUsername(), userDto1.getPassword(), role)){
                return "redirect:/";
            } else{
                errorMessage = "Már létezik ilyen nevű felhasználó!";
                model.addAttribute("userDto",UserDto.empty());
                model.addAttribute("errorMessage",errorMessage);
                model.addAttribute("roles",roleService.getAllRolesExceptAdmin());
                return "register";
            }
        }
    }

    @PostMapping("/back")
    public String goBack(Model model){
        model.addAttribute("userDto",UserDto.empty());
        return "redirect:/";
    }
}

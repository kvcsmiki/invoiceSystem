package com.task.invoice.ui.admin;

import com.task.invoice.core.dtos.UserDto;
import com.task.invoice.core.entities.User;
import com.task.invoice.core.helpers.RoleHelper;
import com.task.invoice.core.services.RoleService;
import com.task.invoice.core.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/admin")
    private String landing(Model model){
        UserDto userDto = (UserDto) model.getAttribute("userDto");
        if(userDto == null || userDto.getRoles() == null || !userDto.getRoles().contains(roleService.getRole(RoleHelper.ADMIN.getValue()))){
            return "redirect:/";
        }else {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("roles", roleService.getAllRoles());
            return "admin";
        }
    }
}

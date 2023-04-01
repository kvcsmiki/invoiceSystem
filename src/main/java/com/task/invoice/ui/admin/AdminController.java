package com.task.invoice.ui.admin;

import com.task.invoice.core.dtos.UserDto;
import com.task.invoice.core.entities.Role;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    private String errorMessage = "";

    @GetMapping("/admin")
    private String landing(Model model){
        UserDto userDto = (UserDto) model.getAttribute("userDto");
        if(userDto == null || userDto.getRoles() == null || !userDto.getRoles().contains(roleService.getRole(RoleHelper.ADMIN.getValue()))){
            return "redirect:/";
        }else {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("roles", roleService.getAllRoles());
            model.addAttribute("userDto",userDto);
            return "admin";
        }
    }
    @PostMapping("/admin/saveRoles")
    private String saveRoles(@RequestParam("username") String username,
                             @RequestParam("selectedRoles") List<String> selectedRoles,
                             @RequestParam("userDto") String userDtoName,
                             Model model, RedirectAttributes redirectAttributes){
        UserDto userDto = userService.getUser(userDtoName).get();
        List<Role> roles = roleService.getRoles(selectedRoles);
        if(roles.isEmpty()){
            errorMessage = "Egy szerepkör mindenki számára kötelező!";
        } else{
            if(!userService.saveRoles(username,roles)){
                errorMessage = "A szerepkörök mentése nem sikerült!";
            }
        }
        System.out.println(username);
        System.out.println(roles);
        System.out.println(model.getAttribute("userDto"));
        redirectAttributes.addFlashAttribute("errorMessage",errorMessage);
        redirectAttributes.addFlashAttribute("userDto",userDto);
        return "redirect:/admin";
    }
    @PostMapping("/backToHome")
    private String backToHome(@RequestParam("userDto") String userDtoName,
                              Model model, RedirectAttributes redirectAttributes){
        UserDto userDto = userService.getUser(userDtoName).get();
        redirectAttributes.addFlashAttribute("userDto",userDto);
        return "redirect:/home";
    }

    @PostMapping("/admin/deleteUser")
    private String deleteUser(@RequestParam("username") String username,
                              @RequestParam("userDto") String userDtoName,
                              Model model, RedirectAttributes redirectAttributes){
        UserDto userDto = userService.getUser(userDtoName).get();
        if(!userService.deleteUser(username)){
            errorMessage = "A törlés nem sikerült!";
        }
        redirectAttributes.addFlashAttribute("errorMessage",errorMessage);
        redirectAttributes.addFlashAttribute("userDto",userDto);
        return "redirect:/admin";
    }
}

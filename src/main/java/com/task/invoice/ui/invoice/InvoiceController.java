package com.task.invoice.ui.invoice;

import com.task.invoice.core.dtos.InvoiceDto;
import com.task.invoice.core.dtos.UserDto;
import com.task.invoice.core.helpers.RoleHelper;
import com.task.invoice.core.services.InvoiceService;
import com.task.invoice.core.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    private final UserService userService;

    @GetMapping("/invoiceList")
    private String landing(Model model){
        UserDto userDto = (UserDto) model.getAttribute("userDto");
        model.addAttribute("invoices",invoiceService.getAllInvoices());
        model.addAttribute("userDto",userDto);
        return "invoiceList";
    }

    @PostMapping("/goToInvoice")
    private String invoicePage(@RequestParam("invoiceId") String invoiceId,
                               @RequestParam("userDto") String userDtoName,
                               Model model, RedirectAttributes redirectAttributes){
        UserDto userDto = userService.getUser(userDtoName).get();
        InvoiceDto invoiceDto = invoiceService.getInvoiceById(Integer.parseInt(invoiceId)).get();
        redirectAttributes.addFlashAttribute("invoiceDto",invoiceDto);
        redirectAttributes.addFlashAttribute("userDto",userDto);
        redirectAttributes.addFlashAttribute("backPage", "list");
        return "redirect:/invoice";
    }

    @GetMapping("/invoice")
    private String invoiceLanding(Model model){
        UserDto userDto = (UserDto) model.getAttribute("userDto");
        InvoiceDto invoiceDto = (InvoiceDto) model.getAttribute("invoiceDto");
        String backPage = (String) model.getAttribute("backPage");
        model.addAttribute("backPage",backPage);
        model.addAttribute("userDto",userDto);
        model.addAttribute("invoiceDto",invoiceDto);
        return "invoice";
    }

    @PostMapping("/invoice/back")
    private String back(@RequestParam("userDto") String userDtoName,
                        @RequestParam("backPage") String backPage,
                        Model model, RedirectAttributes redirectAttributes){
        UserDto userDto = userService.getUser(userDtoName).get();
        redirectAttributes.addFlashAttribute("userDto",userDto);
        if(backPage.equals("home")){
            return "redirect:/home";
        } else{
            return "redirect:/invoiceList";
        }
    }


}

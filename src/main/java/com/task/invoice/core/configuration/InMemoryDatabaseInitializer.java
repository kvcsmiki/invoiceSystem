package com.task.invoice.core.configuration;

import com.task.invoice.core.entities.Invoice;
import com.task.invoice.core.entities.Role;
import com.task.invoice.core.helpers.RoleHelper;
import com.task.invoice.core.entities.User;
import com.task.invoice.core.repositories.InvoiceRepository;
import com.task.invoice.core.repositories.RoleRepository;
import com.task.invoice.core.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InMemoryDatabaseInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final InvoiceRepository invoiceRepository;

    @PostConstruct
    public void init(){
        Role rAdmin = new Role(RoleHelper.ADMIN.getValue());
        rAdmin.setDescription("Mindenhez hozzáfér");
        Role rAccountant = new Role(RoleHelper.ACCOUNTANT.getValue());
        Role rUser = new Role(RoleHelper.USER.getValue());
        roleRepository.saveAll(List.of(rAdmin,rAccountant,rUser));

        User admin = new User("admin","asd123",
                List.of(roleRepository.findByName(RoleHelper.ADMIN.getValue())));
        User accountant = new User("konyvelo","asd123",
                List.of(roleRepository.findByName(RoleHelper.ACCOUNTANT.getValue())));
        User user = new User("user","asd123",
                List.of(roleRepository.findByName(RoleHelper.USER.getValue())));
        userRepository.saveAll(List.of(admin,accountant,user));

        invoiceRepository.save(new Invoice("Vásárló",LocalDate.now(), LocalDate.now(), "Item","Komment",20));
    }
}

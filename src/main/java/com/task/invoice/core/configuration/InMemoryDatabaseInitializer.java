package com.task.invoice.core.configuration;

import com.task.invoice.core.entities.Role;
import com.task.invoice.core.helpers.RoleHelper;
import com.task.invoice.core.entities.User;
import com.task.invoice.core.repositories.RoleRepository;
import com.task.invoice.core.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InMemoryDatabaseInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostConstruct
    public void init(){
        Role rAdmin = new Role(RoleHelper.ADMIN.getValue());
        rAdmin.setDescription("Mindenhez hozzáfér");
        Role rAccountant = new Role(RoleHelper.ACCOUNTANT.getValue());
        Role rUser = new Role(RoleHelper.USER.getValue());
        roleRepository.saveAll(List.of(rAdmin,rAccountant,rUser));

        User admin = new User("Admin","asd123",
                List.of(roleRepository.findByName(RoleHelper.ADMIN.getValue())));
        User accountant = new User("Konyvelo","asd123",
                List.of(roleRepository.findByName(RoleHelper.ACCOUNTANT.getValue())));
        User user = new User("User","asd123",
                List.of(roleRepository.findByName(RoleHelper.USER.getValue())));
        userRepository.saveAll(List.of(admin,accountant,user));
    }
}

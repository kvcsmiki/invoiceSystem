package com.task.invoice.core.services;

import com.task.invoice.core.dtos.RoleDto;
import com.task.invoice.core.dtos.UserDto;
import com.task.invoice.core.entities.Role;
import com.task.invoice.core.entities.User;
import com.task.invoice.core.repositories.RoleRepository;
import com.task.invoice.core.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private UserDto userDto = null;

    @Override
    public Optional<UserDto> login(String username, String password){
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if(user.isEmpty()){
            return Optional.empty();
        }
        Date loginDate = new Date();
        user.get().setLoginDate(loginDate);
        userRepository.save(user.get());
        UserDto userDto1 = new UserDto(user.get().getUsername(), user.get().getRoles(), loginDate);
        userDto = userDto1;
        return Optional.of(userDto1);
    }

    @Override
    public Optional<UserDto> logout() {
        Optional<UserDto> user = Optional.ofNullable(userDto);
        userDto = null;
        return user;
    }

    @Override
    public void registerUser(String username, String password, RoleDto role) {
        Role role1 = roleRepository.findByName(role.getName());
        User user = new User(username, password, List.of(role1));
        userRepository.save(user);
    }
}

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
import java.util.stream.Collectors;

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
    public boolean registerUser(String username, String password, String role) {
        Role role1 = roleRepository.findByName(role);
        boolean exists = userRepository.findAll().stream().anyMatch(user -> user.getUsername().equals(username));
        if(!exists && role1 != null){
            User user = new User(username, password, List.of(role1));
            userRepository.save(user);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> convertUser(user))
                .toList();
    }

    @Override
    public Optional<UserDto> getUser(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(new UserDto(user.get().getUsername(),user.get().getRoles(),user.get().getLoginDate()));
    }

    @Override
    public boolean saveRoles(String username, List<Role> roles){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            return false;
        }
        user.get().setRoles(roles);
        userRepository.save(user.get());
        return true;
    }

    @Override
    public boolean deleteUser(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            return false;
        }
        userRepository.delete(user.get());
        return true;
    }

    private UserDto convertUser(User user){
        return new UserDto(user.getUsername(), user.getRoles(),user.getLoginDate());
    }
}

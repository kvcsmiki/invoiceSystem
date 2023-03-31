package com.task.invoice.core.dtos;

import com.task.invoice.core.entities.Role;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private List<Role> roles;
    private Date loginDate;

    public static UserDto empty(){
        return new UserDto();
    }

    public UserDto(String username, List<Role> roles, Date loginDate) {
        this.username = username;
        this.roles = roles;
        this.loginDate = loginDate;
    }
}

package com.task.invoice.core.dtos;

import com.task.invoice.core.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {

    private String username;
    private List<Role> roles;
    private Date loginDate;

}

package ru.geekbrains.thymeleafdemo.mapper.security;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import ru.geekbrains.thymeleafdemo.dto.security.UserDto;
import ru.geekbrains.thymeleafdemo.model.security.Role;
import ru.geekbrains.thymeleafdemo.model.security.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    private final String DELIMITER = ", ";
    private final String PREFIX = "ROLE_";

    @Mapping(source = "roles", target = "roles", qualifiedByName = "setRolesToStringRoles")
    public abstract UserDto userToUserDto(User user);

    @Named("setRolesToStringRoles")
    public String setRolesToStringRoles(Set<Role> roles) {
        StringJoiner sjRoles = new StringJoiner(DELIMITER);
        roles
                .stream()
                .sorted(Comparator.comparing(Role::getId))
                .map(Role::getName)
                .map(role -> role.startsWith(PREFIX) ? role.substring(5) : role)
                .forEach(sjRoles::add);
        return sjRoles.toString();
    }
}

package ru.geekbrains.thymeleafdemo.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.thymeleafdemo.mapper.security.UserMapper;
import ru.geekbrains.thymeleafdemo.service.security.UserService;

import java.util.stream.Collectors;

@Controller
@RequestMapping("user")
public class UserController {

    UserService userService;
    UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/list")
    public String getUserList(Model model) {
        model.addAttribute(
                "users",
                userService.getAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList()));
        return "user_list";
    }
}

package com.api.crud.controllers;

import com.api.crud.models.UserModel;
import com.api.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<UserModel> getUsers() {
        return userService.todosLosUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<UserModel> getUserById(@PathVariable Long id) {
        return userService.usuarioporId(id);
    }

    @PostMapping("/agregar")
    public UserModel saveUser(@RequestBody UserModel usermodel) {
        return userService.saveUser(usermodel);
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@RequestBody UserModel usermodel, @PathVariable Long id) {
        return userService.usuarioActualizar(usermodel, id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        boolean ok = userService.deleteUser(id);
        if (ok) {
            return "User with id " + id + " delete";
        } else
            return "Error, we hace a problem ";
    }

}


package com.api.crud.services;

import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository iuserRepository;

    public List<UserModel> todosLosUsuarios() {
        return iuserRepository.findAll();
    }

    public UserModel saveUser(UserModel usermodel) {
        iuserRepository.save(usermodel);
        return usermodel;
    }

    public Optional<UserModel> usuarioporId(Long id) {
        return iuserRepository.findById(id);
    }

    public UserModel usuarioActualizar(UserModel userUpdate, Long id) {
        UserModel usermodel = iuserRepository.findById(id).get();
        usermodel.setFirstName(userUpdate.getFirstName());
        usermodel.setLastName(userUpdate.getLastName());
        usermodel.setEmail(userUpdate.getEmail());
        iuserRepository.save(usermodel);
        return userUpdate;
    }
    public Boolean deleteUser(Long id) {
        try {
            iuserRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}

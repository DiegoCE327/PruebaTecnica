package com.api.crud.testservice;

import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;
import com.api.crud.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class testService {
    @Mock
    private IUserRepository iuserRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testTodosLosUsuarios() {
        // Arrange
        List<UserModel> users = Arrays.asList(
                new UserModel(1L, "John", "Doe", "johndoe@example.com"),
                new UserModel(2L, "Jane", "Smith", "janesmith@example.com")
        );
        Mockito.when(iuserRepository.findAll()).thenReturn(users);

        // Act
        List<UserModel> result = userService.todosLosUsuarios();

        // Assert
        Assertions.assertEquals(users, result);
    }

    @Test
    public void testSaveUser() {
        // Arrange
        UserModel user = new UserModel(1L, "John", "Doe", "johndoe@example.com");

        // Act
        UserModel result = userService.saveUser(user);

        // Assert
        Assertions.assertEquals(user, result);
        Mockito.verify(iuserRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void testUsuarioporId() {
        // Arrange
        Long userId = 1L;
        UserModel user = new UserModel(userId, "John", "Doe", "johndoe@example.com");
        Mockito.when(iuserRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<UserModel> result = userService.usuarioporId(userId);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(user, result.get());
    }

    @Test
    public void testUsuarioActualizar() {
        // Arrange
        Long userId = 1L;
        UserModel userUpdate = new UserModel(userId, "John", "Doe", "newemail@example.com");
        UserModel existingUser = new UserModel(userId, "John", "Doe", "johndoe@example.com");
        Mockito.when(iuserRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        Mockito.when(iuserRepository.save(existingUser)).thenReturn(existingUser);

        // Act
        UserModel result = userService.usuarioActualizar(userUpdate, userId);

        // Assert
        Assertions.assertEquals(userUpdate, result);
        Assertions.assertEquals("newemail@example.com", existingUser.getEmail());
        Mockito.verify(iuserRepository, Mockito.times(1)).save(existingUser);
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        Long userId = 1L;

        // Act
        Boolean result = userService.deleteUser(userId);

        // Assert
        Assertions.assertTrue(result);
        Mockito.verify(iuserRepository, Mockito.times(1)).deleteById(userId);
    }

    @Test
    public void testDeleteUserNull() {
        // Arrange
        Long userId = 1L;

        // Act
        Boolean result = userService.deleteUser(userId);

        // Assert
        Assertions.assertTrue(result);
        Mockito.verify(iuserRepository, Mockito.times(1)).deleteById(userId);
    }
}

package com.api.crud.testcontroller;

import com.api.crud.controllers.UserController;
import com.api.crud.models.UserModel;
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
class CrudApplicationTests {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;
	
	@Test
	void testGetUsers() {
		List<UserModel> users = Arrays.asList(
				new UserModel(1L, "John", "Doe", "johndoe@example.com"),
				new UserModel(2L, "Jane", "Smith", "janesmith@example.com")
		);

		Mockito.when(userService.todosLosUsuarios()).thenReturn(users);
		List<UserModel> result = userController.getUsers();
		Assertions.assertEquals(users,result);
	}
	@Test
	public void testGetUserById() {

		// Arrange
		Long userId = 1L;
		Optional<UserModel> user = Optional.of(new UserModel(userId, "John", "Doe", "johndoe@example.com"));
		Mockito.when(userService.usuarioporId(userId)).thenReturn(user);

		// Act
		Optional<UserModel> result = userController.getUserById(userId);

		// Assert
		Assertions.assertEquals(user, result);
	}

	@Test
	public void testSaveUser() {
		// Arrange
		UserModel user = new UserModel(1L, "John", "Doe", "johndoe@example.com");
		Mockito.when(userService.saveUser(user)).thenReturn(user);

		// Act
		UserModel result = userController.saveUser(user);

		// Assert
		Assertions.assertEquals(user, result);
	}

	@Test
	public void testUpdateUser() {
		// Arrange
		Long userId = 1L;
		UserModel user = new UserModel(userId, "John", "Doe", "newemail@example.com");
		Mockito.when(userService.usuarioActualizar(user, userId)).thenReturn(user);

		// Act
		UserModel result = userController.updateUser(user, userId);

		// Assert
		Assertions.assertEquals(user, result);
	}

	@Test
	public void testDeleteUser() {
		// Arrange
		Long userId = 1L;
		Mockito.when(userService.deleteUser(userId)).thenReturn(true);

		// Act
		String result = userController.deleteUser(userId);

		// Assert
		Assertions.assertEquals("User with id " + userId + " delete", result);
	}
	@Test
	public void testDeleteUserNull() {
		// Arrange
		Long userId = 1l;
		Mockito.when(userService.deleteUser(userId)).thenReturn(false);

		// Act
		String result = userController.deleteUser(userId);

		// Assert
		Assertions.assertEquals("Error, we hace a problem ", result);
	}

}

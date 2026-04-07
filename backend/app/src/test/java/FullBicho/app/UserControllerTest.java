package FullBicho.app;

import FullBicho.app.controller.UserController;
import FullBicho.app.dto.UserRequestDTO;
import FullBicho.app.dto.UserUpdateDTO;
import FullBicho.app.repository.UserRepository;
import FullBicho.app.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

@SpringBootTest
class UserControllerTest {

    @Autowired

    @Mock
    private AdminService adminService;
    @Mock
    private UserController userController;

    @Autowired
    @Mock
    private UserRepository userRepository;


    @Test
    void saveUserTestSave(){
        UserRequestDTO userRequestDTO = new UserRequestDTO("Robert","123","robert@email.com","12345678900");
        when(userController.saveUser(userRequestDTO)).thenReturn(new ResponseEntity<>("Usuário salvo com sucesso!", HttpStatus.CREATED));

        ResponseEntity<String> resultado = userController.saveUser(userRequestDTO);

        assertEquals("Usuário salvo com sucesso!",resultado.getBody());
        assertEquals(HttpStatus.CREATED,resultado.getStatusCode());

    }

    @Test
    void saveUserTestBlankName(){
        UserRequestDTO userRequestDTO = new UserRequestDTO("","123","robert@email.com","12345678900");
        when(userController.saveUser(userRequestDTO)).thenReturn(new ResponseEntity<>("NOME DE USUÁRIO OBRIGATÓRIO!!!", HttpStatus.BAD_REQUEST));

        ResponseEntity<String> resultado = userController.saveUser(userRequestDTO);

        assertEquals("NOME DE USUÁRIO OBRIGATÓRIO!!!",resultado.getBody());
        assertEquals(HttpStatus.BAD_REQUEST,resultado.getStatusCode());

    }

    @Test
    void updateUserTest(){

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO("NovoNome","novoEmail@gmail.com","123","12345678900");

        when(userController.updateUser(userUpdateDTO)).thenReturn(new ResponseEntity<>("USUÁRIO ATUALIZADO COM SUCESSO!!!", HttpStatus.CREATED));

        ResponseEntity<String> resultado = userController.updateUser(userUpdateDTO);

        assertEquals("USUÁRIO ATUALIZADO COM SUCESSO!!!",resultado.getBody());
        assertEquals(HttpStatus.CREATED,resultado.getStatusCode());

    }
}
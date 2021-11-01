package com.demo.OfferTechnicalTest.service;

import com.demo.OfferTechnicalTest.OfferTechnicalTestApplication;
import com.demo.OfferTechnicalTest.dao.UserRepository;
import com.demo.OfferTechnicalTest.exception.NotAllowedUserException;
import com.demo.OfferTechnicalTest.exception.UserNotFoundException;
import com.demo.OfferTechnicalTest.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = OfferTechnicalTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @MockBean
    UserRepository userRepository;

    @Test
    void createUserSuccessfully() throws NotAllowedUserException {
        User user = new User();
        user.setUserName("Syrine");
        user.setBirthDate(LocalDate.EPOCH);
        user.setCountry("France");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Assertions.assertNotNull(userService.createUser(user));
    }

    @Test
    void createUserFailed() {
        User user = new User();
        user.setUserName("Syrine");
        user.setBirthDate(LocalDate.EPOCH);
        user.setCountry("Tunisia");
        assertThrows(
                NotAllowedUserException.class,
                () -> userService.createUser(user),
                "This user is not from France");
    }

    @Test
    void getUserById() throws UserNotFoundException {
        User user = new User();
        user.setId(1L);
        user.setUserName("Syrine");
        user.setBirthDate(LocalDate.EPOCH);
        user.setCountry("France");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Assertions.assertNotNull(userService.findById(1L));
    }

}

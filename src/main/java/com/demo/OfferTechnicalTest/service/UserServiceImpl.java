package com.demo.OfferTechnicalTest.service;

import com.demo.OfferTechnicalTest.dao.UserRepository;
import com.demo.OfferTechnicalTest.exception.NotAllowedUserException;
import com.demo.OfferTechnicalTest.exception.UserNotFoundException;
import com.demo.OfferTechnicalTest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    public UserRepository userRepository;

    @Override
    public User findById(Long id) throws UserNotFoundException {
       return userRepository
                        .findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User does not exist in database"));
    }

    @Override
    public User createUser(User user) throws NotAllowedUserException {
        if (!user.getCountry().matches("France")) {
            throw new NotAllowedUserException("This user is not from France");
        }
        return userRepository.save(user);
    }
}

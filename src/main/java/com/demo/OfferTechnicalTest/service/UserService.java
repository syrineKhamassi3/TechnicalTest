package com.demo.OfferTechnicalTest.service;

import com.demo.OfferTechnicalTest.exception.NotAllowedUserException;
import com.demo.OfferTechnicalTest.exception.UserNotFoundException;
import com.demo.OfferTechnicalTest.model.User;

public interface UserService {

    public User findById(Long id) throws UserNotFoundException;

    public User createUser(User user) throws NotAllowedUserException;

}

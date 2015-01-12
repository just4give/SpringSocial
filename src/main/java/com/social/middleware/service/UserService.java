package com.social.middleware.service;

import com.social.middleware.dto.RegistrationForm;
import com.social.middleware.exception.DuplicateEmailException;
import com.social.web.model.User;

/**
 * Created by mithundas on 11/30/14.
 */
public interface UserService {
    /**
     * Creates a new user account to the service.
     * @param userAccountData   The information of the created user account.
     * @return  The information of the created user account.
     * @throws DuplicateEmailException Thrown when the email address is found from the database.
     */
    public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
}

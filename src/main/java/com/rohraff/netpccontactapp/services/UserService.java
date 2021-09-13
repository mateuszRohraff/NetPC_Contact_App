package com.rohraff.netpccontactapp.services;

import com.rohraff.netpccontactapp.mapper.UserMapper;
import com.rohraff.netpccontactapp.model.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    //Metoda sprawdza, czy użytkownik jest zautoryzowany. Na jej podstawie generowny jest widok aplikacji.
    public boolean checkAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }


    public Optional<String> checkPasswordComplexity(String password) {
        if(password.length() < 8) {
            return Optional.of("Password must be longer than 8 characters!");
        }
        List<Character> chars = new ArrayList<>();
        for (char ch : password.toCharArray()) {
            if(Character.isDigit(ch)) {
                chars.add(ch);
            }
        }
        if(chars.isEmpty()){
            return Optional.of("The password must contain at least one digit!");
        }
        return Optional.empty();
    }
}


package com.teksystems.users.restfulusersexercise.Services;

import com.teksystems.users.restfulusersexercise.Model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void createUser(User user);

    User getUserByEmail(String email);

    User getUserById(Long id);

    void deleteUser(Long id);

    void updateUser(User user,Long id);
}

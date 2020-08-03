package com.teksystems.users.restfulusersexercise.Services;

import com.teksystems.users.restfulusersexercise.Model.User;
import com.teksystems.users.restfulusersexercise.Shared.Dto.UserDto;

import java.util.List;

public interface UserService {
    List<User> getUsers(int page, int limit);

    UserDto createUser(UserDto userDto);

    User getUserByEmail(String email);

    User getUserById(Long id);

    void deleteUser(Long id);

    void updateUser(User user,Long id);
}

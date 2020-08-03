package com.teksystems.users.restfulusersexercise.Services.Implementations;

import com.teksystems.users.restfulusersexercise.Model.User;
import com.teksystems.users.restfulusersexercise.Services.UserService;
import com.teksystems.users.restfulusersexercise.Shared.Dto.UserDto;
import com.teksystems.users.restfulusersexercise.dao.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getUsers(int page, int limit) {
        List<User> returnValue;

        if(page>0) page--;
        Pageable pageableRequest = PageRequest.of(page,limit);
        Page<User> userPage = userRepository.findAll(pageableRequest);
        returnValue = userPage.getContent();

        return returnValue;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User newUser = new User();
        BeanUtils.copyProperties(userDto, newUser);

        User storedUserDetails = userRepository.save(newUser);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

//    @Override
//    public void createUser(User user) {
//        userRepository.save(user);
//    }

    @Override
    public User getUserByEmail(String email) {
        User returnValue = userRepository.findByEmail(email);
        return returnValue;
    }

    @Override
    public User getUserById(Long id) {
        User returnValue;
        if(userRepository.findById(id).isPresent()){
            returnValue = userRepository.findById(id).get();
        return returnValue;
        }
        throw new NullPointerException("User not found");
    }

    @Override
    public void updateUser(User user,Long id) {
        Optional<User> oldUser = userRepository.findById(id);

        if(!oldUser.isPresent()){
            throw new NullPointerException("User not found");
        }
        oldUser.get().setPassword(user.getPassword());
        oldUser.get().setLastName(user.getLastName());
        oldUser.get().setFirstName(user.getFirstName());
        oldUser.get().setEmail(user.getEmail());
        userRepository.save(oldUser.get());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

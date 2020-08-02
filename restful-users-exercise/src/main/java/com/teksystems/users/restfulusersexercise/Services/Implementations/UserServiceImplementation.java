package com.teksystems.users.restfulusersexercise.Services.Implementations;

import com.teksystems.users.restfulusersexercise.Model.User;
import com.teksystems.users.restfulusersexercise.Services.UserService;
import com.teksystems.users.restfulusersexercise.dao.UserRepository;
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
    public List<User> getUsers() {
        List<User> returnValue = new ArrayList<User>();
        returnValue = (List<User>) userRepository.findAll();
        return returnValue;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

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

package com.teksystems.users.restfulusersexercise.Controller;

import com.teksystems.users.restfulusersexercise.Model.User;
import com.teksystems.users.restfulusersexercise.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        List<User> returnValue = userService.getUsers();
        return returnValue;
    }

    @GetMapping(path = "/email/{email}")
    public User getUser(@PathVariable String email){
        User returnValue = userService.getUserByEmail(email);
        return returnValue;
    }

    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable Long id){
        User returnValue = userService.getUserById(id);
        return returnValue;
    }



    @PostMapping
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }


    @PutMapping(path = "/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id){
        userService.updateUser(user,id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}

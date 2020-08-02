package com.teksystems.users.restfulusersexercise.dao;

import com.teksystems.users.restfulusersexercise.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {
    User findByEmail(String email);
//    User findByid(Long id);
}

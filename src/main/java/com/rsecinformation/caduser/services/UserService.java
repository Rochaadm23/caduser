package com.rsecinformation.caduser.services;

import com.rsecinformation.caduser.entities.User;
import com.rsecinformation.caduser.repositories.UserRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepositorie userRepositorie;

    public List<User> findAll(){
        return userRepositorie.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = userRepositorie.findById(id);
        return obj.get();
    }

    public User insert(User obj){
        return userRepositorie.save(obj);
    }

    public void delete(Long id){
        userRepositorie.deleteById(id);
    }

    public User update(Long id, User obj){
        User entity = userRepositorie.getOne(id);
        updateData(entity, obj);
        return userRepositorie.save(entity);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}

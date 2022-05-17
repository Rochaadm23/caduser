package com.rsecinformation.caduser.services;

import com.rsecinformation.caduser.dto.UserDTO;
import com.rsecinformation.caduser.entities.User;
import com.rsecinformation.caduser.repositories.UserRepositorie;
import com.rsecinformation.caduser.services.exceptions.DatabaseException;
import com.rsecinformation.caduser.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepositorie userRepositorie;

    public List<User> findAll() {
        return userRepositorie.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = userRepositorie.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj) {
        return userRepositorie.save(obj);
    }

    public void delete(Long id) {
        try {
            userRepositorie.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj) {
        try {
            User entity = userRepositorie.getById(id);
            updateData(entity, obj);
            return userRepositorie.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }

    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail(), objDto.getPhone(), objDto.getPassword());
    }
}

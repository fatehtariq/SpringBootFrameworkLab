package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User get(int id) {
        return userRepository.findById(id).get();
    }

    public User save(User userRequest) {
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        user = userRepository.save(user);

        userRepository.flush();
        return user;
    }

    public List<User> all() {
        return userRepository.findAll();
    }

    public User update(int id, User updatedUser) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
            if(updatedUser.getName() != null)
                user.setName(updatedUser.getName());
            if(updatedUser.getAge() != -1)
                user.setAge(updatedUser.getAge());
            userRepository.flush();
            return user;
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    public User delete(int id) {
        User user = userRepository.findById(id).orElse(null);
        userRepository.deleteById(id);
        return user;
    }

    public List<User> getByAge(int age) {
        return userRepository.findAllByAge(age);
    }

    public List<User> getByName(String name) {
        return userRepository.findAllByName(name);
    }
}
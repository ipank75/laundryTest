package com.irpan.projek.laundry.service;

import com.irpan.projek.laundry.entity.User;
import com.irpan.projek.laundry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll(Pageable pageable) {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.find(id);
    }
    public User updateById(Integer id,  User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public User create(User user) {
        user.setIdActive(true);
        return userRepository.save(user);
    }
    public User delete(Integer id) {
        return userRepository.delete(id);
    }
}

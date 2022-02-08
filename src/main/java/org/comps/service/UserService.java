package org.comps.service;

import org.comps.model.User;
import org.comps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }

    public User findById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    public List<User> findUsersByClassId(String classId) {
        return userRepository.findUsersByClassId(classId);
    }
}

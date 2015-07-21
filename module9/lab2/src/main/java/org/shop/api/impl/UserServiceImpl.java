package org.shop.api.impl;

import org.shop.api.UserService;
import org.shop.data.User;
import org.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    /* (non-Javadoc)
     * @see org.shop.api.UserService#registerUser(org.shop.data.User)
     */
    @Override
    public Long registerUser(User user) {
        return repository.createUser(user);
    }

    /* (non-Javadoc)
     * @see org.shop.api.UserService#getUserById(java.lang.Long)
     */
    @Override
    public User getUserById(Long userId) {
        return repository.getUserById(userId);
    }

    /* (non-Javadoc)
     * @see org.shop.api.UserService#updateUserProfile(org.shop.data.User)
     */
    @Override
    public void updateUserProfile(User user) {
        repository.updateUser(user);
    }

    /* (non-Javadoc)
     * @see org.shop.api.UserService#getUsers()
     */
    @Override
    public List<User> getUsers() {
        return repository.getUsers();
    }

    //+ UserService: использовать метод populate
    @Autowired
    public void populate(UserRepository repository) {
        this.repository = repository;
    }
}

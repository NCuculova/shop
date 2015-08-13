package mk.ukim.finki.emk.shop.service.impl;

import mk.ukim.finki.emk.shop.model.User;
import mk.ukim.finki.emk.shop.repository.UserRepository;
import mk.ukim.finki.emk.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nadica-PC on 6/4/2015.
 */
@Service
public class UserServiceImpl extends BaseEntityCrudServiceImpl<User,UserRepository> implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    protected UserRepository getRepository() {
        return repository;
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}

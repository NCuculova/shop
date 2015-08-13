package mk.ukim.finki.emk.shop.web;

import mk.ukim.finki.emk.shop.model.Role;
import mk.ukim.finki.emk.shop.model.User;
import mk.ukim.finki.emk.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by Nadica-PC on 6/4/2015.
 */

@RestController
@RequestMapping("/api/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public User create(@RequestBody @Valid User entity,
                       HttpServletRequest request, HttpServletResponse response) {
            User user = userService.findByEmail(entity.getEmail());
        if(user != null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return  null;
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setRole(Role.USER);
        userService.save(entity);
        return entity;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<User> getAll() {
        List<User> users = userService
                .findAll();
        return users;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public User getUser(@PathVariable Long id,
                        HttpServletResponse response) {
        User user = userService.findOne(id);
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return user;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable Long id, HttpServletRequest request,
                       HttpServletResponse response) {
        userService.delete(id);
    }
}

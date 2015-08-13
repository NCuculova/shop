package mk.ukim.finki.emk.shop.web;

import mk.ukim.finki.emk.shop.model.User;
import mk.ukim.finki.emk.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Nadica-PC on 8/10/2015.
 */
@RestController
public class Authenticate {

    @Autowired
    UserService userService;

    @RequestMapping("/authenticated")
    public Principal authenticateUser(Principal user) {
        return user;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    public User getSignedUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername());
        return user;
    }

}

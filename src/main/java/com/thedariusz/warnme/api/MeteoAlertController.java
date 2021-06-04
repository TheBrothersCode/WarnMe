package com.thedariusz.warnme.api;

import com.thedariusz.warnme.twitter.TweetService;
import com.thedariusz.warnme.user.User;
import com.thedariusz.warnme.user.UserDto;
import com.thedariusz.warnme.user.UserService;
import com.thedariusz.warnme.user.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//@RestController
@Controller
@RequestMapping(value = "/alerts")
public class MeteoAlertController {

    private final TweetService tweetService;
    private final UserDao dao;
    private final UserService userService;

    @Autowired
    public MeteoAlertController(TweetService tweetService, UserDao dao, UserService userService) {
        this.tweetService = tweetService;
        this.dao = dao;
        this.userService = userService;
    }

    @PostMapping("/{id}")
    @ResponseBody
    public void fetchAllAlerts(@PathVariable("id") String twitterUserId) {
        tweetService.syncTweets(twitterUserId);
    }

    @GetMapping
    public String getMainView() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginView() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        return "redirect:/alerts";
    }

    @GetMapping("/logout")
    public String getLogoutView() {
        return "logout";
    }

    @GetMapping("/twitter")
    public String getTwitterView() {
        return "twitter";
    }

    @GetMapping("/error")
    public String getErrorView() {
        return "error";
    }

//    @GetMapping("/register")
//    public String getRegisterView(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "register";
//    }

    @GetMapping("/register")
    public String getRegisterView(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "register";
    }
    @PostMapping("/register")
    public String getRegisterForm(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
        String message = userService.validateUsername(userDto); //todo change to pro validation
        if (!message.isBlank()) {
            bindingResult.rejectValue("username", "error.user",
                    message);
            return "register";
        }

        message = userService.validatePassword(userDto); //todo change to pro validation
        if (!message.isBlank()) {
            bindingResult.rejectValue("password", "error.password",
                    message);
            return "register";
        }

        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            userService.saveUser(userDto);
            return "login";
        }
    }

//    @PostMapping("/register")
//    public String getRegisterForm(@Valid User user, BindingResult bindingResult, Model model) {
//        User existUser = dao.findByUserName(user.getUsername());
//        if (existUser!=null) {
//            bindingResult.rejectValue("username", "error.user",
//                    "There is already a user registered with the username provided");
//        }
//
//        if (user.getUsername()==null || user.getUsername().isBlank()) {
//            bindingResult.rejectValue("username", "error.user",
//                    "Username cant be empty!");
//        }
//
//        if (user.getPassword()==null || user.getPassword().isBlank()) {
//            bindingResult.rejectValue("password", "error.password",
//                    "Password cant be empty!");
//        }
//
//
//        if (bindingResult.hasErrors()) {
//            return "/register";
//        } else {
//            dao.saveUser(user);
//            model.addAttribute("user", new User());
//            return "/login";
//        }
//    }

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        dao.saveUser(user);
        return "admin";
    }

}

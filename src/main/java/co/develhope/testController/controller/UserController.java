package co.develhope.testController.controller;

import co.develhope.testController.entities.User;
import co.develhope.testController.repositories.UserRepository;
import co.develhope.testController.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    public @ResponseBody User create(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id){
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()){
        return user.get();
    }else{
        return null;
    } // return userRepository.findById(id).orElse(null);
    }


    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
       return userRepository.save(user);
    }

    @PutMapping("/{id}/activation")
    public void setUserActivation(@PathVariable Long id, @RequestParam("activated") boolean activated){
        userService.setUserActivationStatus(id,activated);

    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

}
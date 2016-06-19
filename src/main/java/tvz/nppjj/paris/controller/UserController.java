package tvz.nppjj.paris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tvz.nppjj.paris.model.User;
import tvz.nppjj.paris.model.dto.UserDto;
import tvz.nppjj.paris.model.mapper.UserMapper;
import tvz.nppjj.paris.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDto> getAllUsers() {
        return UserMapper.transformUserListToDtoList(userService.getAllUsers());
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @CrossOrigin(origins = "http://localhost:8100")
    public String saveUser(UserDto userDto) {
        // userService.registerUser(UserMapper.transformToUser(userDto));
        return "Saved";
    }

}

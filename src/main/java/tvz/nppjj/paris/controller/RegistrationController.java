package tvz.nppjj.paris.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tvz.nppjj.paris.model.dto.RegistrationDto;
import tvz.nppjj.paris.model.mapper.UserMapper;
import tvz.nppjj.paris.service.UserService;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerUser(@Valid @RequestBody RegistrationDto registrationDto) {
        // if registrationDto fails validation or exception is thrown from
        // service or DB, client will get ajax error response
        userService.registerUser(UserMapper.transformToUser(registrationDto));

        // TODO: log possible exceptions
    }

}

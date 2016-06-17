package tvz.nppjj.paris.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tvz.nppjj.paris.model.dto.RegistrationCommand;
import tvz.nppjj.paris.service.UserService;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerUser(@Valid @RequestBody RegistrationCommand registrationCommand) {
        // if registrationDto fails validation or exception is thrown from
        // service or DB, client will get ajax error response
        userService.registerUser(registrationCommand);

        // TODO: log possible exceptions
    }

}

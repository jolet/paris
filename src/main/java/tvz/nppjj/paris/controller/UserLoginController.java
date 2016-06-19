package tvz.nppjj.paris.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tvz.nppjj.paris.command.UserLoginCommand;
import tvz.nppjj.paris.init.config.filter.JwtSignature;
import tvz.nppjj.paris.model.dto.LoginResponseDto;
import tvz.nppjj.paris.model.dto.UserDto;
import tvz.nppjj.paris.service.UserService;

@RestController
public class UserLoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponseDto authenticateUser(@Valid @RequestBody UserLoginCommand userLoginCommand) {
        UserDto userDto = userService.loginUser(userLoginCommand.getUsername(), userLoginCommand.getPassword());

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setUserDto(userDto);
        loginResponseDto.setToken(JwtSignature.createJwtToken(userDto));
        return loginResponseDto;

    }

    // TODO: remove after jwt integration
    @RequestMapping(value = "/jwt", method = RequestMethod.POST)
    public String jwtTest() {
        return "jwt request OK";
    }

}

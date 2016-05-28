package tvz.nppjj.paris.model.mapper;

import tvz.nppjj.paris.model.User;
import tvz.nppjj.paris.model.dto.RegistrationDto;

public class UserMapper {

    public static User transformToUser(RegistrationDto registrationDto) {

        User newUser = new User();
        newUser.setEmail(registrationDto.getEmail());
        newUser.setUsername(registrationDto.getEmail());
        newUser.setPassword(registrationDto.getPassword());
        newUser.setPhone_number(registrationDto.getPhoneNumber());
        return newUser;
    }
}

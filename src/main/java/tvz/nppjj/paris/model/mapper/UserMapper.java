package tvz.nppjj.paris.model.mapper;

import java.util.ArrayList;
import java.util.List;

import tvz.nppjj.paris.model.User;
import tvz.nppjj.paris.model.dto.RegistrationDto;
import tvz.nppjj.paris.model.dto.UserDto;

public class UserMapper {

    public static User transformToUser(RegistrationDto registrationDto) {

        User newUser = new User();
        newUser.setEmail(registrationDto.getEmail());
        newUser.setUsername(registrationDto.getEmail());
        newUser.setPassword(registrationDto.getPassword());
        newUser.setPhone_number(registrationDto.getPhoneNumber());
        return newUser;
    }
     public static UserDto transformUserToUserDto(User user) {

       UserDto userDto=new UserDto();
       userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getEmail());
        userDto.setPhoneNumber(user.getPhone_number());
        userDto.setRole(user.getRole().getName());
        return userDto;
    }
     
     public static List<UserDto> transformUserListToDtoList(List<User> userList){
    	 List<UserDto> userDtoList=new ArrayList<>();
    	 for (int i=0;i<userList.size();i++) {
    		 userDtoList.add(transformUserToUserDto(userList.get(i)));
		}
    	 return userDtoList;
    	 
     }
}

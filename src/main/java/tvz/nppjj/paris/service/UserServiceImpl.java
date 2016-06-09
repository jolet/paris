package tvz.nppjj.paris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tvz.nppjj.paris.model.User;
import tvz.nppjj.paris.model.dto.RegistrationCommand;
import tvz.nppjj.paris.model.dto.UserDto;
import tvz.nppjj.paris.model.enums.RoleType;
import tvz.nppjj.paris.model.exception.ParisException;
import tvz.nppjj.paris.model.mapper.UserMapper;
import tvz.nppjj.paris.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.saveAndFlush(user);

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User registerUser(RegistrationCommand registrationCommand) {
        User user = UserMapper.transformToUser(registrationCommand);
        // Check if user already exists, throw exception if it does.
        // Exception will be propagated back to calling controller and returned
        // through ajax error response
        User checkUser = userRepository.findByEmail(user.getEmail());
        if (checkUser != null) {
            throw new ParisException("User with email " + user.getEmail() + " already exists!");
        }

        // add default user role for now
        user.setRole(roleService.findByRoleType(RoleType.USER));
        return userRepository.save(user);

        // TODO: send email notification...
    }

    @Override
    public UserDto loginUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new ParisException("That user do not exist!");
        }
        return UserMapper.transformUserToUserDto(user);
    }

}

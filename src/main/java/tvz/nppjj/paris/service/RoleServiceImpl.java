package tvz.nppjj.paris.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tvz.nppjj.paris.model.Role;
import tvz.nppjj.paris.model.enums.RoleType;
import tvz.nppjj.paris.repository.RoleRepository;

/**
 * 
 * Role Service implementation
 * 
 * @author josip.kovacek
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRoleType(RoleType roleType) {
        return roleRepository.findByName(roleType.getRoleName());
    }
}

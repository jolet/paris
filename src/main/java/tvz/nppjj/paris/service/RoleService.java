package tvz.nppjj.paris.service;

import tvz.nppjj.paris.model.Role;
import tvz.nppjj.paris.model.enums.RoleType;

public interface RoleService {

    Role findByRoleType(RoleType role);

}

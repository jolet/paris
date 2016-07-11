package tvz.nppjj.paris.model.enums;

public enum RoleType {

    ADMINISTRATOR("Administrator"), ORGANISATOR("Organisator"), USER("User");

    private String roleName;

    private RoleType(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

}

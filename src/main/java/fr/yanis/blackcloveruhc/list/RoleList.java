package fr.yanis.blackcloveruhc.list;

import fr.yanis.blackcloveruhc.roles.Asta;
import fr.yanis.blackcloveruhc.roles.Role;
import fr.yanis.blackcloveruhc.roles.Yuno;

public enum RoleList {

    ASTA("Asta", CampList.ROYAUME, new Asta()),
    YUNO("Yuno", CampList.ROYAUME, new Yuno());

    private String name;
    private CampList camp;
    private Role role;

    RoleList(String name, CampList camp, Role role) {
        this.name = name;
        this.camp = camp;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public CampList getCamp() {
        return camp;
    }

    public Role getInstance() {
        return role;
    }
}

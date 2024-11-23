package com.desolatetimelines.acct.security.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "group_privilege")
public class JpaAcctGroupPrivilege implements AcctGroupPrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupPrivilegeId;

    @Column(name = "group_uuid")
    private String groupUUID;

    @Column(name = "privilege_name")
    private String privilegeName;

    public JpaAcctGroupPrivilege() {
    }

    public JpaAcctGroupPrivilege(String groupUUID, String privilegeName) {
        this.groupUUID = groupUUID;
        this.privilegeName = privilegeName;
    }

    @Override
    public String getGroupUUID() {
        return groupUUID;
    }

    @Override
    public void setGroupUUID(String groupUUID) {
        this.groupUUID = groupUUID;
    }

    @Override
    public String getPrivilegeName() {
        return privilegeName;
    }

    @Override
    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctGroupPrivilege that = (JpaAcctGroupPrivilege) o;
        return Objects.equals(groupUUID, that.groupUUID) && Objects.equals(privilegeName, that.privilegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupUUID, privilegeName);
    }
}

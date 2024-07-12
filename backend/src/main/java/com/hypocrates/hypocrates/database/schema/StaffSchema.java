package com.hypocrates.hypocrates.database.schema;


import com.hypocrates.hypocrates.core.domain.staff.AppRule;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.digester.Rule;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SecondaryTable(name = "AppUser", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "UserContact", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "UserSecurity", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class StaffSchema extends BaseSchema {
    @Column(table = "AppUser", name = "firstname")
    private String firstname;

    @Column(table = "AppUser", name = "lastname")
    private String lastname;

    @Column(table = "AppUser", name = "patronymic")
    private String patronymic;

    @Column(table = "AppUser", name = "birthday")
    private Date birthday;

    @Column(table = "AppUser", name = "avatar_url")
    private String avatarUrl;

    @Column(table = "UserContact", name = "email")
    private String email;

    @Column(table = "UserContact", name = "emailIsActive")
    private boolean emailIsActive;

    @Column(table = "UserContact", name = "phone")
    private String phone;

    @Column(table = "UserContact", name = "phoneIsActive")
    private boolean phoneIsActive;

    @Column(table = "UserSecurity", name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private StaffRoleSchema role;

    public Set<AppRule> getRules() {
        return role.getRules();
    }

}

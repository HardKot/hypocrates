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
@Table(name = "staff_schema")
@SecondaryTable(name = "App_User", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "User_Contact", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "User_Security", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class StaffSchema extends BaseSchema {
    @Column(table = "app_user", name = "firstname")
    private String firstname;

    @Column(table = "app_user", name = "lastname")
    private String lastname;

    @Column(table = "app_user", name = "patronymic")
    private String patronymic;

    @Column(table = "app_user", name = "birthday")
    private Date birthday;

    @Column(table = "app_user", name = "avatar_url")
    private String avatarUrl;

    @Column(table = "user_contact", name = "email")
    private String email;

    @Column(table = "user_contact", name = "email_is_active")
    private boolean emailIsActive;

    @Column(table = "user_contact", name = "phone")
    private String phone;

    @Column(table = "user_contact", name = "phone_is_active")
    private boolean phoneIsActive;

    @Column(table = "user_security", name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private StaffRoleSchema role;

    public Set<AppRule> getRules() {
        return role.getRules();
    }

}

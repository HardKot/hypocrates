package com.hypocrates.hypocrates.database.schema;


import com.hypocrates.hypocrates.core.domain.staff.AppRule;
import jakarta.persistence.*;
import lombok.*;
import org.apache.tomcat.util.digester.Rule;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "staff_schema")
public class StaffSchema extends BaseSchema {
    private String firstname;

    private String lastname;

    private String patronymic;

    private Date birthday;

    @Column(name = "avatar_url")
    private String avatarUrl;

    private String email;

    @Column(name = "email_is_active")
    private boolean emailIsActive;

    private String phone;

    @Column(name = "phone_is_active")
    private boolean phoneIsActive;

    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private StaffRoleSchema role;

    public Set<AppRule> getRules() {
        if (role == null) {
            return Set.of();
        }
        return role.getRules();
    }

}

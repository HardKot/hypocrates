package com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hypocrates.hypocrates.entity.staff.AppRule;
import com.hypocrates.hypocrates.infrastructure.config.database.BaseSchema;
import com.hypocrates.hypocrates.infrastructure.config.database.admin.schema.ClinicSchema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity(name ="Staff")
@NoArgsConstructor
public class StaffSchema extends BaseSchema {
    private String firstname;
    private String lastname;
    private String patronymic;
    private Date birthday;
    @Column(name = "avatar_url")
    private String avatarUrl;

    private String email;
    private String phone;

    @Column(name = "email_is_active")
    private Boolean emailIsActive;
    @Column(name = "phone_is_active")
    private boolean phoneIsActive;

    @JsonIgnore
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    private StaffRoleSchema role;

    public Set<AppRule> getRules() {
        if (role == null) {
            return Set.of();
        }
        return role.getRules();
    }
}

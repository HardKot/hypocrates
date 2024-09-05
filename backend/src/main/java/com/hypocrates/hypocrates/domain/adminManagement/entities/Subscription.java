package com.hypocrates.hypocrates.domain.adminManagement.entities;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Subscription extends AbstractAdminEntity {
    private String name;
    private int price;
    private Duration duration;

    private boolean isActive;

    private Set<SubscriptionFunctional> functionalSet;

    public enum SubscriptionFunctional {

    }
}

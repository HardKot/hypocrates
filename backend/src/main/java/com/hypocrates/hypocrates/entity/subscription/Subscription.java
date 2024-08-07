package com.hypocrates.hypocrates.entity.subscription;

import com.hypocrates.hypocrates.entity.AbstractEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.util.Set;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Subscription extends AbstractEntity {
    private String name;
    private int price;
    private Duration duration;

    private boolean isActive;

    private Set<SubscriptionFunctional> functionalSet;
}

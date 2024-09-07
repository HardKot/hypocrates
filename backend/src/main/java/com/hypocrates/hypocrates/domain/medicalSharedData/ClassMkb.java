package com.hypocrates.hypocrates.domain.medicalSharedData;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ClassMkb")
@Table(name = "class_mkb")
public class ClassMkb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @NotNull
    @Column(name = "code", nullable = false, length = Integer.MAX_VALUE)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ClassMkb parent;

    @Column(name = "parent_code", length = Integer.MAX_VALUE)
    private String parentCode;

    @NotNull
    @Column(name = "node_count", nullable = false)
    private Short nodeCount;

    @Column(name = "additional_info", length = Integer.MAX_VALUE)
    private String additionalInfo;

    @OneToMany(mappedBy = "parent")
    private Set<ClassMkb> classMkbs = new LinkedHashSet<>();

}
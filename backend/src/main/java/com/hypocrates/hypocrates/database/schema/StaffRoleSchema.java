package com.hypocrates.hypocrates.database.schema;


import com.hypocrates.hypocrates.domain.AppRule;
import com.hypocrates.hypocrates.dto.StaffRoleDTO;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class StaffRoleSchema extends BaseSchema {
    private String name;

    @ElementCollection(targetClass = AppRule.class)
    @Enumerated(EnumType.STRING)
    private AppRule[] rules;


    static StaffRoleSchema dtoToSchema(StaffRoleDTO dto) {
        StaffRoleSchema schema = new StaffRoleSchema();
        schema.setId(dto.getId());
        schema.setName(dto.getName());
        schema.setRules(dto.getRules());
        return schema;
    }
}

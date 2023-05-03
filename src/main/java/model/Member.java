package model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
// @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class Member extends Customer {
    private String name, phone;
    private int point, transactions;

    @Override
    public void polymorphTest() {
        System.out.println("from Member");
    }
}

package model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property="__typename", defaultImpl = Member.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value=VIP.class, name = "VIP"),
})
public class Member extends Customer {
    private String name, phone;
    private int point, transactions;

    @Override
    public void polymorphTest() {
        System.out.println("from Member");
    }
}

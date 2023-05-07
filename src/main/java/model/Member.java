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
    private static final long serialVersionUID = 8L;
    private String name, phone;
    private int point, transactions;
    private boolean active;
    private static int maxMemID = 0;

    @Override
    public void polymorphTest() {
        System.out.println("from Member");
    }

    public static void resetMaxMemID(int start) {
        maxMemID = start;
    }

    public static int checkMaxMemID() { return maxMemID; }
}

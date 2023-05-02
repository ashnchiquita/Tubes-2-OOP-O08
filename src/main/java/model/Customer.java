package model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@JsonTypeInfo(use= JsonTypeInfo.Id.CLASS, property="@class")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value=Member.class, name = "Member"),
//        @JsonSubTypes.Type(value=VIP.class, name = "VIP"),
//})

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;

    public void polymorphTest() {
        System.out.println("from Customer");
    }
}
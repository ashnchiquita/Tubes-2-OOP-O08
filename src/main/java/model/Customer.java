package model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property="__typename", defaultImpl = Customer.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value=Member.class, name = "Member"),
})

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int count = 0;
    public int id;

    public void polymorphTest() {
        System.out.println("from Customer");
    }

    public static void resetCount(int start) { count = start; }
    public static int checkCount() { return count; }

    public abstract static class CustomerBuilder< C extends Customer, B extends Customer.CustomerBuilder<C,B> > {
        private B id(int b) {
            System.out.println("babi");
            this.id = ++count;
            return self();
        }

        public B id() {
            return id(0);
        }
    }
}

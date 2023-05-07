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
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property="__typename", defaultImpl = VIP.class)
public class VIP extends Member {
    private static final long serialVersionUID = 10L;
    private static int maxVIPID = 0;
    @Override
    public void polymorphTest() {
        System.out.println("from VIP");
    }
    public static void resetMaxVIPID(int start) {
        maxVIPID = start;
    }
    public static int checkMaxVIPID() { return maxVIPID; }
}
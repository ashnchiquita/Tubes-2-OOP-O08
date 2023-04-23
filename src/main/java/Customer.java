package member;

public class Customer {
    private static int customerId = 0;

    public Customer() {
        customerId++;
    }

    public int getCustomerId() {
        return customerId;
    }
}

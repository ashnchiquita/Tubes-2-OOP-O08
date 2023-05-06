package controller.customer;

import model.Customer;
import model.Member;

import java.util.List;

public interface CustomerIO {
    public Customer getByID(int id);

    public List<Customer> getAllCustomer();

    public boolean insertCustomer(Customer data);

    public boolean updateCustomer(Customer newData);

    public boolean deleteCustomer(int id);
}

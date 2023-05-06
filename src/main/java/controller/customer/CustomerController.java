package controller.customer;

import lombok.Getter;
import lombok.Setter;
import model.Customer;

import java.util.List;

@Setter @Getter
public class CustomerController {
    CustomerIO dataIO;

    public Customer getByID(int id) { return dataIO.getByID(id); }

    public List<Customer> getAllCustomer()  { return dataIO.getAllCustomer(); }

    public boolean insertCustomer(Customer data) { return dataIO.insertCustomer(data); }

    public boolean updateCustomer(Customer newData) { return dataIO.updateCustomer(newData); }

    public boolean deleteCustomer(int id) { return dataIO.deleteCustomer(id); }

    // tambahin sesuai kebutuhan
}

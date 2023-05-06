package controller.customer;

import lombok.Getter;
import lombok.Setter;
import model.Customer;
import controller.*;

import java.util.List;

@Setter
@Getter
public class CustomerController implements GenericController<Customer> {
    CustomerIO dataIO;

    public Customer getByID(int id) {
        return dataIO.getByID(id);
    }

    public List<Customer> getAll() {
        return dataIO.getAllCustomer();
    }

    public boolean insert(Customer data) {
        return dataIO.insertCustomer(data);
    }

    public boolean update(Customer newData) {
        return dataIO.updateCustomer(newData);
    }

    public boolean delete(int id) {
        return dataIO.deleteCustomer(id);
    }

    // tambahin sesuai kebutuhan
}

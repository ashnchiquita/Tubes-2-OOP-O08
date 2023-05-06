package testing;

import controller.barang.BarangAdapterOBJ;
import controller.customer.CustomerAdapterJSON;
import controller.customer.CustomerAdapterOBJ;
import controller.customer.CustomerAdapterXML;
import controller.customer.CustomerController;
import model.Customer;

import java.util.Objects;

public class TesCustomer {
    public static void main(String[] args) {
        CustomerController dataIO = new CustomerController();
        // dataIO.setDataIO(new CustomerAdapterJSON("src/main/resources/data/tes_customer.json"));
        // dataIO.setDataIO(new CustomerAdapterXML("src/main/resources/data/tes_customer.xml"));
        dataIO.setDataIO(new CustomerAdapterOBJ("src/main/resources/data/tes_customer"));

        Customer c = Customer.builder().id(1).build();
        Customer c2 = Customer.builder().id(3).build();

        dataIO.insertCustomer(c);
        dataIO.insertCustomer(c2);

        System.out.println(dataIO.getByID(1));
        Objects.requireNonNull(dataIO.getAllCustomer()).forEach(Customer::polymorphTest);

        Objects.requireNonNull(dataIO.getAllCustomer()).forEach(System.out::println);
        dataIO.deleteCustomer(1);
    }
}

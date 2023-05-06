package testing;

import controller.MainController;
import controller.barang.BarangAdapterOBJ;
import controller.customer.CustomerAdapterJSON;
import controller.customer.CustomerAdapterOBJ;
import controller.customer.CustomerAdapterXML;
import model.Customer;

import java.util.Objects;

public class TesCustomer {
        public static void main(String[] args) {
                MainController controller = new MainController();
                // dataIO.setDataIO(new
                // CustomerAdapterJSON("src/main/resources/data/tes_customer.json"));
                controller.setCustomerDataIO(new CustomerAdapterXML("src/main/resources/data/tes_customer.xml"));
                // controller.setCustomerDataIO(new
                // CustomerAdapterOBJ("src/main/resources/data/tes_customer"));

                Customer c = Customer.builder().id().build();
                Customer c2 = Customer.builder().id().build();

                controller.getCustomerDataIO().insert(c);
                controller.getCustomerDataIO().insert(c2);

                System.out.println(controller.getCustomerDataIO().getByID(1));
                Objects.requireNonNull(controller.getCustomerDataIO().getAll()).forEach(Customer::polymorphTest);

                Objects.requireNonNull(controller.getCustomerDataIO().getAll()).forEach(System.out::println);
                controller.getCustomerDataIO().delete(1);
        }
}

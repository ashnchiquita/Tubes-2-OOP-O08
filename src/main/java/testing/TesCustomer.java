package testing;

import controller.MainController;
// import controller.barang.*;
import controller.customer.*;
// import controller.member.*;
import model.Customer;

import java.util.Objects;

public class TesCustomer {
  public static void main(String[] args) {
    MainController controller = new MainController();
    controller.setCustomerDataIO(new CustomerAdapterJSON("src/main/resources/data/customer.json"));
    // dataIO.setDataIO(new
    // CustomerAdapterJSON("src/main/resources/data/tes_customer.json"));
    // controller.setCustomerDataIO(new
    // CustomerAdapterXML("src/main/resources/data/tes_customer.xml"));
    // controller.setCustomerDataIO(new
    // CustomerAdapterOBJ("src/main/resources/data/tes_customer"));
    // controller.setCustomerDataIO(
    // new CustomerAdapterSQL("jdbc:mysql://localhost:3306/testing_oop", "rma1403",
    // ""));

    Customer c = Customer.builder().id().build();
    Customer c2 = Customer.builder().id().build();

    controller.getCustomerDataIO().insert(c);
    controller.getCustomerDataIO().insert(c2);

    System.out.println(controller.getCustomerDataIO().getByID(4));
    Objects.requireNonNull(controller.getCustomerDataIO().getAll()).forEach(Customer::polymorphTest);

    Objects.requireNonNull(controller.getCustomerDataIO().getAll()).forEach(System.out::println);
    // controller.getCustomerDataIO().delete(5);
  }
}

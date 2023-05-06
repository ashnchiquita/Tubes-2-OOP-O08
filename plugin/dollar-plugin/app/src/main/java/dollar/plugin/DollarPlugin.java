package dollar.plugin;

import javax.swing.*;

import controller.*;
import controller.barang.*;

public class DollarPlugin implements SystemPlugin {
  @Override
  public MainController getController(MainController controller) {
    System.out.println("Masuk dollar plugin");
    System.out.println("Baru lagi");

    BarangDecorator barangDecorator = new BarangDecorator();
    barangDecorator.setDataIO(controller.getBarangDataIO());

    MainController newController = new MainController();
    newController.setCustomerDataIO(controller.getCustomerDataIO());
    newController.setFixedBillDataIO(controller.getFixedBillDataIO());
    newController.setMemberDataIO(controller.getMemberDataIO());
    newController.setVIPDataIO(controller.getVIPDataIO());
    newController.setBarangDataIO(barangDecorator);

    return newController;
  }

  @Override
  public JPanel displayPanel(MainController controller) {
    return null;
  }
}

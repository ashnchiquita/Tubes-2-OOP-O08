package dollar.plugin;

import javax.swing.*;

import controller.*;
import controller.barang.*;

public class DollarPlugin implements SystemPlugin {
  @Override
  public MainController getController(MainController controller) {
    BarangDecorator barangDecorator = new BarangDecorator();
    barangDecorator.setDataIO(controller.getBarangDataIO());

    FixedBillDecorator billDecorator = new FixedBillDecorator();
    billDecorator.setDataIO(controller.getFixedBillDataIO());

    MainController newController = new MainController();
    newController.setCustomerDataIO(controller.getCustomerDataIO());
    newController.setMemberDataIO(controller.getMemberDataIO());
    newController.setVIPDataIO(controller.getVIPDataIO());
    newController.setFixedBillDataIO(billDecorator);
    newController.setBarangDataIO(barangDecorator);

    return newController;
  }

  @Override
  public JPanel displayPanel(MainController controller) {
    return null;
  }
}

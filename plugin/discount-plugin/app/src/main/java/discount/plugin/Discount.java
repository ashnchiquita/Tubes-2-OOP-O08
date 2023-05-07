package discount.plugin;

import javax.swing.JPanel;

import controller.*;

public class Discount implements SystemPlugin {
  @Override
  public MainController getController(MainController controller) {
    FixedBillDecorator billDecorator = new FixedBillDecorator();
    billDecorator.setDataIO(controller.getFixedBillDataIO());

    MainController newController = new MainController();
    newController.setCustomerDataIO(controller.getCustomerDataIO());
    newController.setMemberDataIO(controller.getMemberDataIO());
    newController.setVIPDataIO(controller.getVIPDataIO());
    newController.setBarangDataIO(controller.getBarangDataIO());
    newController.setFixedBillDataIO(billDecorator);

    return newController;
  }

  @Override
  public JPanel displayPanel(MainController controller) {
    return null;
  }
}

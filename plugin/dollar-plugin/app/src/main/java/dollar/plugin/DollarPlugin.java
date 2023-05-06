package dollar.plugin;

import javax.swing.*;

import controller.*;
import controller.barang.*;

public class DollarPlugin implements SystemPlugin {
  @Override
  public MainController getController(MainController controller) {
    System.out.println("Masuk dollar plugin");

    MainController newController = new MainController();
    newController.setCustomerCon(controller.getCustomerCon());
    newController.setFixedBillCon(controller.getFixedBillCon());
    newController.setMemberCon(controller.getMemberCon());
    newController.setVipCon(controller.getVipCon());

    return newController;
  }

  @Override
  public JPanel displayPanel(MainController controller) {
    return null;
  }
}

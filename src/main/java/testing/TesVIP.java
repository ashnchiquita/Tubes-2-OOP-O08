package testing;

import controller.MainController;
import controller.vip.VIPAdapterJSON;
import controller.vip.VIPAdapterOBJ;
import controller.vip.VIPAdapterXML;
import model.VIP;

import java.util.Objects;

public class TesVIP {
    public static void main(String[] args) {
        MainController controller = new MainController();
        // dataIO.setDataIO(new VIPAdapterJSON("src/main/resources/data/tes_vip.json"));
        controller.setVIPDataIO(new VIPAdapterXML("src/main/resources/data/tes_vip.xml"));
        // controller.setVIPDataIO(new
        // VIPAdapterOBJ("src/main/resources/data/tes_vip"));

        VIP c = VIP.builder().id(1).point(0).transactions(0).name("chi").phone("123").active(true).build();
        VIP c2 = VIP.builder().id(3).point(1).transactions(2).name("chu").phone("123").active(false).build();

        controller.getVIPDataIO().insert(c);
        System.out.println(controller.getVIPDataIO().getByID(3));
        controller.getVIPDataIO().insert(c2);

        c.setTransactions(10);
        controller.getVIPDataIO().update(c);

        Objects.requireNonNull(controller.getVIPDataIO().getAll()).forEach(VIP::polymorphTest);

        Objects.requireNonNull(controller.getVIPDataIO().getAll()).forEach(System.out::println);
        controller.getVIPDataIO().delete(1);
    }
}

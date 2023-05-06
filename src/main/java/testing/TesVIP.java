package testing;

import controller.vip.VIPAdapterJSON;
import controller.vip.VIPAdapterOBJ;
import controller.vip.VIPAdapterXML;
import controller.vip.VIPController;
import model.VIP;

import java.util.Objects;

public class TesVIP {
    public static void main(String[] args) {
        VIPController dataIO = new VIPController();
        // dataIO.setDataIO(new VIPAdapterJSON("src/main/resources/data/tes_vip.json"));
        dataIO.setDataIO(new VIPAdapterXML("src/main/resources/data/tes_vip.xml"));
        // dataIO.setDataIO(new VIPAdapterOBJ("src/main/resources/data/tes_vip"));

        VIP c = VIP.builder().id().point(0).transactions(0).name("chi").phone("123").active(true).build();
        VIP c2 = VIP.builder().id().point(1).transactions(2).name("chu").phone("123").active(false).build();

        dataIO.insertVIP(c);
        System.out.println(dataIO.getByID(1));
        dataIO.insertVIP(c2);

        c.setTransactions(10);
        dataIO.updateVIP(c);

        Objects.requireNonNull(dataIO.getAllVIP()).forEach(VIP::polymorphTest);

        Objects.requireNonNull(dataIO.getAllVIP()).forEach(System.out::println);
        dataIO.deleteVIP(1);
    }
}

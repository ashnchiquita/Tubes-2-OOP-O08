package controller;

import boundary.panel.settings.Settings;
import controller.barang.*;
import controller.customer.*;
import controller.fixedbill.*;
import controller.member.*;
import controller.vip.*;
import lombok.*;
import model.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;

@Getter
@Setter
public class MainController {
    GenericDataIO<Barang> barangDataIO;
    GenericDataIO<Customer> customerDataIO;
    GenericDataIO<FixedBill> fixedBillDataIO;
    GenericDataIO<Member> memberDataIO;
    GenericDataIO<VIP> VIPDataIO;

    public MainController() {
        String path = "";
        String extension = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(Settings.dataStore.getPath()));

            path = br.readLine();
            extension = br.readLine();
            if(extension.equals("OBJ")){
                // urutannya harus banget gini harus
                extension = "";
                barangDataIO = new BarangAdapterOBJ(path + "/barang" + extension);
                memberDataIO = new MemberAdapterOBJ(path + "" + "/member" + extension);
                VIPDataIO = new VIPAdapterOBJ(path + "" + "/vip" + extension);
                customerDataIO = new CustomerAdapterOBJ(path + "/customer" + extension);
                fixedBillDataIO = new FixedBillAdapterOBJ(path + "/fixed_bill" + extension);
            }
            else if(extension.equals("XML")){
                extension = ".xml";
                barangDataIO = new BarangAdapterXML(path + "/barang" + extension);
                memberDataIO = new MemberAdapterXML(path + "" + "/member" + extension);
                VIPDataIO = new VIPAdapterXML(path + "" + "/vip" + extension);
                customerDataIO = new CustomerAdapterXML(path + "/customer" + extension);
                fixedBillDataIO = new FixedBillAdapterXML(path + "/fixed_bill" + extension);
            }
            else if(extension.equals("JSON")) {
                extension = ".json";
                barangDataIO = new BarangAdapterJSON(path + "/barang" + extension);
                memberDataIO = new MemberAdapterJSON(path + "" + "/member" + extension);
                VIPDataIO = new VIPAdapterJSON(path + "" + "/vip" + extension);
                customerDataIO = new CustomerAdapterJSON(path + "/customer" + extension);
                fixedBillDataIO = new FixedBillAdapterJSON(path + "/fixed_bill" + extension);
            } else if (extension.equals("SQL Raw") && !Settings.usn.equals("") && !Settings.url.equals("")) {
                barangDataIO = new BarangAdapterSQL(Settings.url, Settings.usn, Settings.pw);
                memberDataIO = new MemberAdapterSQL(Settings.url, Settings.usn, Settings.pw);
                VIPDataIO = new VIPAdapterSQL(Settings.url, Settings.usn, Settings.pw);
                customerDataIO = new CustomerAdapterSQL(Settings.url, Settings.usn, Settings.pw);
                fixedBillDataIO = new FixedBillAdapterSQL(Settings.url, Settings.usn, Settings.pw);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Data loading failed\n" + e.toString());
        }

    }
}

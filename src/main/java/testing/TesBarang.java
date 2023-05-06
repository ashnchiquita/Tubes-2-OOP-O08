package testing;

import java.io.*;
import java.net.*;
import java.util.*;

import controller.barang.BarangAdapterOBJ;
import controller.barang.BarangAdapterXML;
import controller.barang.BarangAdapterJSON;
import controller.*;

import model.Barang;

import java.util.Objects;

public class TesBarang {
    public static void main(String[] args) {
        MainController controller = new MainController();
        // dataIO.setDataIO(new
        // BarangAdapterJSON("src/main/resources/data/tes_barang.json"));
        controller.setBarangDataIO(new BarangAdapterXML("src/main/resources/data/tes_barang.xml"));
        // controller.setBarangDataIO(new
        // BarangAdapterOBJ("src/main/resources/data/tes_barang"));

        // try {
        // final URL plugin_1 = new
        // File("./plugin/dollar-plugin/app/build/libs/app.jar").toURI().toURL();
        // ClassLoader ucl = new URLClassLoader(new URL[] { plugin_1 });

        // ServiceLoader<SystemPlugin> pluginLoader =
        // ServiceLoader.load(SystemPlugin.class, ucl);

        // for (final SystemPlugin plugin : pluginLoader) {
        // controller = plugin.getController(controller);
        // }
        // } catch (MalformedURLException e) {
        // System.out.println(e);
        // }

        Barang b1 = Barang.builder().id(1).name("ayam").kategori("a").gambar("hai").hargaJual(3).hargaBeli(3)
                .jumlah(4)
                .build();
        Barang b2 = Barang.builder().id(2).name("ayam").kategori("a").gambar("hai").hargaJual(2).hargaBeli(2)
                .jumlah(5)
                .build();

        controller.getBarangDataIO().insert(b1);
        controller.getBarangDataIO().insert(b2);

        b1.setHargaBeli(10);
        controller.getBarangDataIO().update(b1);

        System.out.println(controller.getBarangDataIO().getByID(1));

        Objects.requireNonNull(controller.getBarangDataIO().getAll()).forEach(System.out::println);
        System.out.println(controller.getBarangDataIO().delete(1));
        Objects.requireNonNull(controller.getBarangDataIO().getAll()).forEach(System.out::println);
    }
}

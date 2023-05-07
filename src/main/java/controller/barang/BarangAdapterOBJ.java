package controller.barang;

import model.Barang;
import controller.*;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BarangAdapterOBJ implements GenericDataIO<Barang> {
    private final String filePath;
    private List<Barang> list = new ArrayList<>();

    public BarangAdapterOBJ(String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            Objects.requireNonNull(getAll(), "Barang list must be a non-null value");
            if (list.size() != 0) {
                Barang.resetCount(list.stream().max(Comparator.comparing(Barang::getId)).get().getId());
            }
        }

        // handle lazy loading
        Barang b = Barang.builder().id().jumlah(10).hargaJual(5).hargaBeli(2).build();
        insert(b);
        delete(b.getId());
        Barang.resetCount(Barang.checkCount() - 1);
    }

    public void write() throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
    }

    public void read() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        list = (List<Barang>) ois.readObject(); // wah unsafe gmn ya biar safe
        ois.close();
    }

    @Override
    @Nullable
    public Barang getByID(int id) {
        List<Barang> filtered = Objects.requireNonNull(getAll(), "Barang list must be a non-null value").stream()
                .filter(fixedBill -> fixedBill.getId() == id).collect(Collectors.toList());
        if (!filtered.isEmpty()) {
            return filtered.get(0);
        } else {
            System.out.println("ID " + id + " tidak ditemukan");
            return null; // Return null artinya ID ga ketemu
        }
    }

    @Override
    @Nullable
    public List<Barang> getAll() {
        try {
            read();
            return list;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null; // Return null artinya terjadi IOException
        }
    }

    @Override
    public boolean insert(Barang data) {
        int pos = -1;

        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i).getName(), data.getName())) {
                pos = i;
                break;
            }
        }

        if (pos != -1) { // add stock
            Barang prevData = list.get(pos).toBuilder().build();
            Barang.resetCount(Barang.checkCount() - 1);
            Barang temp = data.toBuilder().build();
            Barang.resetCount(Barang.checkCount() - 1);
            temp.setJumlah(temp.getJumlah() + prevData.getJumlah());
            temp.setId(list.get(pos).getId());
            try {
                list.set(pos, temp);
                write();
                return true;
            } catch (IOException e) {
                list.set(pos, prevData); // recover
                e.printStackTrace();
                return false;
            }
        } else { // add new barang
            try {
                list.add(data);
                write();
                return true;
            } catch (IOException e) {
                list.remove(data);
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public boolean update(Barang newData) {
        Objects.requireNonNull(getAll(), "Barang list must be a non-null value");

        int pos = -1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == newData.getId()) {
                pos = i;
                break;
            }
        }

        if (pos != -1) {
            Barang prevData = list.get(pos).toBuilder().build();
            Barang.resetCount(Barang.checkCount() - 1);
            try {
                list.set(pos, newData);
                write();
                return true;
            } catch (IOException e) {
                list.set(pos, prevData); // recover
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        Barang data = getByID(id);
        if (data != null) {
            try {
                list.remove(data);
                write();
                return true;
            } catch (IOException e) {
                list.add(data); // recover
                e.printStackTrace();
                return false;
            }
        }
        return false; // ID not found
    }
}
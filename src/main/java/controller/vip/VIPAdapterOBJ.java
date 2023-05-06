package controller.vip;

import model.VIP;
import org.jetbrains.annotations.Nullable;

import controller.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class VIPAdapterOBJ implements GenericDataIO<VIP> {
    private final String filePath;
    private List<VIP> list = new ArrayList<>();

    public VIPAdapterOBJ(String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            Objects.requireNonNull(getAll(), "VIP list must be a non-null value");
        }

        // handle lazy loading
        VIP fb = VIP.builder().id().point(0).transactions(0).active(true).build();
        insert(fb);
        delete(fb.getId());
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
        list = (List<VIP>) ois.readObject(); // wah unsafe gmn ya biar safe
        ois.close();
    }

    @Override
    @Nullable
    public VIP getByID(int id) {
        List<VIP> filtered = Objects.requireNonNull(getAll(), "VIP list must be a non-null value").stream()
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
    public List<VIP> getAll() {
        try {
            read();
            return list;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null; // Return null artinya terjadi IOException
        }
    }

    @Override
    public boolean insert(VIP data) {
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

    @Override
    public boolean update(VIP newData) {
        Objects.requireNonNull(getAll(), "VIP list must be a non-null value");

        int pos = -1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == newData.getId()) {
                pos = i;
                break;
            }
        }

        if (pos != -1) {
            VIP prevData = list.get(pos).toBuilder().build();
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
        VIP data = getByID(id);
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
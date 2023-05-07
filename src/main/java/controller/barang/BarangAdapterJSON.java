package controller.barang;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Barang;
import model.Member;
import controller.*;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BarangAdapterJSON implements GenericDataIO<Barang> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final String filePath;
    private List<Barang> list = new ArrayList<>();

    public BarangAdapterJSON(String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            Objects.requireNonNull(getAll(), "Barang list must be a non-null value");
        }

        // handle lazy loading
        Barang b = Barang.builder().id().jumlah(10).hargaJual(5).hargaBeli(2).build();
        insert(b);
        delete(b.getId());
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
            list = mapper.readValue(new File(filePath), new TypeReference<List<Barang>>() {
            });
            return list;
        } catch (IOException e) {
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
            Barang temp = data.toBuilder().build();
            temp.setJumlah(temp.getJumlah() + prevData.getJumlah());
            temp.setId(data.getId());
            try {
                list.set(pos, temp);
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), list);
                return true;
            } catch (IOException e) {
                list.set(pos, prevData); // recover
                e.printStackTrace();
                return false;
            }
        } else { // add new barang
            try {
                list.add(data);
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), list);
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
            try {
                list.set(pos, newData);
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), list);
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
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), list);
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
package controller.fixedbill;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.FixedBill;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FixedBillAdapterXML implements FixedBillIO {
    private static final XmlMapper mapper = (XmlMapper) new XmlMapper().registerModule(new JavaTimeModule());
    private final String filePath;
    private List<FixedBill> list = new ArrayList<>();

    public FixedBillAdapterXML(String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            Objects.requireNonNull(getAllFixedBill(),"Fixed Bill list must be a non-null value");
        }

        // handle lazy loading
        FixedBill fb = FixedBill.builder().id().billing(0).build();
        insertFixedBill(fb);
        deleteFixedBill(fb.getId());
    }

    @Override @Nullable
    public FixedBill getByID(int id) {
        List<FixedBill> filtered = Objects.requireNonNull(getAllFixedBill(), "Fixed Bill list must be a non-null value").stream().filter(fixedBill -> fixedBill.getId() == id).collect(Collectors.toList());
        if (!filtered.isEmpty()) {
            return filtered.get(0);
        } else {
            System.out.println("ID " + id + " tidak ditemukan");
            return null; // Return null artinya ID ga ketemu
        }
    }

    @Override @Nullable
    public List<FixedBill> getAllFixedBill() {
        try {
            list = mapper.readValue(new File(filePath), new TypeReference<List<FixedBill>>() { });
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null artinya terjadi IOException
        }
    }

    @Override
    public boolean insertFixedBill(FixedBill data) {
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

    @Override
    public boolean updateFixedBill(FixedBill newData) {
        Objects.requireNonNull(getAllFixedBill(),"Fixed Bill list must be a non-null value");

        int pos = -1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == newData.getId()) {
                pos = i;
                break;
            }
        }

        if (pos != -1) {
            FixedBill prevData = list.get(pos).toBuilder().build();
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
    public boolean deleteFixedBill(int id) {
        FixedBill data = getByID(id);
        if (data != null) {
            try {
                list.remove(data);
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), list);
                return true;
            } catch (IOException e){
                list.add(data); // recover
                e.printStackTrace();
                return false;
            }
        }
        return false; // ID not found
    }
}
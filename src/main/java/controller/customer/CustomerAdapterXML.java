package controller.customer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Customer;
import model.Member;
import model.VIP;
import org.jetbrains.annotations.Nullable;

import controller.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomerAdapterXML implements GenericDataIO<Customer> {
    private static final XmlMapper mapper = (XmlMapper) new XmlMapper().registerModule(new JavaTimeModule());
    private final String filePath;
    private List<Customer> list = new ArrayList<>();

    public CustomerAdapterXML(String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            Objects.requireNonNull(getAll(), "Customer list must be a non-null value");

            if (list.size() != 0) {
                Customer.resetCount(list.stream().max(Comparator.comparing(Customer::getId)).get().getId());
            }
        }

        Customer.resetCount(Math.max(Math.max(Member.checkMaxMemID(), Customer.checkCount()), VIP.checkMaxVIPID()));
        // handle lazy loading
        Customer b = Customer.builder().id().build();
        insert(b);
        delete(b.getId());
    }

    @Override
    @Nullable
    public Customer getByID(int id) {
        List<Customer> filtered = Objects.requireNonNull(getAll(), "Customer list must be a non-null value").stream()
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
    public List<Customer> getAll() {
        try {
            list = mapper.readValue(new File(filePath), new TypeReference<List<Customer>>() {
            });
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null artinya terjadi IOException
        }
    }

    @Override
    public boolean insert(Customer data) {
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
    public boolean update(Customer newData) {
        Objects.requireNonNull(getAll(), "Customer list must be a non-null value");

        int pos = -1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == newData.getId()) {
                pos = i;
                break;
            }
        }

        if (pos != -1) {
            Customer prevData = list.get(pos).toBuilder().build();
            Customer.resetCount(Customer.checkCount() - 1);
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
        Customer data = getByID(id);
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
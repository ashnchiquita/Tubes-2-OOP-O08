package controller.member;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Customer;
import model.Member;
import org.jetbrains.annotations.Nullable;

import controller.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MemberAdapterXML implements GenericDataIO<Member> {
    private static final XmlMapper mapper = (XmlMapper) new XmlMapper().registerModule(new JavaTimeModule());
    private final String filePath;
    private List<Member> list = new ArrayList<>();

    public MemberAdapterXML(String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            Objects.requireNonNull(getAll(), "Member list must be a non-null value");
            if (list.size() != 0) {
                Member.resetMaxMemID(list.stream().max(Comparator.comparing(Customer::getId)).get().getId());
            }
        }

        // handle lazy loading
        Member fb = Member.builder().id().point(0).transactions(0).active(true).build();
        insert(fb);
        delete(fb.getId());
    }

    @Override
    @Nullable
    public Member getByID(int id) {
        List<Member> filtered = Objects.requireNonNull(getAll(), "Member list must be a non-null value").stream()
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
    public List<Member> getAll() {
        try {
            list = mapper.readValue(new File(filePath), new TypeReference<List<Member>>() {
            });
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null artinya terjadi IOException
        }
    }

    @Override
    public boolean insert(Member data) {
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
    public boolean update(Member newData) {
        Objects.requireNonNull(getAll(), "Member list must be a non-null value");

        int pos = -1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == newData.getId()) {
                pos = i;
                break;
            }
        }

        if (pos != -1) {
            Member prevData = list.get(pos).toBuilder().build();
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
        Member data = getByID(id);
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

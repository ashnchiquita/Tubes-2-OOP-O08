package controller.member;

import model.Member;
import org.jetbrains.annotations.Nullable;

import controller.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MemberAdapterOBJ implements GenericDataIO<Member> {
    private final String filePath;
    private List<Member> list = new ArrayList<>();

    public MemberAdapterOBJ(String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            Objects.requireNonNull(getAll(), "Member list must be a non-null value");
        }

        // handle lazy loading
        Member fb = Member.builder().id().point(0).transactions(0).active(true).build();
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
        list = (List<Member>) ois.readObject(); // wah unsafe gmn ya biar safe
        ois.close();
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
            read();
            return list;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null; // Return null artinya terjadi IOException
        }
    }

    @Override
    public boolean insert(Member data) {
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
        Member data = getByID(id);
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
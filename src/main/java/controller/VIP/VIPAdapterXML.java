package controller.VIP;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import lombok.RequiredArgsConstructor;
import model.VIP;

@RequiredArgsConstructor
public class VIPAdapterXML implements VIPDataIO {
    private final String filename;

    private boolean isValidDataField(String line) {
        return line.contains("<id>") || line.contains("<name>") || line.contains("<phone>") || line.contains("<point>")
                || line.contains("transaction");
    }

    @Override
    public VIP getByID(int id) {
        try {
            List<String> VIPXMList = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get(filename));

            lines.stream().reduce("", (prev, curr) -> {
                if (isValidDataField(curr)) {
                    return prev + curr.strip();
                } else if (curr.contains("</VIP>")) {
                    VIPXMList.add(prev);
                    return "";
                }
                return prev;
            });

            String VIPString = VIPXMList.stream().filter(str -> str.contains(String.format("<id>%d</id>", id)))
                    .collect(Collectors.toList()).get(0);

            int dataID = Integer
                    .parseInt(VIPString
                            .substring(VIPString.indexOf("<id>") + "<id>".length(), VIPString.indexOf("</id>")).strip());
            String name = VIPString
                    .substring(VIPString.indexOf("<name>") + "<name>".length(), VIPString.indexOf("</name>")).strip();
            String phone = VIPString
                    .substring(VIPString.indexOf("<phone>") + "<phone>".length(), VIPString.indexOf("</phone>")).strip();
            int point = Integer
                    .parseInt(VIPString
                            .substring(VIPString.indexOf("<point>") + "<point>".length(), VIPString.indexOf("</point>"))
                            .strip());
            int transactions = Integer
                    .parseInt(VIPString
                            .substring(VIPString.indexOf("<transactions>") + "<transactions>".length(),
                                    VIPString.indexOf("</transactions>"))
                            .strip());

            return VIP.builder().id(dataID).name(name).phone(phone).point(point).transactions(transactions).build();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ID does not exist");
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public List<VIP> getAllVIPs() {
        try {
            List<String> VIPXMList = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get(filename));

            lines.stream().reduce("", (prev, curr) -> {
                if (isValidDataField(curr)) {
                    return prev + curr.strip();
                } else if (curr.contains("</VIP>")) {
                    VIPXMList.add(prev);
                    return "";
                }
                return prev;
            });

            List<VIP> VIPList = new ArrayList<>();

            VIPXMList.stream().forEach(VIPString -> {
                int dataID = Integer
                        .parseInt(VIPString
                                .substring(VIPString.indexOf("<id>") + "<id>".length(), VIPString.indexOf("</id>")).strip());
                String name = VIPString
                        .substring(VIPString.indexOf("<name>") + "<name>".length(), VIPString.indexOf("</name>")).strip();
                String phone = VIPString
                        .substring(VIPString.indexOf("<phone>") + "<phone>".length(), VIPString.indexOf("</phone>")).strip();
                int point = Integer
                        .parseInt(VIPString
                                .substring(VIPString.indexOf("<point>") + "<point>".length(), VIPString.indexOf("</point>"))
                                .strip());
                int transactions = Integer
                        .parseInt(VIPString
                                .substring(VIPString.indexOf("<transactions>") + "<transactions>".length(),
                                        VIPString.indexOf("</transactions>"))
                                .strip());

                VIPList
                        .add(VIP.builder().id(dataID).name(name).phone(phone).point(point).transactions(transactions).build());
            });

            return VIPList;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public boolean insertVIP(VIP data) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            if (lines.stream().filter(str -> str.contains(String.format("<id>%d</id>", data.getId())))
                    .collect(Collectors.toList())
                    .size() > 0) {
                System.out.println("Id is not unique");
                return false;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            lines.subList(0, lines.size() - 1).stream().forEach(line -> {
                try {
                    writer.write(line);
                    writer.newLine();
                } catch (IOException e) {
                    System.out.println(e);
                }
            });

            writer.newLine();
            writer.write("  <VIP>");
            writer.newLine();
            writer.write(String.format("    <id>%d</id>", data.getId()));
            writer.newLine();
            writer.write(String.format("    <name>%s</name>", data.getName()));
            writer.newLine();
            writer.write(String.format("    <phone>%s</phone>", data.getPhone()));
            writer.newLine();
            writer.write(String.format("    <point>%d</point>", data.getPoint()));
            writer.newLine();
            writer.write(String.format("    <transactions>%d</transactions>", data.getTransactions()));
            writer.newLine();
            writer.write("  </VIP>");
            writer.newLine();
            writer.write(lines.get(lines.size() - 1));
            writer.newLine();

            writer.close();

            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean updateVIP(int id, VIP newData) {
        try {
            List<String> VIPXMList = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get(filename));

            lines.stream().reduce("", (prev, curr) -> {
                if (isValidDataField(curr)) {
                    return prev + curr + '\n';
                } else if (curr.contains("</VIP>")) {
                    VIPXMList.add(prev);
                    return "";
                }
                return prev;
            });

            int updateIndex = -1;
            for (int i = 0; i < VIPXMList.size(); i++) {
                if (VIPXMList.get(i).contains(String.format("<id>%d</id>", id))) {
                    updateIndex = i;
                    break;
                }
            }

            if (updateIndex == -1) {
                return false;
            }

            String VIPString = VIPXMList.get(updateIndex);
            String newDataString = VIPString
                    .substring(0, VIPString.indexOf("<name>") + "<name>".length()) + newData.getName()
                    + VIPString.substring(VIPString.indexOf("</name>"), VIPString.length());

            newDataString = newDataString
                    .substring(0, newDataString.indexOf("<phone>") + "<phone>".length()) + String.valueOf(newData.getPhone())
                    + newDataString.substring(newDataString.indexOf("</phone>"), newDataString.length());

            newDataString = newDataString
                    .substring(0, newDataString.indexOf("<point>") + "<point>".length()) + newData.getPoint()
                    + newDataString.substring(newDataString.indexOf("</point>"), newDataString.length());

            newDataString = newDataString
                    .substring(0, newDataString.indexOf("<transactions>") + "<transactions>".length()) + newData.getTransactions()
                    + newDataString.substring(newDataString.indexOf("</transactions>"), newDataString.length());

            VIPXMList.set(updateIndex, newDataString);

            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("<VIPList>");
            writer.newLine();

            VIPXMList.stream().forEach(str -> {
                try {
                    writer.write("  <VIP>");
                    writer.newLine();

                    Arrays.asList(str.split("\n")).stream().forEach(xmlString -> {
                        try {
                            writer.write(xmlString);
                            writer.newLine();
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    });

                    writer.write("  </VIP>");
                    writer.newLine();
                } catch (IOException e) {
                    System.out.println(e);
                }
            });

            writer.write("</VIPList>");
            writer.newLine();
            writer.close();

            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ID does not exist");
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    @Override
    public boolean deleteVIP(int id) {
        try {
            List<String> VIPXMList = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get(filename));

            lines.stream().reduce("", (prev, curr) -> {
                if (isValidDataField(curr)) {
                    return prev + curr + '\n';
                } else if (curr.contains("</VIP>")) {
                    VIPXMList.add(prev);
                    return "";
                }
                return prev;
            });

            int updateIndex = -1;
            for (int i = 0; i < VIPXMList.size(); i++) {
                if (VIPXMList.get(i).contains(String.format("<id>%d</id>", id))) {
                    updateIndex = i;
                    break;
                }
            }

            if (updateIndex == -1) {
                return false;
            }

            VIPXMList.remove(updateIndex);

            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("<VIPList>");
            writer.newLine();

            VIPXMList.stream().forEach(str -> {
                try {
                    writer.write("  <VIP>");
                    writer.newLine();

                    Arrays.asList(str.split("\n")).stream().forEach(xmlString -> {
                        try {
                            writer.write(xmlString);
                            writer.newLine();
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    });

                    writer.write("  </VIP>");
                    writer.newLine();
                } catch (IOException e) {
                    System.out.println(e);
                }
            });

            writer.write("</VIPList>");
            writer.newLine();
            writer.close();

            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ID does not exist");
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }
}

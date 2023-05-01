package controller.barang;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import model.Barang;

@RequiredArgsConstructor
public class BarangAdapterXML implements BarangDataIO {
    private final String filename;

    private boolean isValidDataField(String line) {
        return line.contains("<id>") || line.contains("<name>") || line.contains("<kategori>") || line.contains("<hargaJual>")
            || line.contains("hargaBeli") || line.contains("jumlah") || line.contains("gambar");
    }

    public Barang getByID(int id) {
        try {
            List<String> barangXMList = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get(filename));
        
            lines.stream().reduce("", (prev, curr) -> {
                if (isValidDataField(curr)) {
                  return prev + curr.strip();
                } else if (curr.contains("</Barang>")) {
                  barangXMList.add(prev);
                  return "";
                }
                return prev;
            });

            String barangString = barangXMList.stream().filter(str -> str.contains(String.format("<id>%d</id>", id)))
            .collect(Collectors.toList()).get(0);

            int dataID = Integer
                .parseInt(barangString
                    .substring(barangString.indexOf("<id>") + "<id>".length(), barangString.indexOf("</id>")).strip());
            String name = barangString
                .substring(barangString.indexOf("<name>") + "<name>".length(), barangString.indexOf("</name>")).strip();
            String kategori = barangString
                .substring(barangString.indexOf("<kategori>") + "<kategori>".length(), barangString.indexOf("</kategori>")).strip();
            int hargaJual = Integer
                .parseInt(barangString
                    .substring(barangString.indexOf("<hargaJual>") + "<hargaJual>".length(), barangString.indexOf("</hargaJual>"))
                    .strip());
            int hargaBeli = Integer
            .parseInt(barangString
                .substring(barangString.indexOf("<hargaBeli>") + "<hargaBeli>".length(), barangString.indexOf("</hargaBeli>"))
                .strip());
            int jumlah = Integer
            .parseInt(barangString
                .substring(barangString.indexOf("<jumlah>") + "<jumlah>".length(), barangString.indexOf("</jumlah>"))
                .strip());
            String gambar = barangString
                .substring(barangString.indexOf("<gambar>") + "<gambar>".length(), barangString.indexOf("</gambar>")).strip();
                return Barang.builder().id(dataID).name(name).kategori(kategori).hargaJual(hargaJual).hargaBeli(hargaBeli).jumlah(jumlah).gambar(gambar).build();
        
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ID does not exist");
        } catch (Exception e) {
            System.out.println(e);
        }
      
        return null;
    }
    @Override
    public List<Barang> getAllBarang() {
      try {
        List<String> barangXMList = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));
  
        lines.stream().reduce("", (prev, curr) -> {
          if (isValidDataField(curr)) {
            return prev + curr.strip();
          } else if (curr.contains("</Barang>")) {
            barangXMList.add(prev);
            return "";
          }
          return prev;
        });
  
        List<Barang>barangList = new ArrayList<>();
  
        barangXMList.stream().forEach(barangString -> {
            int dataID = Integer
                .parseInt(barangString
                    .substring(barangString.indexOf("<id>") + "<id>".length(), barangString.indexOf("</id>")).strip());
            String name = barangString
                .substring(barangString.indexOf("<name>") + "<name>".length(), barangString.indexOf("</name>")).strip();
            String kategori = barangString
                .substring(barangString.indexOf("<kategori>") + "<kategori>".length(), barangString.indexOf("</kategori>")).strip();
            int hargaJual = Integer
                .parseInt(barangString
                    .substring(barangString.indexOf("<hargaJual>") + "<hargaJual>".length(), barangString.indexOf("</hargaJual>"))
                    .strip());
            int hargaBeli = Integer
            .parseInt(barangString
                .substring(barangString.indexOf("<hargaBeli>") + "<hargaBeli>".length(), barangString.indexOf("</hargaBeli>"))
                .strip());
            int jumlah = Integer
            .parseInt(barangString
                .substring(barangString.indexOf("<jumlah>") + "<jumlah>".length(), barangString.indexOf("</jumlah>"))
                .strip());
            String gambar = barangString
                .substring(barangString.indexOf("<gambar>") + "<gambar>".length(), barangString.indexOf("</gambar>")).strip();
  
          barangList
              .add(Barang.builder().id(dataID).name(name).kategori(kategori).hargaJual(hargaJual).hargaBeli(hargaBeli).jumlah(jumlah).gambar(gambar).build());
        });
  
        return barangList;
      } catch (Exception e) {
        System.out.println(e);
      }
  
      return null;
    }

    @Override
    public boolean insertBarang(Barang data) {
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
        writer.write("  <Barang>");
        writer.newLine();
        writer.write(String.format("    <id>%d</id>", data.getId()));
        writer.newLine();
        writer.write(String.format("    <name>%s</name>", data.getName()));
        writer.newLine();
        writer.write(String.format("    <kategori>%s</kategori>", data.getKategori()));
        writer.newLine();
        writer.write(String.format("    <hargaJual>%d</hargaJual>", data.getHargaJual()));
        writer.newLine();
        writer.write(String.format("    <hargaBeli>%d</hargaBeli>", data.getHargaBeli()));
        writer.newLine();
        writer.write(String.format("    <jumlah>%d</jumlah>", data.getJumlah()));
        writer.newLine();
        writer.write(String.format("    <gambar>%d</gambar>", data.getGambar()));
        writer.newLine();
        writer.write("  </Barang>");
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
    public boolean updateBarang(int id, Barang newData) {
      try {
        List<String> barangXMList = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));
  
        lines.stream().reduce("", (prev, curr) -> {
          if (isValidDataField(curr)) {
            return prev + curr + '\n';
          } else if (curr.contains("</Barang>")) {
            barangXMList.add(prev);
            return "";
          }
          return prev;
        });
  
        int updateIndex = -1;
        for (int i = 0; i < barangXMList.size(); i++) {
          if (barangXMList.get(i).contains(String.format("<id>%d</id>", id))) {
            updateIndex = i;
            break;
          }
        }
  
        if (updateIndex == -1) {
          return false;
        }
  
        String barangString = barangXMList.get(updateIndex);
        String newDataString = barangString
            .substring(0, barangString.indexOf("<name>") + "<name>".length()) + newData.getName()
            + barangString.substring(barangString.indexOf("</name>"), barangString.length());
  
        newDataString = newDataString
            .substring(0, newDataString.indexOf("<kategori>") + "<kategori>".length()) + String.valueOf(newData.getKategori())
            + newDataString.substring(newDataString.indexOf("</kategori>"), newDataString.length());
  
        newDataString = newDataString
            .substring(0, newDataString.indexOf("<hargaJual>") + "<hargaJual>".length()) + newData.getHargaJual()
            + newDataString.substring(newDataString.indexOf("</hargaJual>"), newDataString.length());
       
        newDataString = newDataString
        .substring(0, newDataString.indexOf("<hargaBeli>") + "<hargaBeli>".length()) + newData.getHargaBeli()
        + newDataString.substring(newDataString.indexOf("</hargaBeli>"), newDataString.length());

        newDataString = newDataString
        .substring(0, newDataString.indexOf("<jumlah>") + "<jumlah>".length()) + newData.getJumlah()
        + newDataString.substring(newDataString.indexOf("</jumlah>"), newDataString.length());

        newDataString = newDataString
            .substring(0, newDataString.indexOf("<gambar>") + "<gambar>".length()) + newData.getGambar()
            + newDataString.substring(newDataString.indexOf("</gambar>"), newDataString.length());
  
        barangXMList.set(updateIndex, newDataString);
  
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("<BarangList>");
        writer.newLine();
  
        barangXMList.stream().forEach(str -> {
          try {
            writer.write("  <Barang>");
            writer.newLine();
  
            Arrays.asList(str.split("\n")).stream().forEach(xmlString -> {
              try {
                writer.write(xmlString);
                writer.newLine();
              } catch (IOException e) {
                System.out.println(e);
              }
            });
  
            writer.write("  </Barang>");
            writer.newLine();
          } catch (IOException e) {
            System.out.println(e);
          }
        });
  
        writer.write("</BarangList>");
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
  public boolean deleteBarang(int id) {
    try {
      List<String> barangXMList = new ArrayList<>();
      List<String> lines = Files.readAllLines(Paths.get(filename));

      lines.stream().reduce("", (prev, curr) -> {
        if (isValidDataField(curr)) {
          return prev + curr + '\n';
        } else if (curr.contains("</Barang>")) {
          barangXMList.add(prev);
          return "";
        }
        return prev;
      });

      int updateIndex = -1;
      for (int i = 0; i < barangXMList.size(); i++) {
        if (barangXMList.get(i).contains(String.format("<id>%d</id>", id))) {
          updateIndex = i;
          break;
        }
      }

      if (updateIndex == -1) {
        return false;
      }

      barangXMList.remove(updateIndex);

      BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
      writer.write("<BarangList>");
      writer.newLine();

      barangXMList.stream().forEach(str -> {
        try {
          writer.write("  <Barang>");
          writer.newLine();

          Arrays.asList(str.split("\n")).stream().forEach(xmlString -> {
            try {
              writer.write(xmlString);
              writer.newLine();
            } catch (IOException e) {
              System.out.println(e);
            }
          });

          writer.write("  </Barang>");
          writer.newLine();
        } catch (IOException e) {
          System.out.println(e);
        }
      });

      writer.write("</BarangList>");
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

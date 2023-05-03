package controller.datastore;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.dataformat.xml.*;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class XMLAdapter<T> implements DataIO<T> {
  private static final XmlMapper mapper = (XmlMapper) new XmlMapper().registerModule(new JavaTimeModule());

  private final String filepath;
  private final Class<T> clazz;

  public XMLAdapter(String filepath, Class<T> clazz) {
    this.filepath = filepath;
    this.clazz = clazz;
    File f = new File(filepath);
    if (!f.exists() || f.isDirectory()) {
      try {
        mapper.writerWithDefaultPrettyPrinter().writeValue(f, new ArrayList<T>());
      } catch (IOException e) {
        System.out.println("Error initializing XML Adapter");
      }
    }
  }

  private boolean insertWrapper() {
    try {
      List<String> lines = Files.readAllLines(Paths.get(filepath));
      BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
      lines.stream().forEach(line -> {
        try {
          if (line.contains("<item>")) {
            writer.write(line.replace("<item>", String.format("<item _class=\"%s\">", clazz.getName())));
          } else {
            writer.write(line);
          }
          writer.newLine();
        } catch (IOException e) {
          System.out.println(e);
        }
      });

      writer.close();
      return true;
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }

  @Override
  public T findById(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public List<T> findAll() {
    try {
      List<T> list = mapper.readValue(new File(filepath),
          TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz));
      return list;
    } catch (IOException e) {
      System.out.println(String.format("XML Adapter error on findAll method: %s", e));
      return new ArrayList<T>();
    }
  }

  @Override
  public boolean insert(T data) {
    try {
      List<T> dataList = mapper.readValue(new File(filepath),
          TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz));
      dataList.add(data);
      mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filepath), dataList);
      return true;
    } catch (IOException e) {
      System.out.println(String.format("XML Adapter error on insert method: %s", e));
      return false;
    }
  }

  @Override
  public boolean update(int id, T newData) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public boolean delete(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

}

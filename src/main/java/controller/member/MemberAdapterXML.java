package controller.member;

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
import model.Member;

@RequiredArgsConstructor
public class MemberAdapterXML implements MemberDataIO {
  private final String filename;

  private boolean isValidDataField(String line) {
    return line.contains("<id>") || line.contains("<name>") || line.contains("<phone>") || line.contains("<point>")
        || line.contains("transaction");
  }

  @Override
  public Member getByID(int id) {
    try {
      List<String> memberXMList = new ArrayList<>();
      List<String> lines = Files.readAllLines(Paths.get(filename));

      lines.stream().reduce("", (prev, curr) -> {
        if (isValidDataField(curr)) {
          return prev + curr.strip();
        } else if (curr.contains("</Member>")) {
          memberXMList.add(prev);
          return "";
        }
        return prev;
      });

      String memberString = memberXMList.stream().filter(str -> str.contains(String.format("<id>%d</id>", id)))
          .collect(Collectors.toList()).get(0);

      int dataID = Integer
          .parseInt(memberString
              .substring(memberString.indexOf("<id>") + "<id>".length(), memberString.indexOf("</id>")).strip());
      String name = memberString
          .substring(memberString.indexOf("<name>") + "<name>".length(), memberString.indexOf("</name>")).strip();
      String phone = memberString
          .substring(memberString.indexOf("<phone>") + "<phone>".length(), memberString.indexOf("</phone>")).strip();
      int point = Integer
          .parseInt(memberString
              .substring(memberString.indexOf("<point>") + "<point>".length(), memberString.indexOf("</point>"))
              .strip());
      int transactions = Integer
          .parseInt(memberString
              .substring(memberString.indexOf("<transactions>") + "<transactions>".length(),
                  memberString.indexOf("</transactions>"))
              .strip());

      return Member.builder().id(dataID).name(name).phone(phone).point(point).transactions(transactions).build();
    } catch (IndexOutOfBoundsException e) {
      System.out.println("ID does not exist");
    } catch (Exception e) {
      System.out.println(e);
    }

    return null;
  }

  @Override
  public List<Member> getAllMembers() {
    try {
      List<String> memberXMList = new ArrayList<>();
      List<String> lines = Files.readAllLines(Paths.get(filename));

      lines.stream().reduce("", (prev, curr) -> {
        if (isValidDataField(curr)) {
          return prev + curr.strip();
        } else if (curr.contains("</Member>")) {
          memberXMList.add(prev);
          return "";
        }
        return prev;
      });

      List<Member> memberList = new ArrayList<>();

      memberXMList.stream().forEach(memberString -> {
        int dataID = Integer
            .parseInt(memberString
                .substring(memberString.indexOf("<id>") + "<id>".length(), memberString.indexOf("</id>")).strip());
        String name = memberString
            .substring(memberString.indexOf("<name>") + "<name>".length(), memberString.indexOf("</name>")).strip();
        String phone = memberString
            .substring(memberString.indexOf("<phone>") + "<phone>".length(), memberString.indexOf("</phone>")).strip();
        int point = Integer
            .parseInt(memberString
                .substring(memberString.indexOf("<point>") + "<point>".length(), memberString.indexOf("</point>"))
                .strip());
        int transactions = Integer
            .parseInt(memberString
                .substring(memberString.indexOf("<transactions>") + "<transactions>".length(),
                    memberString.indexOf("</transactions>"))
                .strip());

        memberList
            .add(Member.builder().id(dataID).name(name).phone(phone).point(point).transactions(transactions).build());
      });

      return memberList;
    } catch (Exception e) {
      System.out.println(e);
    }

    return null;
  }

  @Override
  public boolean insertMember(Member data) {
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
      writer.write("  <Member>");
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
      writer.write("  </Member>");
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
  public boolean updateMember(int id, Member newData) {
    try {
      List<String> memberXMList = new ArrayList<>();
      List<String> lines = Files.readAllLines(Paths.get(filename));

      lines.stream().reduce("", (prev, curr) -> {
        if (isValidDataField(curr)) {
          return prev + curr + '\n';
        } else if (curr.contains("</Member>")) {
          memberXMList.add(prev);
          return "";
        }
        return prev;
      });

      int updateIndex = -1;
      for (int i = 0; i < memberXMList.size(); i++) {
        if (memberXMList.get(i).contains(String.format("<id>%d</id>", id))) {
          updateIndex = i;
          break;
        }
      }

      if (updateIndex == -1) {
        return false;
      }

      String memberString = memberXMList.get(updateIndex);
      String newDataString = memberString
          .substring(0, memberString.indexOf("<name>") + "<name>".length()) + newData.getName()
          + memberString.substring(memberString.indexOf("</name>"), memberString.length());

      newDataString = newDataString
          .substring(0, newDataString.indexOf("<phone>") + "<phone>".length()) + String.valueOf(newData.getPhone())
          + newDataString.substring(newDataString.indexOf("</phone>"), newDataString.length());

      newDataString = newDataString
          .substring(0, newDataString.indexOf("<point>") + "<point>".length()) + newData.getPoint()
          + newDataString.substring(newDataString.indexOf("</point>"), newDataString.length());

      newDataString = newDataString
          .substring(0, newDataString.indexOf("<transactions>") + "<transactions>".length()) + newData.getTransactions()
          + newDataString.substring(newDataString.indexOf("</transactions>"), newDataString.length());

      memberXMList.set(updateIndex, newDataString);

      BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
      writer.write("<MemberList>");
      writer.newLine();

      memberXMList.stream().forEach(str -> {
        try {
          writer.write("  <Member>");
          writer.newLine();

          Arrays.asList(str.split("\n")).stream().forEach(xmlString -> {
            try {
              writer.write(xmlString);
              writer.newLine();
            } catch (IOException e) {
              System.out.println(e);
            }
          });

          writer.write("  </Member>");
          writer.newLine();
        } catch (IOException e) {
          System.out.println(e);
        }
      });

      writer.write("</MemberList>");
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
  public boolean deleteMember(int id) {
    try {
      List<String> memberXMList = new ArrayList<>();
      List<String> lines = Files.readAllLines(Paths.get(filename));

      lines.stream().reduce("", (prev, curr) -> {
        if (isValidDataField(curr)) {
          return prev + curr + '\n';
        } else if (curr.contains("</Member>")) {
          memberXMList.add(prev);
          return "";
        }
        return prev;
      });

      int updateIndex = -1;
      for (int i = 0; i < memberXMList.size(); i++) {
        if (memberXMList.get(i).contains(String.format("<id>%d</id>", id))) {
          updateIndex = i;
          break;
        }
      }

      if (updateIndex == -1) {
        return false;
      }

      memberXMList.remove(updateIndex);

      BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
      writer.write("<MemberList>");
      writer.newLine();

      memberXMList.stream().forEach(str -> {
        try {
          writer.write("  <Member>");
          writer.newLine();

          Arrays.asList(str.split("\n")).stream().forEach(xmlString -> {
            try {
              writer.write(xmlString);
              writer.newLine();
            } catch (IOException e) {
              System.out.println(e);
            }
          });

          writer.write("  </Member>");
          writer.newLine();
        } catch (IOException e) {
          System.out.println(e);
        }
      });

      writer.write("</MemberList>");
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

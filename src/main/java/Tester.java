import controller.member.MemberAdapterXML;
import controller.member.MemberController;

public class Tester {
  public static void main(String[] args) {
    MemberController controller = new MemberController();
    controller.setDataIO(new MemberAdapterXML("src/main/resources/data/member.xml"));

  }
}

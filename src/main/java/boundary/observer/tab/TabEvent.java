package boundary.observer.tab;


public class TabEvent {
    public static final String CLOSE = "Close";
    public String type;
    public TabEvent(String type) {
        this.type = type;
    }

}

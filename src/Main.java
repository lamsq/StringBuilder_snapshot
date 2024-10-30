import java.util.*;

public class Main {
    public static void main(String[] args) {
        StringBuilderCustom sb = new StringBuilderCustom();

        sb.append("Hello");
        System.out.println(sb.getContent());  //output "Hello"
        sb.append(", world!");
        System.out.println(sb.getContent());  //output "Hello, world!"
        sb.undo();
        System.out.println(sb.getContent());  //output "Hello"
        sb.undo();
        System.out.println(sb.getContent());  //output: ""
    }
}

class Snapshot {
    private final String state;

    public Snapshot(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}

class StringBuilderCustom {
    private StringBuilder content;
    private Stack<Snapshot> history;

    public StringBuilderCustom() {
        this.content = new StringBuilder();
        this.history = new Stack<>();
    }

    public void append(String text) {
        saveSnapshot(); //save the state
        content.append(text);
    }

    private void saveSnapshot() {
        history.push(new Snapshot(content.toString()));
    }

    public void undo() {
        if (!history.isEmpty()) {
            Snapshot lastSnapshot = history.pop();
            content = new StringBuilder(lastSnapshot.getState()); //restore the last state
        } else {
            System.out.println("Initial state reached");
        }
    }

    public String getContent() {
        return content.toString();
    }

    public void clear() {
        saveSnapshot(); //save the state
        content.setLength(0);
    }
}

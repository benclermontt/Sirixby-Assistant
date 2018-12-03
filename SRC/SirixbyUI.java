import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.awt.*;

public class SirixbyUI extends JFrame {
    private JPanel rootPanel;
    private JTextField textField1;
    private JButton button1;
    private JTextArea sirixbyPersonalAssistantTextArea;
    private JTextArea textArea1;
    private JTextField OutputNorth;
    private JTextField InputSouth;

    public SirixbyUI(){
        add(rootPanel);
        setTitle("Sirixby Personal Assistant");
        setSize(800,600);
    }

    public void updateTextArea(String text) {
        textArea1.append("\n" + text);
    }

    public String readInput() {
        return textField1.getText();
    }
}

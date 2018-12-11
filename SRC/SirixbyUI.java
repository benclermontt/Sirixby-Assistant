import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SirixbyUI extends JFrame implements ActionListener {
    private JPanel rootPanel;
    public JTextField textField1;
    public JButton button1;
    public JTextArea textArea1;
    private String word2;
    public static volatile boolean s1 = false;
    public String answer = "";

    public SirixbyUI(){
        add(rootPanel);
        setTitle("Sirixby Personal Assistant");
        setSize(600,650);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                // Set word2 with the input string from the textfiel
                answer = textField1.getText();
                textField1.setText("");
            }
        });

    }

    public void updateTextArea(String text) {
        textArea1.append("\n" + text);
    }

    public void actionPerformed(ActionEvent e) {

    }

    public String getInput() {
        while(answer.equalsIgnoreCase("") || answer == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return answer;
            }
        }

        return answer;
    }
}

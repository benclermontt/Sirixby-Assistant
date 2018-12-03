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
    public static boolean s1 = false;

    public SirixbyUI(){
        add(rootPanel);
        setTitle("Sirixby Personal Assistant");
        setSize(400,500);


    }

    public void updateTextArea(String text) {
        textArea1.append("\n" + text);
    }

    public String readInput() {


        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                // Set word2 with the input string from the textfiel
                SirixbyUI.s1 = true;

            }
        });

        while(!s1) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                String yes = textField1.getText();
                textField1.setText("");
                return yes;
            }
        }
        return textField1.getText();

    }

    public void actionPerformed(ActionEvent e) {

    }
}

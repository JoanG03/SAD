package XatGrafic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
public class XatGrafic {
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        MySocket sc = new MySocket(args[0], Integer.parseInt(args[1]));
        JFrame frame = new JFrame("Xat");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> missatges = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(missatges);

        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField textField = new JTextField();
        JButton sendButton = new JButton("Send");
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String missatge = textField.getText();
                
                if (!missatge.equals("")) { 
                    sc.writeLine(missatge); 
                    listModel.addElement("Tu: " + missatge); 
                    textField.setText(""); 
                }
            }
        });
        

        new Thread(){
            public void run(){
                String line;
                while ((line = sc.readLine()) != null) {
                    listModel.addElement(line); 
                }
                sc.close(); 
            }
        }.start();
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                sc.close();
            }
        });
    }
}

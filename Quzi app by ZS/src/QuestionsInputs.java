import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class QuestionsInputs extends JFrame implements ActionListener {

    JPanel pan1,pan2;
    JLabel questionLbl,aLbl,bLbl,cLbl,dLbl;
    JTextField aFd,bFd,cFd,dFd;
    JTextArea questionFd;
    JButton saveBtn,exitBtn;
    JComboBox<String> options;
    String[] opts = {"A","B","C","D"};
    static int counter = 0;
    int till;
    String Title,saveCrtOpt,statement;
    String[] optFds = new String[4];


    QuestionsInputs(int n,String title){
        super("Questions");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.white);

        pan1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        pan1.setBackground(Color.white);

        counter++;
        till=n;
        Title=title;

        questionLbl = new JLabel("Question "+counter+": ");
        aLbl=new JLabel("A");
        bLbl=new JLabel("B");
        cLbl=new JLabel("C");
        dLbl=new JLabel("D");

        questionFd=new JTextArea(2,15);
        questionFd.setBackground(new Color(237, 247, 241));
        questionFd.setLineWrap(true);
        aFd=new JTextField(15);
        bFd=new JTextField(15);
        cFd=new JTextField(15);
        dFd=new JTextField(15);

        saveBtn=new JButton("Save");
        saveBtn.setFocusPainted(false);
        saveBtn.setBackground(new Color(44, 128, 112));
        saveBtn.setForeground(Color.white);

        exitBtn=new JButton("Exit");
        exitBtn.setBackground(new Color(44, 128, 112));
        exitBtn.setForeground(Color.white);
        exitBtn.setFocusPainted(false);

        options = new JComboBox<>(opts);

        gbc.gridx=1;    gbc.gridy=1;    gbc.anchor=GridBagConstraints.EAST;
        pan1.add(questionLbl,gbc);
        gbc.gridy=2;
        pan1.add(aLbl,gbc);
        gbc.gridy=3;
        pan1.add(bLbl,gbc);
        gbc.gridy=4;
        pan1.add(cLbl,gbc);
        gbc.gridy=5;
        pan1.add(dLbl,gbc);

        gbc.gridx=2;    gbc.gridy=1;
        gbc.gridwidth=3;    gbc.anchor=GridBagConstraints.WEST;
        pan1.add(questionFd,gbc);
        gbc.gridy=2;
        pan1.add(aFd,gbc);
        gbc.gridy=3;
        pan1.add(bFd,gbc);
        gbc.gridy=4;
        pan1.add(cFd,gbc);
        gbc.gridy=5;
        pan1.add(dFd,gbc);

        gbc.gridx=2;    gbc.gridy=7;
        gbc.gridwidth=1;
        pan1.add(saveBtn,gbc);
        gbc.gridx=4;    gbc.anchor=GridBagConstraints.EAST;
        pan1.add(exitBtn,gbc);

        gbc.gridx=1;    gbc.gridy=6;
        pan1.add(new JLabel("Correct Option:"),gbc);
        gbc.gridx=2;    gbc.anchor=GridBagConstraints.WEST;
        pan1.add(options,gbc);

        add(pan1,BorderLayout.CENTER);


        exitBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object fm=e.getSource();
        if(fm==exitBtn){
            new Screen1();
            dispose();
        } else if (fm==saveBtn) {
            statement = questionFd.getText();
            optFds[0]=aFd.getText();
            optFds[1]=bFd.getText();
            optFds[2]=cFd.getText();
            optFds[3]=dFd.getText();
            saveCrtOpt = (String) options.getSelectedItem();

            if(counter<till){
                saveQues();
                new QuestionsInputs(till,Title);
                dispose();
            }else{
                saveQues();
                JOptionPane.showMessageDialog(null,"Quiz created successfully");
                dispose();
            }

        }else if (e.getSource()==options) {
            saveCrtOpt = (String) options.getSelectedItem();
        }
    }

    void saveQues(){
       try(FileWriter zout = new FileWriter(Title+".txt",true)){
           zout.write(statement+System.lineSeparator());
           for (int i = 0; i < 4; i++) {
           zout.write(optFds[i]+System.lineSeparator());    }
           zout.write(saveCrtOpt+System.lineSeparator());
       }catch (Exception e){
           throw new RuntimeException(e);
       }

    }
}

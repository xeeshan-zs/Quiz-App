import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.MessageFormat;
import java.util.Scanner;

public class CreateQuiz extends JFrame implements ActionListener {

    JLabel titleLbl,totalQuesLbl,imglbl;
    JTextField titleFd,totalQuesFd;
    JTextArea statementFd;
    JTextField[] optFd = new JTextField[4];
    JButton nextBtn,nextBtn2;
    JPanel midPan,quesPan;
    String title,totalQues;


    CreateQuiz(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.white);

        midPan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc =  new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Quiz icon.png"));
        imglbl = new JLabel(i1);
        add(imglbl,BorderLayout.NORTH);

        titleLbl = new JLabel("Quiz Title: ");
        totalQuesLbl = new JLabel("Total number of questions:");

        titleFd = new JTextField(15);
        totalQuesFd = new JTextField(15);

        nextBtn = new JButton("Next");
        nextBtn.setFocusPainted(false);
        nextBtn.setBackground(new Color(44, 128, 112));
        nextBtn.setForeground(Color.white);

        gbc.gridx=0;    gbc.gridy=0;
        gbc.anchor = GridBagConstraints.EAST;
        midPan.add(titleLbl,gbc);
        gbc.gridy=1;
        midPan.add(totalQuesLbl,gbc);
        gbc.gridx=1;    gbc.gridy=0;
        gbc.gridwidth=3;    gbc.anchor=GridBagConstraints.CENTER;
        midPan.add(titleFd,gbc);
        gbc.gridy=1;
        midPan.add(totalQuesFd,gbc);
        gbc.gridx=1;    gbc.gridy=2;
        gbc.gridwidth=1;
        midPan.add(nextBtn,gbc);



        add(midPan,BorderLayout.CENTER);

        nextBtn.addActionListener(this);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==nextBtn){
            if((titleFd.getText().isEmpty()||totalQuesFd.getText().isEmpty())){
                JOptionPane.showMessageDialog(null,"Fill all fields","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(found()){
                JOptionPane.showMessageDialog(null,"This name already exists \n Use a  new Quiz name","Quiz Name error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try (FileWriter zout = new FileWriter("Forms.txt", true)){
                title = titleFd.getText();
                zout.write(title+System.lineSeparator());
            } catch (Exception et){
                JOptionPane.showMessageDialog(null,"Error While saving file name","Error",JOptionPane.ERROR_MESSAGE);
            }
            totalQues = totalQuesFd.getText();
            new QuestionsInputs(Integer.parseInt(totalQues),title);
            dispose();
        }
    }

    boolean found(){
        File rd = new File("Forms.txt");
        try(Scanner zin =new Scanner(rd)){
            while (zin.hasNextLine()){
                String find = zin.nextLine();
                if(find.equals(titleFd.getText())){
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen1 extends JFrame implements ActionListener {

    JPanel leftPan,rightPan;
    JLabel lbl1,imglbl;
    JButton stdBtn,tchBtn;

    Screen1(){
        super("Quiz App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100,530);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        leftPan = new JPanel(new BorderLayout());
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz.jpeg"));
        imglbl = new JLabel(i1);
        leftPan.setSize(600,400);

        leftPan.add(imglbl,BorderLayout.WEST);
        add(leftPan,BorderLayout.WEST);


        rightPan = new JPanel(new GridBagLayout());

        GridBagConstraints gbc =  new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);


        lbl1 = new JLabel("Are you a student or teacher...?");
        lbl1.setForeground(new Color(44, 128, 112));
        lbl1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        stdBtn = new JButton("Student");
        stdBtn.setBackground(new Color(44, 128, 112));
        stdBtn.setForeground(Color.white);
        stdBtn.setFocusPainted(false);
        stdBtn.addActionListener(this);
        tchBtn = new JButton("Teacher");
        tchBtn.setBackground(new Color(44, 128, 112));
        tchBtn.setForeground(Color.white);
        tchBtn.setFocusPainted(false);
        tchBtn.addActionListener(this);

        gbc.gridx=1;    gbc.gridy=1;
        gbc.gridwidth = 5;  gbc.anchor=GridBagConstraints.CENTER;
        rightPan.add(lbl1,gbc);

        gbc.gridx=4;    gbc.gridy=2;
        gbc.gridwidth=1;    gbc.anchor=GridBagConstraints.CENTER;
        rightPan.add(stdBtn,gbc);

        gbc.gridx=5;    gbc.anchor=GridBagConstraints.CENTER;
        rightPan.add(tchBtn,gbc);
        rightPan.setSize(600,400);

        rightPan.setBackground(Color.WHITE);

        add(rightPan,BorderLayout.CENTER);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if(ob==stdBtn){
            new StdData();
            dispose();
        } else if (ob==tchBtn) {
           new LoginScreen();
            dispose();
        }

    }
}
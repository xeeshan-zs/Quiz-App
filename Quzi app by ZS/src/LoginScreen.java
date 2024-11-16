import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginScreen extends JFrame implements ActionListener {

    public String User;
    JPanel pan1;
    JLabel LginLbl,PassLbl,signUpComplete,forgetPass;
    JTextField LginFd;
    JPasswordField PassFd;
    JButton LginBtn,SignUpBtn;


    LoginScreen(){
        super("Login Page");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        pan1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        LginLbl = new JLabel("Username:");
        //LginLbl.setForeground(Color.white);
        PassLbl = new JLabel("Password:");
        //PassLbl.setForeground(Color.white);
        signUpComplete = new JLabel("Sign-Up Completed");
        signUpComplete.setFont(new Font("Serif",Font.PLAIN,12));
        signUpComplete.setForeground(Color.BLUE);
        forgetPass = new JLabel("forget password ?");
        forgetPass.setFont(new Font("Serif",Font.ITALIC,11));
        forgetPass.setForeground(Color.red);

        LginFd = new JTextField(15);
        PassFd = new JPasswordField(15);

        LginBtn = new JButton("Log-in");
        LginBtn.setBackground(new Color(44, 128, 112));
        LginBtn.setForeground(Color.white);
        SignUpBtn = new JButton("Sign-up");
        SignUpBtn.setBackground(new Color(44, 128, 112));
        SignUpBtn.setForeground(Color.white);

        gbc.gridx=0;    gbc.gridy=0;
        pan1.add(LginLbl,gbc);

        gbc.gridx=1;    gbc.gridy=0;    gbc.gridwidth=2;
        pan1.add(LginFd,gbc);

        gbc.gridx=0;    gbc.gridy=1;    gbc.gridwidth=1;
        pan1.add(PassLbl,gbc);

        gbc.gridx=1;    gbc.gridy=1;    gbc.gridwidth=2;
        pan1.add(PassFd,gbc);

        gbc.gridx=1;    gbc.gridy=4;    gbc.gridwidth=1;
        pan1.add(SignUpBtn,gbc);

        gbc.gridx=2;    gbc.gridy=4;    gbc.gridwidth=1;
        pan1.add(LginBtn,gbc);

        gbc.gridx=1;    gbc.gridy=3;    gbc.gridwidth=1;
        pan1.add(signUpComplete,gbc);
        signUpComplete.setVisible(false);

        gbc.gridx=2;    gbc.gridy=2;    gbc.gridwidth=2;
        pan1.add(forgetPass,gbc);

        add(pan1,BorderLayout.CENTER);
        pan1.setBackground(Color.white);

        SignUpBtn.addActionListener(this);
        LginBtn.addActionListener(this);
        forgetPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ForgetPass();
                dispose();
            }
        });

        setVisible(true);


    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==SignUpBtn){
            new SignUpScreen();
            dispose();
        }else if(e.getSource()==LginBtn){
            login();
        }

    }

    void login(){

        String username = LginFd.getText(),
                password = String.valueOf(PassFd.getPassword());

        File read = new File("Login.txt");
        try {
            Scanner obj = new Scanner(read);
            while(obj.hasNext()){
                String name = obj.nextLine();
                String user = obj.nextLine();
                String pass = obj.nextLine();

                if(username.equals(user)){
                    if( pass.equals(password)) {
                        //JOptionPane.showMessageDialog(null, "Bingo");
                        User = user;
//                        new CreateQuiz();
                        new SelectQuizOrGrade();
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null,
                                "Wrong Password\nTry again",
                                "Error message",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JOptionPane.showMessageDialog(null,
                "Login not found",
                "Error message",
                JOptionPane.ERROR_MESSAGE);
    }
}
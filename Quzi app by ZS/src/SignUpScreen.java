import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SignUpScreen extends JFrame implements ActionListener {
    JLabel nameLbl,userNameLbl,passLbl,confirmPassLbl;
    JTextField nameFd,userNameFd;
    JButton signUpBtn;
    JPasswordField passFd,confirmPassFd;
    JPanel pan;

    SignUpScreen(){
        super("Sign-Up");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        setLayout(new BorderLayout());
        pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        nameLbl = new JLabel("Name:");
        nameLbl.setForeground(Color.white);
        userNameLbl = new JLabel("User Name:");
        userNameLbl.setForeground(Color.white);
        passLbl = new JLabel("Password:");
        passLbl.setForeground(Color.white);
        confirmPassLbl = new JLabel("Confirm Pass:");
        confirmPassLbl.setForeground(Color.white);

        nameFd = new JTextField(15);
        userNameFd = new JTextField(15);
        passFd = new JPasswordField(15);
        confirmPassFd = new JPasswordField(15);

        signUpBtn = new JButton("Sign Up");
        signUpBtn.setBackground(Color.white);
        signUpBtn.setFocusPainted(false);

        gbc.gridx=0;    gbc.gridy=0;    gbc.anchor=GridBagConstraints.EAST;
        pan.add(nameLbl,gbc);

        gbc.gridx=0;    gbc.gridy=1;
        pan.add(userNameLbl,gbc);

        gbc.gridx=0;    gbc.gridy=2;
        pan.add(passLbl,gbc);

        gbc.gridx=0;    gbc.gridy=3;
        pan.add(confirmPassLbl,gbc);

        gbc.gridx=1;    gbc.gridy=0;    gbc.gridwidth=4;
        pan.add(nameFd,gbc);

        gbc.gridx=1;    gbc.gridy=1;
        pan.add(userNameFd,gbc);

        gbc.gridx=1;    gbc.gridy=2;
        pan.add(passFd,gbc);

        gbc.gridx=1;    gbc.gridy=3;
        pan.add(confirmPassFd,gbc);

        gbc.gridx=2;    gbc.gridy=4;    gbc.gridwidth=1;
        pan.add(signUpBtn,gbc);

        pan.setBackground(new Color(39, 72, 87));

        add(pan,BorderLayout.CENTER);
        signUpBtn.addActionListener(this);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpBtn) {
            if(isFilled()) {

                if(userIsNew()){
                    if (String.valueOf(passFd.getPassword()).equals(String.valueOf(confirmPassFd.getPassword()))) {
                        saveInfo();
                        LoginScreen fm = new LoginScreen();
                        fm.signUpComplete.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Password don't match..!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,
                            "This user name already exists\n   try another",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(null,
                        "Fill all the fields...!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    boolean isFilled(){
        if(!userNameFd.getText().isEmpty()){
            if(!nameFd.getText().isEmpty()){
                if(!String.valueOf(passFd.getPassword()).isEmpty()){
                    if(!String.valueOf(confirmPassFd.getPassword()).isEmpty()){
                        return true;
                    }else {return false;}
                }else {return false;}
            }else {return false;}
        }else {return false;}

    }

    void saveInfo(){
        String name = nameFd.getText() , user = userNameFd.getText(),
                pass = String.valueOf(passFd.getPassword());

            try (FileWriter zout = new FileWriter("login.txt", true)) {
                zout.write(name +System.lineSeparator() );
                zout.write(user +System.lineSeparator() );
                zout.write(pass +System.lineSeparator() );

            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    boolean userIsNew(){

        File read = new File("login.txt");
        try {
            Scanner obj = new Scanner(read);
            while(obj.hasNext()){
                obj.nextLine();
                String user = obj.nextLine();
                obj.nextLine();

                if(user.equals(userNameFd.getText())){
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StdData extends JFrame implements ActionListener {

    JLabel toplbl, namelbl, classlbl, rolllbl;
    JTextField namefd, classfd, rollfd;
    JButton backbtn, startbtn;
    JPanel topPan, midPan;


    StdData() {
        super("Student Data");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.white);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Quiz icon.png"));
        toplbl = new JLabel(i1);

        topPan = new JPanel(new BorderLayout());
        topPan.add(toplbl, BorderLayout.CENTER);
        topPan.setPreferredSize(new Dimension(800, 267));

        add(topPan, BorderLayout.NORTH);

        namelbl = new JLabel("Name: ");
        namelbl.setFont(new Font("Times New Roman",Font.BOLD,13));
        classlbl = new JLabel("Class: ");
        classlbl.setFont(new Font("Times New Roman",Font.BOLD,13));
        rolllbl = new JLabel("Roll No. ");
        rolllbl.setFont(new Font("Times New Roman",Font.BOLD,13));

        namefd = new JTextField(15);
        classfd = new JTextField(15);
        rollfd = new JTextField(15);

        backbtn = new JButton("Back");
        backbtn.setBackground(new Color(44, 128, 112));
        backbtn.setForeground(Color.white);
        backbtn.setFocusPainted(false);

        startbtn = new JButton("Next");
        startbtn.setBackground(new Color(44, 128, 112));
        startbtn.setForeground(Color.white);
        startbtn.setFocusPainted(false);

        midPan = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        midPan.add(namelbl, gbc);

        gbc.gridy = 2;
        midPan.add(classlbl, gbc);

        gbc.gridy = 3;
        midPan.add(rolllbl, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 4;
        midPan.add(namefd, gbc);

        gbc.gridy = 2;
        midPan.add(classfd, gbc);

        gbc.gridy = 3;
        midPan.add(rollfd, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        midPan.add(backbtn, gbc);

        gbc.gridx = 2;
        midPan.add(startbtn, gbc);

        add(midPan, BorderLayout.CENTER);

        backbtn.addActionListener(this);
        startbtn.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backbtn) {
            new Screen1();
            dispose();
        } else if (e.getSource() == startbtn) {
            if (namefd.getText().isEmpty() || classfd.getText().isEmpty() || rollfd.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }
            new SelectQuizOrGrade(namefd.getText(), classfd.getText(), rollfd.getText());
            dispose();
        }
    }
}

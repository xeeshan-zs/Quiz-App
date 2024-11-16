import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen1_1 extends JFrame implements ActionListener {

    JPanel topPan, rightPan;
    JLabel lbl1, imglbl;
    JButton stdBtn, tchBtn;

    Screen1_1() {
        super("Quiz App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        topPan = new JPanel(new BorderLayout());
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz.jpeg"));
        imglbl = new JLabel(i1);
        topPan.add(imglbl, BorderLayout.CENTER);

        topPan.setPreferredSize(new Dimension(800, (int) (650 * 0.75))); // 75% of 650px height -> 487px
        add(topPan, BorderLayout.NORTH);

        rightPan = new JPanel(new GridBagLayout());
        rightPan.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);

        lbl1 = new JLabel("Are you a student or teacher...?");
        lbl1.setForeground(new Color(44, 128, 112));
        lbl1.setFont(new Font("Times New Roman", Font.BOLD, 30));

        stdBtn = new JButton("Student");
        stdBtn.setBackground(new Color(44, 128, 112));
        stdBtn.setForeground(Color.WHITE);
        stdBtn.setFocusPainted(false);
        stdBtn.addActionListener(this);

        tchBtn = new JButton("Teacher");
        tchBtn.setBackground(new Color(44, 128, 112));
        tchBtn.setForeground(Color.WHITE);
        tchBtn.setFocusPainted(false);
        tchBtn.addActionListener(this);

        gbc.gridx = 0;    gbc.gridy = 0;
        gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        rightPan.add(lbl1, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.CENTER;
        rightPan.add(stdBtn, gbc);

        gbc.gridx = 1;
        rightPan.add(tchBtn, gbc);

        add(rightPan, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if (ob == stdBtn) {
            new StdData();
            dispose();
        } else if (ob == tchBtn) {
            new LoginScreen();
            dispose();
        }
    }
}

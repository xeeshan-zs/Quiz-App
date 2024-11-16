import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SelectQuizOrGrade extends JFrame implements ActionListener {

    JLabel lbl1, topLbl;
    JButton quizBtn, gradeBtn;
    JPanel midPan, topPan;
    private String Name, Class, Roll,button;
    SelectQuizOrGrade(){
        gui("Create Quiz");

    }

    SelectQuizOrGrade(String Name, String Class, String Roll) {
        super("Selection Screen");
        this.Name = Name;
        this.Class = Class;
        this.Roll = Roll;

        gui("Quiz");
    }
    void gui(String button){
        this.button=button;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.white);



        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Quiz icon.png"));
        topLbl = new JLabel(i1);

        topPan = new JPanel(new BorderLayout());
        topPan.add(topLbl, BorderLayout.CENTER);
        topPan.setPreferredSize(new Dimension(800, 267));

        add(topPan, BorderLayout.NORTH);

        lbl1 = new JLabel("Select any option");
        lbl1.setFont(new Font("Agency FB", Font.BOLD, 26));
        lbl1.setForeground(new Color(44, 128, 112));

        quizBtn = new JButton(button);
        quizBtn.setFocusPainted(false);
        quizBtn.setBackground(new Color(44, 128, 112));
        quizBtn.setForeground(Color.white);

        gradeBtn = new JButton("Grades");
        gradeBtn.setForeground(Color.white);
        gradeBtn.setBackground(new Color(44, 128, 112));
        gradeBtn.setFocusPainted(false);

        midPan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        midPan.add(lbl1, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        midPan.add(quizBtn, gbc);

        gbc.gridx = 1;
        midPan.add(gradeBtn, gbc);

        add(midPan, BorderLayout.CENTER);

        setVisible(true);
        quizBtn.addActionListener(this);
        gradeBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quizBtn) {
            if (Objects.equals(button, "Quiz")) {
                new Rules(Name, Class, Roll);
            } else {
                new CreateQuiz();
            }
            dispose();
        } else if (e.getSource() == gradeBtn) {
            if (Objects.equals(button, "Quiz")) {
                new Grade(Name,Class,Roll);
            }else{
                new Grade();
            }
            dispose();

        }
    }
}

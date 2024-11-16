import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JFrame implements ActionListener {

    JButton nextBtn, backBtn;
    JPanel topPan,midPan,btmPan;
    private String Name,Class,Roll;

    Rules(String Name,String Class, String Roll){
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        topPan = new JPanel(new GridBagLayout());
        topPan.setBackground(Color.white);
        midPan = new JPanel(new GridBagLayout());
        midPan.setBackground(Color.white);
        btmPan = new JPanel(new GridLayout(3,11));

        this.Name=Name;
        this.Class=Class;
        this.Roll=Roll;


        JLabel heading = new JLabel("Welcome to Quiz App");
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 28));
        heading.setForeground(new Color(44, 128, 112));
        topPan.add(heading);
        add(topPan,BorderLayout.NORTH);

        JLabel rules = new JLabel();
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setText(
                "<html>"+
                        "1. You are trained to be a programmer and not a story teller, answer point to point" + "<br><br>" +
                        "2. Do not unnecessarily smile at the person sitting next to you, they may also not know the answer" + "<br><br>" +
                        "3. You may have lot of options in life but here all the questions are compulsory" + "<br><br>" +
                        "4. Crying is allowed but please do so quietly." + "<br><br>" +
                        "5. Only a fool asks and a wise answers (Be wise, not otherwise)" + "<br><br>" +
                        "6. Do not get nervous if your friend is answering more questions, may be he/she is trying tuka strategy" + "<br><br>" +
                        "7. Brace yourself, this paper is not for the faint hearted" + "<br><br>" +
                        "8. Good Luck" + "<br><br>" +
                        "<html>"
        );

        midPan.add(rules);
        add(midPan,BorderLayout.CENTER);


        for (int i = 0; i < 3; i++) {
            btmPan.add(new JLabel("  "));

        }

        backBtn = new JButton("Back");
        backBtn.setBackground(new Color(44, 128, 112));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(this);
        btmPan.add(backBtn);
        for (int i = 0; i < 3; i++) {
            btmPan.add(new JLabel("  "));

        }

        nextBtn = new JButton("Next");
        nextBtn.setBackground(new Color(44, 128, 112));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFocusPainted(false);
        nextBtn.addActionListener(this);
        btmPan.add(nextBtn);
        for (int i = 0; i < 23; i++) {
            btmPan.add(new JLabel("  "));

        }
        btmPan.setBackground(Color.white);

        add(btmPan,BorderLayout.SOUTH);

        setSize(800, 650);
        setVisible(true);
        setLocationRelativeTo(null);
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == nextBtn) {
            new Selection(Name,Class,Roll);
            dispose();

        } else if(ae.getSource()== backBtn) {
            new SelectQuizOrGrade(Name,Class,Roll);
            dispose();

        }
    }
}

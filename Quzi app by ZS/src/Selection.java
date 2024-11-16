import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Selection extends JFrame implements ActionListener {

    JPanel topPan, midPan, btmPan;
    JButton nextBtn, backBtn;
    JLabel lbl1, topLbl;
    JComboBox<String> quizzes;
    ArrayList<String> quizList = new ArrayList<>();
    private String Name, Class, Roll;
    private String selected;

    Selection(String Name, String Class, String Roll) {
        super("Selection");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.Name = Name;
        this.Class = Class;
        this.Roll = Roll;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Quiz icon.png"));
        topLbl = new JLabel(i1);

        topPan = new JPanel(new BorderLayout());
        topPan.add(topLbl, BorderLayout.CENTER);
        topPan.setPreferredSize(new Dimension(800, 267));

        add(topPan, BorderLayout.NORTH);

        // Middle panel setup with label and combo box
        midPan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        lbl1 = new JLabel("Select any quiz from following:");
        readQuiz();

        quizzes = new JComboBox<>(quizList.toArray(new String[0]));

        // Customize JComboBox appearance
        quizzes.setBackground(new Color(230, 240, 250));
        quizzes.setForeground(new Color(44, 128, 112));
        quizzes.setFont(new Font("SansSerif", Font.PLAIN, 14));
        quizzes.setBorder(BorderFactory.createLineBorder(new Color(44, 128, 112), 1));
        quizzes.setPreferredSize(new Dimension(250, 30));

        quizzes.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel) {
                    JLabel label = (JLabel) renderer;
                    label.setBorder(new EmptyBorder(5, 10, 5, 10));  // Adds padding
                }
                return renderer;
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 1;
        midPan.add(lbl1, gbc);
        gbc.gridy = 2;
        midPan.add(quizzes, gbc);

        btmPan = new JPanel(new GridLayout(3, 11));
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

        add(btmPan, BorderLayout.SOUTH);
        add(midPan, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextBtn) {
            this.selected = (String) quizzes.getSelectedItem();
            getQuestions();
            dispose();
        } else if (e.getSource() == backBtn) {
            new Rules(Name, Class, Roll);
            dispose();
        }
    }

    void readQuiz() {
        File fl = new File("Forms.txt");
        try {
            Scanner zin = new Scanner(fl);
            while (zin.hasNextLine()) {
                String quizName = zin.nextLine().trim();
                if (!quizName.isEmpty()) {
                    quizList.add(quizName);
                }
            }
            zin.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Quiz file not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void getQuestions() {
        ArrayList<Object> questionsList = new ArrayList<>();
        try {
            File ob = new File(selected + ".txt");
            Scanner rd = new Scanner(ob);

            while (rd.hasNextLine()) {
                Questions q = new Questions();
                q.question_statement = rd.nextLine();
                for (int k = 0; k < 4; k++) {
                    q.options[k] = rd.nextLine();
                }
                q.copt = rd.nextLine();
                questionsList.add(q);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "File not found: " + selected + ".txt", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Questions[] questionsArray = questionsList.toArray(new Questions[0]);
        new RealDeal(questionsArray, questionsArray.length,Name,Class,Roll,selected);
        dispose();
    }
}

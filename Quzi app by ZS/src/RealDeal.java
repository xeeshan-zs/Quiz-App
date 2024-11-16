import javax.naming.Name;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class RealDeal extends JFrame implements ActionListener {

    JLabel questionLbl;
    JLabel[] optionLbl = new JLabel[4];
    JButton nextBtn, submitBtn;
    JPanel midPan;
    JRadioButton[] optRadio = new JRadioButton[4];
    ButtonGroup radioGroup = new ButtonGroup();
    String[] rawOptions = {"A", "B", "C", "D"};
    Questions[] questions;
    int currentQuestionIndex = 0;
    int marks = 0;
    int totalQuestions;
    String Name,Class,Roll,Quiz;

    public RealDeal(Questions[] questions, int n,String Name,String Class,String Roll,String Quiz) {
        super("Quiz Starts");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.white);

        this.questions = questions;
        this.totalQuestions = n;
        this.Name=Name;
        this.Class=Class;
        this.Roll=Roll;
        this.Quiz=Quiz;

        for (int i = 0; i < 4; i++) {
            optRadio[i] = new JRadioButton(rawOptions[i]);
            optRadio[i].setActionCommand(rawOptions[i]);
            radioGroup.add(optRadio[i]);
        }

        midPan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        questionLbl = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        midPan.add(questionLbl, gbc);

        for (int i = 0; i < 4; i++) {
            optionLbl[i] = new JLabel();
            gbc.gridx = 1;
            gbc.gridy = 1 + i;
            gbc.gridwidth = 1;
            midPan.add(optionLbl[i], gbc);

            gbc.gridx = 0;
            midPan.add(optRadio[i], gbc);
        }

        nextBtn = new JButton("Next");
        nextBtn.setFocusPainted(false);
        nextBtn.setBackground(new Color(44, 128, 112));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.addActionListener(this);

        submitBtn = new JButton("Submit");
        submitBtn.setFocusPainted(false);
        submitBtn.setBackground(new Color(44, 128, 112));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.addActionListener(this);

        gbc.gridy = 5;
        gbc.gridx = 0;
        midPan.add(nextBtn, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        midPan.add(submitBtn, gbc);

        add(midPan, BorderLayout.CENTER);

        loadQuestion(currentQuestionIndex);
        setVisible(true);
    }

    private void loadQuestion(int index) {
        if (index < totalQuestions) {
            Questions currentQuestion = questions[index];
            questionLbl.setText("Q" + (index + 1) + ": " + currentQuestion.question_statement);

            // Set option text
            for (int i = 0; i < 4; i++) {
                optionLbl[i].setText(currentQuestion.options[i]);
                optRadio[i].setSelected(false);
            }
            radioGroup.clearSelection();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == nextBtn) {
            String selectedOption = radioGroup.getSelection() != null ?
                    radioGroup.getSelection().getActionCommand() : "";
            if (selectedOption.equals(questions[currentQuestionIndex].copt)) {
                marks++;
            }

            currentQuestionIndex++;
            if (currentQuestionIndex < totalQuestions) {
                loadQuestion(currentQuestionIndex);
            } else {
                showScore();
            }
        } else if (source == submitBtn) {
            showScore();
        }
    }

    private void showScore() {
        float percentage = ((float) marks / totalQuestions) * 100;

        JOptionPane.showMessageDialog(this,
                "Quiz Completed!\nYour Score: " + marks + " out of " +
                        totalQuestions+"\nPercentage: "+String.format("%.2f", percentage)+"%");

        try(FileWriter ob = new FileWriter("Grades.txt",true)){
            ob.write("\nName: "+ Name +System.lineSeparator()+
                    "Class: "+Class+System.lineSeparator()+
                    "Roll Number: "+ Roll+System.lineSeparator()+
                    "Quiz Name: "+ Quiz+System.lineSeparator()+
                    "Marks: " +marks+"/"+totalQuestions+ System.lineSeparator()+
                    "Percentage: "+percentage+ System.lineSeparator()
            );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error in saving score :(");
        }

        dispose();
    }
}

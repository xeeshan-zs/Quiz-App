import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Grade extends JFrame implements ActionListener {

    JLabel  topLbl;
    JTextArea Fd1G;
    JPanel midPan, topPan;
    JButton closeBtn,backBtn;
    private String Name, Class, Roll;


    Grade(){
        super("Grade");
         Roll = null;
        gui();
    }

    Grade(String Name, String Class, String Roll) {
        super("Grade");

        this.Name = Name;
        this.Class = Class;
        this.Roll = Roll;

        gui();

    }
    void gui(){
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

        midPan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        backBtn = new JButton("Back");
        backBtn.setBackground(new Color(44, 128, 112));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        closeBtn = new JButton("Close");
        closeBtn.setBackground(new Color(44, 128, 112));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFocusPainted(false);

        if(Roll != null) {
            Fd1G = new JTextArea(String.valueOf(findScore()));
        }else {
            Fd1G = new JTextArea(String.valueOf(showAllScore()));
        }
        Fd1G.setFont(new Font("Serif", Font.PLAIN, 13));
        Fd1G.setEditable(false);
        Fd1G.setBackground(new Color(230, 240, 250));
        Fd1G.setForeground(new Color(44, 128, 112));

        JScrollPane scrollPane = new JScrollPane(Fd1G);
        scrollPane.setPreferredSize(new Dimension(300, 250));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        gbc.gridx=0;    gbc.gridy=0;
        gbc.gridwidth=10;
        midPan.add(scrollPane,gbc);

        gbc.gridy=1;   gbc.gridx=1; gbc.gridwidth=1;
        gbc.anchor=GridBagConstraints.EAST;
        midPan.add(backBtn,gbc);

        gbc.gridx=2;
        midPan.add(closeBtn,gbc);



        add(midPan,BorderLayout.CENTER);
        closeBtn.addActionListener(this);
        backBtn.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backBtn){
            if(Roll != null) {
                new StdData();
            }else{
                new SelectQuizOrGrade();
            }
            dispose();
        } else if (e.getSource()==closeBtn) {
            dispose();
        }
    }
    StringBuilder findScore(){
        StringBuilder all = new StringBuilder();

        File fl = new File("Grades.txt");
        try(Scanner zin = new Scanner(fl)){

            while(zin.hasNextLine()){
                zin.nextLine();
                String Name = zin.nextLine();
                String Class = zin.nextLine();
                String Roll = zin.nextLine();
                String Quiz = zin.nextLine();
                String Marks = zin.nextLine();
                String Percentage = zin.nextLine();

                if(Roll.equals("Roll Number: "+this.Roll)){
                    all.append(Name).append("\n");
                    all.append(Class).append("\n");
                    all.append(Roll).append("\n");
                    all.append(Quiz).append("\n");
                    all.append(Marks).append("\n");
                    all.append(Percentage).append("\n\n");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(all.isEmpty()){
            all.append("No record found");
        }
        return all;
    }

    StringBuilder showAllScore(){
        StringBuilder all = new StringBuilder();
        File fl = new File("Grades.txt");
        try(Scanner zin = new Scanner(fl)){

            while(zin.hasNextLine()){
                zin.nextLine();
                String Name = zin.nextLine();
                String Class = zin.nextLine();
                String Roll = zin.nextLine();
                String Quiz = zin.nextLine();
                String Marks = zin.nextLine();
                String Percentage = zin.nextLine();

                all.append(Name).append("\n");
                all.append(Class).append("\n");
                all.append(Roll).append("\n");
                all.append(Quiz).append("\n");
                all.append(Marks).append("\n");
                all.append(Percentage).append("\n\n");

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(all.isEmpty()){
            all.append("No record found");
        }
        return all;
    }
}

package home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import static home.Marks.grades;

public class UI {
    JFrame frame,homeFrame,gradeFrame,resultFrame;
    JButton signUP,logIN,next,logOut,add,go;
    JTextField roll,grade;
    JLabel label,label2,label3,label4,label5,label6;
    FileContents fileContents;
    int user;
    UI() throws Exception {
        frame = new JFrame("Result Checker");
        frame.setSize(480,480);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setBackground(Color.lightGray);

        label = new JLabel("Result Checker");
        label.setBounds(180,50,150,25);

        roll = new JTextField("Enter Roll");
        roll.setBounds(155,100,150,25);
        roll.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                roll.setText("");
            }
        });

        logIN = new JButton("Log In");
        logIN.setBounds(155,160,150,35);
        logIN.setBackground(Color.darkGray);
        logIN.setForeground(Color.white);
        logIN.addActionListener(this::clickedLogin);

        signUP = new JButton("Sign Up");
        signUP.setBounds(155,220,150,35);
        signUP.setBackground(Color.darkGray);
        signUP.setForeground(Color.white);
        signUP.addActionListener(this::clickedSignup);

        fileContents = new FileContents();

        frame.add(signUP); frame.add(logIN);
        frame.add(roll); frame.add(label);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void clickedLogin(ActionEvent clicked){
        String get_Roll = roll.getText();
        int getRoll = Integer.parseInt(get_Roll);
        boolean state = true;
        for(int roll : fileContents.allUsers){
            if(roll == getRoll){
                state = false;
                JOptionPane.showMessageDialog(frame,"Logged In");
                home(getRoll);
                break;
            }
        }
        if(state){
            JOptionPane.showMessageDialog(frame,"Sign Up First");
        }
    }

    public void clickedSignup(ActionEvent clicked){
        String get_Roll = roll.getText();
        int getRoll = Integer.parseInt(get_Roll);
        boolean state = true;
        for(int i = 0; i < fileContents.allUsers.size(); i++){
            if(fileContents.allUsers.get(i) == getRoll){
                state = false;
                JOptionPane.showMessageDialog(frame,"Already Signed Up, Log In Now");
                break;
            }
        }

        if(state){
            try{
                fileContents.addUser(getRoll);
                JOptionPane.showMessageDialog(frame,"Signed Up, Logging In Now");
                home(getRoll);
            }
            catch(Exception e){
                System.out.println("Something is wrong");
            }
        }


    }

    JComboBox comboBox;
    JComboBox courseBox;

    public void home(int roll){
        user = roll;
        homeFrame = new JFrame("Result Checker");
        homeFrame.setSize(480,480);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setLayout(null);
        homeFrame.setBackground(Color.lightGray);

        label2 = new JLabel("User " + user);
        label2.setBounds(180,45,150,25);

        label3 = new JLabel("Select Semester");
        label3.setBounds(90,90,150,25);

        String[] thSemester = {"1.1","1.2","2.1","2.2","3.1","3.2","4.1","4.2"};
        comboBox = new JComboBox<>(thSemester);
        comboBox.setBounds(200, 90,90,20);

        next = new JButton("Next");
        next.setBounds(155,250,150,35);
        next.setBackground(Color.darkGray);
        next.setForeground(Color.white);
        next.addActionListener(this::clickedNext);

        logOut = new JButton("Log Out");
        logOut.setBounds(155,320,150,35);
        logOut.setBackground(Color.darkGray);
        logOut.setForeground(Color.white);
        logOut.addActionListener(this::clickedLogOut);

        homeFrame.add(comboBox); homeFrame.add(label2);
        homeFrame.add(next); homeFrame.add(label3);
        homeFrame.add(logOut);
        frame.setVisible(false);
        homeFrame.setVisible(true);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    int totalCourse;

    public void clickedNext(ActionEvent clicked){
        int thSem = comboBox.getSelectedIndex() + 1;
        start(thSem);
    }

    public void clickedLogOut(ActionEvent clicked){
        JOptionPane.showMessageDialog(homeFrame,"Logging Out");
        homeFrame.setVisible(false);
        gradeFrame.setVisible(false);
        resultFrame.setVisible(false);
        frame.setVisible(true);
    }

    int index;
    public void gradeInput(SortContents sortedContents){
        gradeFrame = new JFrame("Result Checker");
        gradeFrame.setSize(480,480);
        gradeFrame.setLocationRelativeTo(null);
        gradeFrame.setLayout(null);
        gradeFrame.setBackground(Color.lightGray);

        label4 = new JLabel("User " + user);
        label4.setBounds(180,45,150,25);

        label5 = new JLabel("Enter Grades");
        label5.setBounds(190,90,150,25);

        totalCourse = sortedContents.courseCodes.size();

        String[] comboArr = new String[totalCourse];

        int i = 0;
        for(String data : sortedContents.courseCodes){
            comboArr[i] = data;
            i++;
        }

        courseBox = new JComboBox<>(comboArr);
        courseBox.setBounds(30, 125,400,30);

        grade = new JTextField("F to A+");
        grade.setBounds(86,312,70,25);
        grade.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                grade.setText("");
            }
        });

        add = new JButton("Add");
        add.setBounds(220,308,150,35);
        add.setBackground(Color.darkGray);
        add.setForeground(Color.white);
        add.addActionListener(this::clickedAdd);

        go = new JButton("Result");
        go.setBounds(80,365,85,35);
        go.setBackground(Color.darkGray);
        go.setForeground(Color.white);
        go.addActionListener(this::clickedGo);

        logOut = new JButton("Log Out");
        logOut.setBounds(220,365,150,35);
        logOut.setBackground(Color.darkGray);
        logOut.setForeground(Color.white);
        logOut.addActionListener(this::clickedLogOut);


        gradeFrame.add(label4); gradeFrame.add(label5);
        gradeFrame.add(logOut); gradeFrame.add(courseBox);
        gradeFrame.add(add); gradeFrame.add(grade);
        gradeFrame.add(go);

        frame.setVisible(false);
        homeFrame.setVisible(false);
        gradeFrame.setVisible(true);
        gradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void clickedAdd(ActionEvent clicked){
        index = courseBox.getSelectedIndex();
        try{
            grades.remove(index);
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            grades.add(index,grade.getText());
        }
    }

    SortContents sortedContents_2;
    public void clickedGo(ActionEvent clicked) {
        try{
            if(grades.size() == totalCourse){
                Marks marks = new Marks(sortedContents_2);
                Calculation calculation = new Calculation();
                double cGPA = calculation.calculateCGPA();

                result(cGPA);

                System.out.println("\n----");
                System.out.println("Your achieved CGPA is : " + cGPA);   // final output
                System.out.println("----\n");
            }
            else{
                JOptionPane.showMessageDialog(gradeFrame,"Invalid Grades");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void start(int thSemester){
        try {
            SortContents sortedContents = new SortContents(thSemester);
            sortedContents_2 = sortedContents;
            gradeInput(sortedContents);

        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void result(double cGPA){
        resultFrame = new JFrame("Result Checker");
        resultFrame.setSize(480,480);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setLayout(null);
        resultFrame.setBackground(Color.lightGray);

        label4 = new JLabel("User " + user);
        label4.setBounds(180,45,150,25);

        label6 = new JLabel("Your GPA is " + cGPA);
        label6.setBounds(175,120,150,25);
        label6.setForeground(Color.red);


        logOut = new JButton("Log Out");
        logOut.setBounds(155,250,150,35);
        logOut.setBackground(Color.darkGray);
        logOut.setForeground(Color.white);
        logOut.addActionListener(this::clickedLogOut);


        resultFrame.add(label4); resultFrame.add(logOut);
        resultFrame.add(label6);

        frame.setVisible(false);
        homeFrame.setVisible(false);
        gradeFrame.setVisible(false);
        resultFrame.setVisible(true);
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

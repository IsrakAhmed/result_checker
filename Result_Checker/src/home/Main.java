package home;

/*      Created by Israk Ahmed on May 19 , 2022        */

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Calculation {
    /* Semesterly calculation is done here. */
    double cgpa = 0;
    ArrayList<Double> gpa;
    ArrayList<Integer> courseCredits;

    Calculation(){
        gpa = Marks.gpa;
        courseCredits = SortContents.courseCredits;
    }

    double calculateCGPA(){

        /* calculating the CGPA on the basis of all facts */

        int total_credits = 0;

        for(int credit : courseCredits){
            total_credits = total_credits + credit;
        }
        for(int i = 0; i < courseCredits.size(); i++){
            double temp = courseCredits.get(i) * gpa.get(i);
            cgpa = cgpa + temp;

        }//2037820103
        cgpa = cgpa / total_credits;  /* final line of calculation */

        return cgpa;
    }
}

class Marks{

    static ArrayList<Double> gpa = new ArrayList<>();
    public static ArrayList<String> grades = new ArrayList<>();

    SortContents sortedContents;

    /* getting obtained gpa based on grades */

    Marks(SortContents sortedContents) throws Exception{
        this.sortedContents = sortedContents;
        convertGradesIntoGPA();
    }

    /* taking the Grades as input from user --- according to courses */
    void convertGradesIntoGPA(){

        /* finding the deserving gpa according to grades */
        System.out.println("error" + " size " + grades.size());
        for(String grade : grades){
            if(grade.equals("A+")){
                gpa.add(4.0);
            }
            else if(grade.equals("A")){
                gpa.add(3.75);
            }
            else if(grade.equals("A-")){
                gpa.add(3.5);
            }
            else if(grade.equals("B+")){
                gpa.add(3.25);
            }
            else if(grade.equals("B")){
                gpa.add(3.0);
            }
            else if(grade.equals("B-")){
                gpa.add(2.75);
            }
            else if(grade.equals("C+")){
                gpa.add(2.5);
            }
            else if(grade.equals("C")){
                gpa.add(2.25);
            }
            else if(grade.equals("D")){
                gpa.add(2.0);
            }
            else if(grade.equals("F")){
                gpa.add(0.0);
            }
            else{
                gpa.add(0.0);
            }
        }

    }

}

class SortContents extends FileContents{

    /* sorting the contents of the file and adding only the required ones into new arrayLists */

    ArrayList<String> courseCodes = new ArrayList<>();
    static ArrayList<Integer> courseCredits = new ArrayList<>();

    int thSemester;

    SortContents(int thSemester) throws Exception{
        this.thSemester = thSemester;

        for(String data: fileContents_1) {
            int temp = Integer.parseInt(data.substring(0,1));
            String dataMain = data.substring(1);
            if(temp == thSemester){
                courseCodes.add(dataMain);
            }
        }
        for(int data: fileContents_2) {
            String temp = String.valueOf(data);
            int temp_N = Integer.parseInt(temp.substring(0,1));
            int dataMain = Integer.parseInt(temp.substring(1));

            if(temp_N == thSemester){
                courseCredits.add(dataMain);
            }

        }
    }

}

class FileContents{

    /* getting all the contents of the file here , and adding them into arrayLists */

    ArrayList<String> fileContents_1 = new ArrayList<>();
    ArrayList<Integer> fileContents_2 = new ArrayList<>();
    ArrayList<Integer> allUsers = new ArrayList<>();
    Files files;
    PrintWriter writer;

    FileContents() throws Exception {
        files = new Files();  // instance of the class Files.

        while(files.course_Codes.hasNextLine()){
            String temp_1 = files.course_Codes.nextLine();
            int temp_2 = files.course_Credits.nextInt();

            fileContents_1.add(temp_1);
            fileContents_2.add(temp_2);
        }

        while(files.registered_Users.hasNextInt()){
            int temp_3 = files.registered_Users.nextInt();
            allUsers.add(temp_3);
        }
    }
    void addUser(int roll){
        allUsers.add(roll);
        try{
            writer = new PrintWriter(files.registeredUsers);
            int iterator = 0;
            while(iterator < allUsers.size()){

                writer.println(allUsers.get(iterator));

                iterator++;
            }
            writer.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

class Files{

    File courseCodes = new File("courseCodes.txt"); // this file contains all the course codes in a sequential order according to syllabus.
    File courseCredits = new File("courseCredits.txt"); // this file contains all the credit points of the courses in a sequential order according to syllabus.
    File registeredUsers = new File("RegisteredUsers.txt");

    Scanner course_Codes;
    Scanner course_Credits;
    Scanner registered_Users;

    Files(){
        try{
            course_Codes = new Scanner(courseCodes);
            course_Credits = new Scanner(courseCredits);
            registered_Users = new Scanner(registeredUsers);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

public class Main {
    static UI ui;
    public static void main(String[] args) {

        try {
            ui = new UI();
        }
        catch (Exception e){
            System.out.println("Something is wrong"); // if any exception is thrown this line will print
        }

    }

    }

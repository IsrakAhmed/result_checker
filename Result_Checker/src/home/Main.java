package home;

/*      Created by Israk Ahmed on May 19 , 2022        */

import java.io.File;
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
        }
        cgpa = cgpa / total_credits;  /* final line of calculation */

        return cgpa;
    }
}

class Marks{

    ArrayList<Double> marks = new ArrayList<>();
    static ArrayList<Double> gpa = new ArrayList<>();
    ArrayList<Double> percents = new ArrayList<>();

    Scanner marksInput = new Scanner(System.in);

    SortContents sortedContents;

    /* taking the marks as input from user --- according to courses */

    Marks(SortContents sortedContents) throws Exception{
        this.sortedContents = sortedContents;
        System.out.println("Enter marks you achieved on the courses below");
        for(String data : sortedContents.courseCodes){
            System.out.print(data + " : ");
            double mark = marksInput.nextDouble();
            marks.add(mark);
        }
        convertMarksIntoGPA();
    }

    /* getting percentage on total marks */

    void convertMarksIntoGPA(){

        for(int i = 0; i < sortedContents.courseCredits.size(); i++){
            int credit = sortedContents.courseCredits.get(i);
            double mark = marks.get(i);
            double totalMark = credit * 25;
            double percentage = (mark * 100) / totalMark;
            percents.add(percentage);
        }

        /* finding the deserving gpa according to marks */

        for(double percent : percents){
            if(percent >= 80){
                gpa.add(4.0);
            }
            else if(percent >= 75 && percent < 80){
                gpa.add(3.75);
            }
            else if(percent >= 70 && percent < 75){
                gpa.add(3.5);
            }
            else if(percent >= 65 && percent < 70){
                gpa.add(3.25);
            }
            else if(percent >= 60 && percent < 65){
                gpa.add(3.0);
            }
            else if(percent >= 55 && percent < 60){
                gpa.add(2.75);
            }
            else if(percent >= 50 && percent < 55){
                gpa.add(2.5);
            }
            else if(percent >= 45 && percent < 50){
                gpa.add(2.25);
            }
            else if(percent >= 40 && percent < 45){
                gpa.add(2.0);
            }
            else if(percent < 40){
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

    FileContents() throws Exception {
        Files files = new Files();  // instance of the class Files.

        while(files.course_Codes.hasNextLine()){
            String temp_1 = files.course_Codes.nextLine();
            int temp_2 = files.course_Credits.nextInt();
            fileContents_1.add(temp_1);
            fileContents_2.add(temp_2);
        }
    }
}

class Files{

    File courseCodes = new File("courseCodes.txt"); // this file contains all the course codes in a sequential order according to syllabus.
    File courseCredits = new File("courseCredits.txt"); // this file contains all the credit points of the courses in a sequential order according to syllabus.

    Scanner course_Codes;
    Scanner course_Credits;

    Files() throws Exception{
        course_Codes = new Scanner(courseCodes);
        course_Credits = new Scanner(courseCredits);
    }
}

class Menu{
    /* Menu is shown from here , it gives many options to user to choose. */
    Menu(){
        System.out.println("W E L C O M E   M A T E"); // this is the very first line which will be shown as output.
    }
    void choiceMenu(){
        /* this is the main menu of this program. */
        System.out.println("Which semester result you want to see ? ");
        System.out.print("Semester : ");
    }

    void continuationMenu(){
        /* if anyone wants to see more results they continue without rerunning */
        System.out.println("Do you want to continue ?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Enter 1 or 2 : ");
    }
    void exit(){
        System.exit(0);     /* E X I T */
    }
}

public class Main {
    public static void main(String[] args) {

        try {
            Menu menu = new Menu();     // instance of the class Menu.

            label:
            while(true){

                menu.choiceMenu();    // the menu is showing here.

                Scanner inputTaker = new Scanner(System.in);
                int thSemester = inputTaker.nextInt(); // taking the choice of the user

                if(thSemester < 1 || thSemester > 8){
                    System.out.println("Enter a valid number");
                    continue label;
                }

                SortContents sortedContents = new SortContents(thSemester);
                Marks marks = new Marks(sortedContents);
                Calculation calculation = new Calculation();

                double cGPA = calculation.calculateCGPA();

                System.out.println("Your achieved CGPA is : " + cGPA);   // final output

                menu.continuationMenu();    /* checking if the user wants to continue or not */
                int choice = inputTaker.nextInt();

                if(choice == 2){
                    System.out.println("G O O D B Y E   M A T E");

                    menu.exit();      // calling exit
                }

            }
        }
        catch (Exception e){
            System.out.println("Something is wrong"); // if any exception is thrown this line will print
        }

    }
}

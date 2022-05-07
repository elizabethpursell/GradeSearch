/* 
Name:Elizabeth Pursell
Date: 3/11/2022
CSE 007 Spring 2022: Lab 6
Produce RandomGrades program that will create an array in descending order of students' grades and finds a specific grade in the list.
IDE Used: Visual Studio Code
*/
import java.util.Scanner;   //import Scanner, Random, and Arrays classes
import java.util.Random;
import java.util.Arrays;
public class RandomGrades{
    public static void main(String [] args){
        //create variables, scanner, and random number generator
        Scanner myScan = new Scanner(System.in);
        Random ran = new Random();
        int classSize = 1;
        int gradeInt = 0;
        char gradeChar = 'a';
        boolean intCheck = false;
        boolean gradeCheck = false;
        boolean userChar = false;
        boolean userInt = false;
        
        //do while loop to check if the user's input for array length/class size is an integer in range
        do{
            System.out.println("Enter the Class Size: ");

            //checks to see if the input is an integer
            intCheck = myScan.hasNextInt();
            if(intCheck){
                int temp = myScan.nextInt();

                //executes if integer is out of range
                if(temp < 5 || temp > 150){
                    intCheck = false;
                    String junk = myScan.nextLine();
                    System.out.println("Invalid input: Enter an integer in range.");
                }
                //executes if integer is in range
                else{
                    String junk = myScan.nextLine();
                    classSize = temp;
                    intCheck = true;
                }
            }
            //executes if user's input is not an integer
            else{
                System.out.println("Invalid input: Enter an integer.");
                String junk = myScan.nextLine();
                intCheck = false;
            }
        }while(!intCheck);      //keeps looping until an integer is inputted

        //creates array of the specified amount of students' grades, assigns random grades to students, prints array
        int [] gradesArray = new int [classSize];
        for(int index = 0; index < gradesArray.length; index++){
            gradesArray[index] = ran.nextInt(100);
        }
        System.out.println("Final Grades of CSE7: "  + Arrays.toString(gradesArray));

        /*I chose selection sorting because it makes more sense to me since
        it finds the minimum value and swaps it with the current element until it is sorted*/

        //sort array in descending order using selection sorting and print
        for(int i = 0; i < gradesArray.length - 1; i++){
            int indexMin = i;
            int min = gradesArray[i];
            for(int j = i + 1; j < gradesArray.length; j++){
                if(min < gradesArray[j]){
                    min = gradesArray[j];
                    indexMin = j;
                }
            }
            if(indexMin != i){
                gradesArray[indexMin] = gradesArray[i];
                gradesArray[i] = min;
            }
        }
        System.out.println("Descending CSE7 Grades: " + Arrays.toString(gradesArray));

        //calculate average grade of class and print with 3 decimal places
        int sum = 0;
        for(int index = 0; index < gradesArray.length; index++){
            sum = sum + gradesArray[index];
        }
        double averageGrade = (double) sum / gradesArray.length;
        System.out.print("Average Course Grade: ");
        System.out.printf("%1.3f", averageGrade);
        System.out.println();

        //do while loop to validate user's input for grade search
        do{
            System.out.println("Enter a Grade/Grade Range to Search For: ");
            intCheck = myScan.hasNextInt();

            //executes if user input is an integer
            if(intCheck){
                int tempInt = myScan.nextInt();

                //executes if user's integer is out of range
                if(tempInt < 0 || tempInt > 100){
                    gradeCheck = false;
                    String junk = myScan.nextLine();
                    System.out.println("Invalid input: Enter a new integer or character in range.");
                }
                //executes in user's integer is in range
                else{
                    String junk = myScan.nextLine();
                    gradeInt = tempInt;
                    gradeCheck = true;
                    userInt = true;
                }
            }
            //executes if user inputs anything this is not an integer
            else{
                char tempChar = myScan.nextLine().charAt(0);

                //executes if user's character is out of range
                if(tempChar < 'A' || tempChar > 'F' || tempChar == 'E'){
                    gradeCheck = false;
                    System.out.println("Invalid input: Enter a new integer or character in range.");
                }
                //executes if user's character is in range
                else{
                    gradeChar = tempChar;
                    gradeCheck = true;
                    userChar = true;
                }
            }
        }while(!gradeCheck);

        //create variables for binary comparison
        int binaryComparison = 0;
        int mid;
        int indexBin = -1;
        int low = 0;
        int high = gradesArray.length - 1;

        //executes binary search to find the user's integer if they inputted an integer
        if(userInt){
            while (high >= low){
                binaryComparison++;
                mid = (high + low) / 2;
                if(gradeInt > gradesArray[mid]){
                    low = mid + 1;
                }
                else if(gradeInt < gradesArray[mid]){
                    high = mid - 1;
                }
                else{
                    indexBin = mid;
                    break;
                }
            }
            //prints index of user's desired grade
            if(indexBin == -1){
                System.out.println("The grade " + gradeInt + " was not found in binary search.");
            }
            else{
                System.out.println("The grade " + gradeInt + " was found at index " + indexBin + " in " + binaryComparison + " iterations in binary search.");
            }
        }

        //creates variables for linear comparison
        int indexValueLin = -1;
        int gradeFound = 0;
        int indexLin;
        int gradeMax = 0;
        int gradeMin = 0;

        //executes linear search to find number of occurances of scores in inputted range
        if (userChar){
            //switch statement to assign grade ranges for each character
            switch(gradeChar){
                case 'A': gradeMin = 90; gradeMax = 100; break;
                case 'B': gradeMin = 80; gradeMax = 89; break;
                case 'C': gradeMin = 70; gradeMax = 79; break;
                case 'D': gradeMin = 60; gradeMax = 69; break;
                case 'F': gradeMin = 0; gradeMax = 59; break;
                default: System.out.println("Invalid Range."); break;
            }
            //for loop for linear comparison
            for(indexLin = 0; indexLin < gradesArray.length; indexLin++){
                if(gradesArray[indexLin] >= gradeMin && gradesArray[indexLin] <= gradeMax){
                    indexValueLin = indexLin;
                    gradeFound++;
                }
            }
            //prints number of times user's character was found
            if(indexValueLin == -1){
                System.out.println(gradeChar + " grade range was not found in linear search.");
            }
            else{
                System.out.println(gradeChar + " grade range was found " + gradeFound + " times in linear search.");
            }
        }
    }
}
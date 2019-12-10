import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Accounting3000 {
    static Scanner sc= new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        // System.in is the input(keyboard)
        String message="Welcome to Account 3000. Please choose an action.\n1. Print the balances\n2. Add new expense\n3. Add new earning\n0. Stop program\n";
        while(true){

            int choice= readChoice(message);
            if (choice == 0) {
                // stops the program
                break;
            }
            else if (choice == 1) {
               //read the input from the file
                String fileName = "earnings.txt";
                try {

                    File file = new File(fileName);
                    //file reader
                    FileReader fr = new FileReader(file);
                    //memory
                    BufferedReader br = new BufferedReader(fr);
                    // read lines one by one from BufferedReader
                    String line;
                    double balance= 0;
                    // it goes to txt file and reads line by line and stops when there is no line
                    while((line = br.readLine()) != null){
                        //process the line

                        String data[]= line.split(" ");
                        String type= data[0];
                        String name= data[1];

                        // we ues third element in the array and convert it
                        double amount= Double.parseDouble(data[2]);
                        if(type.equals("Earnings")){
                            balance+=amount;
                        }
                        else{
                            balance-=amount;
                        }
                    }
                    System.out.println("After all your expenses and earning, your balance is : "+balance);
                }catch (FileNotFoundException e){
                    System.out.println("File is not present in the path. maybe you need to add some Earnings or expenses first.");
                }

            } else if (choice == 2) {

                String name;

                System.out.println("What is the expense that you have got? ");
                name=sc.next();
                boolean numeric = true;
                try {
                    Double num = Double.parseDouble(name);
                } catch (NumberFormatException e) {
                    numeric = false;
                }
                if(numeric)
                    System.out.println("Wrong! You provided a number.");
                else{
                    double amount= readData("How much did you spend: ");
                    Expenses e= new Expenses(name, amount);
                    e.writeToFile();
                  }

            }
            else if (choice==3) {

                String name;
                while (true) {
                    System.out.println("What is the earning that you have got? ");
                    name = sc.next();
                    boolean numeric = true;
                    try {
                        Double num = Double.parseDouble(name);
                    } catch (NumberFormatException e) {
                        numeric = false;
                    }
                    if (numeric)
                        System.out.println("Wrong! You provided a number.");
                    else {
                        double amount = readData("How much did you earn: ");
                        Earnings e = new Earnings(name, amount);
                        e.writeToFile();
                    }

                }


            }


            }
        }


    public static double readData(String prompt){
        boolean success= false;
        double data=0;
        while (!success){// while loop ends when the user provides the correct value.
            try {
                System.out.println(prompt);
                data= sc.nextDouble(); // try 1 accept value= hi
                success=true;
            }catch (InputMismatchException e){
                // Catch will run when the user provides a string value.
                System.out.println("You have provided wrong type of value. Please try again."); // asks the use to give value again
                sc.next();// read the data again if the user gives wring input
            }
        }
        return data;// returns to line 10 once the correct data has been entered.
    }



    public static int readChoice(String prompt){
        boolean success= false;
        int choice=0;
        while (!success){// while loop ends when the user provides the correct value.
            try {
                System.out.println(prompt);
                choice= sc.nextInt(); // try 1 accept value= hi
                if (choice>=0 && choice<=3) {
                    success = true;
                }
                else{
                    System.out.println("You have not given the data in the range. Please enter again");
                }

            }catch (InputMismatchException e){
                // Catch will run when the user provides a string value.
                System.out.println("You have provided wrong type of value. Please try again."); // asks the use to give value again
                sc.next();// read the data again if the user gives wring input.// bye
            }
        }
        return choice;// returns to line 10 once the correct data has been entered.
    }
}

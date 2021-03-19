package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class fileRead {

    //Input a string, in this case "Clarencé,Andersson", removes the quotation marks and the comma, then returns the string.
    private static String stringHandle(String input) {
        String output = "";
        boolean withinQuotes = false;
        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);

            if (current == '"' && !withinQuotes)
                withinQuotes = true;
            else if (current == '"' && withinQuotes)
                withinQuotes = false;

            if ((current != ',' && withinQuotes) || !withinQuotes)
                output += current;
            else if(current == ',' && withinQuotes)
                output += " ";
        }
        return output.replace("\"", "");
    }

    //This function reads the file, does the calculation and prints the results
    public static void fileReader() {

        try {
            File filePath = new File("src/prospects.txt");
            Scanner fileScan = new Scanner(filePath);

            //Loop from second line
            fileScan.nextLine();

            //While loop as long as there are more lines in the file
            while (fileScan.hasNextLine()) {
                String data = fileScan.nextLine();

                //Splits the data on commas, removes '.'
                if(data != null && data.length() > 1) {
                    data = stringHandle(data);
                    String[] newString = data.split(",");
                    String name = newString[0];
                    name = name.replaceAll("[.]", "");

                    //Variables used to do the loan calculations
                    double totalLoan = Double.parseDouble(newString[1]);
                    float interest = Float.parseFloat(newString[2]);
                    int years = Integer.parseInt(newString[3]);
                    double customerPayments = 0;
                    float monthlyInterest = interest/100/12;

                    customerPayments = totalLoan*((monthlyInterest)*power(1+monthlyInterest, years*12) / (power(1+monthlyInterest, years*12)-1));

                    //Format the payment to xx.xx€
                    DecimalFormat paymentFormat = new DecimalFormat("#.00");

                    //Full print for all calculations
                    System.out.println(name + " wants to borrow " + totalLoan + "€ for a period of " +
                                       years + " years and has to pay " + paymentFormat.format(customerPayments) +"€ each month");
                }
            }
            fileScan.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
    }

    //Power function to do the calculations without using java.math
    public static float power(float base, int exponent){

        float result = 1;

        while(exponent != 0){
            result *= base;
            --exponent;
        }
        return result;
    }
}
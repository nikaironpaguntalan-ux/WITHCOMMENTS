import java.util.Scanner;

public class LabTestMenuSystem {

    private final SelectedLabTests labTests;
    private final String sex;

    public LabTestMenuSystem(String sex) {
        labTests = new SelectedLabTests();

        this.sex = sex.trim().toUpperCase().startsWith("M") ? "M" : "F";
    }

    public void startLabMenu() {

        try (Scanner input = new Scanner(System.in)) {
            int choice;
            
            while (true) {
                displayMenu();
                choice = input.nextInt();
                
                if (choice == 18) {
                    System.out.println("Exiting Lab Menu...");
                    break;
                }
                
                processChoice(choice, input);
            }
        }
        System.out.println("Program Ended.");
    }

    private void displayMenu() {

        System.out.println("\n===== LAB TEST MAIN MENU =====");
        System.out.println("1  - FBS Test");
        System.out.println("2  - RBS Test");
        System.out.println("3  - Total Cholesterol Test");
        System.out.println("4  - HDL Test");
        System.out.println("5  - LDL Test");
        System.out.println("6  - Triglycerides Test");
        System.out.println("7  - Creatinine Test");
        System.out.println("8  - Uric Acid Test");
        System.out.println("9  - BUN Test");
        System.out.println("10 - AST/SGOT Test");
        System.out.println("11 - ALT/SGPT Test");
        System.out.println("12 - Sodium Test");
        System.out.println("13 - Potassium Test");
        System.out.println("14 - Chloride Test");
        System.out.println("15 - Total Calcium Test");
        System.out.println("16 - Ionized Calcium Test");
        System.out.println("17 - Process Result");
        System.out.println("18 - Exit");
        System.out.print("Choose Test: ");
    }

    private void processChoice(int choice, Scanner input) {

        if (choice == 1) {
            System.out.print("Enter FBS Value (mg/dL): ");
            double value = input.nextDouble();
            double si = CONVENTIONALtoSI.glucoseToSI(value);

            double lowConv = 74, highConv = 100;
            double lowSI = 4.07, highSI = 5.5;

            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("FBS", value, si,
                    "mg/dL", "mmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 2) {
            System.out.print("Enter RBS Value (mg/dL): ");
            double value = input.nextDouble();
            double si = CONVENTIONALtoSI.glucoseToSI(value);

            double lowConv = 70, highConv = 139;
            double lowSI = 3.9, highSI = 7.7;

            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("RBS", value, si,
                    "mg/dL", "mmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 3) { 
            System.out.print("Enter Total Cholesterol Value (mg/dL): ");
            double value = input.nextDouble();
            double si = CONVENTIONALtoSI.cholesterolToSI(value);

            double lowConv = 150, highConv = 200;
            double lowSI = 3.9, highSI = 5.72;

            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("Total Cholesterol", value, si,
                    "mg/dL", "mmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 4) { 
            System.out.print("Enter HDL Value (mg/dL): ");
            double value = input.nextDouble();
            double si = CONVENTIONALtoSI.hdlToSI(value);

            double lowConv, highConv, lowSI, highSI;

            if (sex.equals("M")) {
                lowConv = 35; highConv = 80;
                lowSI = 0.91; highSI = 2.08;
            } else {
                lowConv = 42; highConv = 88;
                lowSI = 1.09; highSI = 2.29;
            }

            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("HDL", value, si,
                    "mg/dL", "mmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 5) { 
            System.out.print("Enter LDL Value (mg/dL): ");
            double value = input.nextDouble();
            double si = CONVENTIONALtoSI.ldlToSI(value);

            double lowConv = 50, highConv = 130;
            double lowSI = 1.3, highSI = 3.38;

            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("LDL", value, si,
                    "mg/dL", "mmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 6) { 
            System.out.print("Enter Triglycerides Value (mg/dL): ");
            double value = input.nextDouble();
            double si = CONVENTIONALtoSI.triglyceridesToSI(value);

            double lowConv, highConv, lowSI, highSI;

            if (sex.equals("M")) {
                lowConv = 60; highConv = 165;
                lowSI = 0.68; highSI = 1.88;
            } else {
                lowConv = 40; highConv = 140;
                lowSI = 0.46; highSI = 1.6;
            }

            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("Triglycerides", value, si,
                    "mg/dL", "mmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 7) { 
            System.out.print("Enter Creatinine Value (mg/dL): ");
            double value = input.nextDouble();
            double si = CONVENTIONALtoSI.creatinineToSI(value);

            double lowConv, highConv, lowSI, highSI;

            if (sex.equals("M")) {
                lowConv = 0.9; highConv = 1.30;
                lowSI = 79.6; highSI = 114.9;
            } else {
                lowConv = 0.6; highConv = 1.2;
                lowSI = 53.04; highSI = 106.08;
            }

            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("Creatinine", value, si,
                    "mg/dL", "µmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 8) {
            System.out.print("Enter Uric Acid Value (mg/dL): ");
            double value = input.nextDouble();
            double si = CONVENTIONALtoSI.uricAcidToSI(value);

            double lowConv, highConv, lowSI, highSI;

            if (sex.equals("M")) {
                lowConv = 3.5; highConv = 7.2;
                lowSI = 0.21; highSI = 0.42;
            } else {
                lowConv = 2.6; highConv = 6.0;
                lowSI = 0.15; highSI = 0.35;
            }

            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("Uric Acid", value, si,
                    "mg/dL", "mmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 9) { 
            System.out.print("Enter BUN Value (mg/dL): ");
            double value = input.nextDouble();
            double si = CONVENTIONALtoSI.bunToSI(value);

            double lowConv = 6.0, highConv = 20.0;
            double lowSI = 2.14, highSI = 7.14;


            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("BUN", value, si,
                    "mg/dL", "mmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 10) { 
            System.out.print("Enter AST Value (U/L): ");
            double value = input.nextDouble();
            double si = CONVENTIONALtoSI.enzymeToSI(value);

            double highConv = 45;
            double highSI = 0.78;

            String interpretation = (value > highConv) ? "HIGH" : "NORMAL";

            labTests.addTestResult("AST", value, si,
                    "U/L", "µkat/L",
                    0, highConv,  
                    0, highSI,     
                    interpretation);
        }


        else if (choice == 11) { 
            System.out.print("Enter ALT Value (U/L): ");
            double value = input.nextDouble();
            double si = CONVENTIONALtoSI.enzymeToSI(value);

            double highConv = 48;
            double highSI = 0.83;

            String interpretation = (value > highConv) ? "HIGH" : "NORMAL";

            labTests.addTestResult("ALT", value, si,
                    "U/L", "µkat/L",
                    0, highConv,   
                    0, highSI,     
                    interpretation);
        }

        else if (choice == 12) { 
            System.out.print("Enter Sodium Value (mEq/L): ");
            double value = input.nextDouble();

            double lowConv = 135, highConv = 145;
            double lowSI = 135, highSI = 145;

            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("Sodium", value, value,
                    "mEq/L", "mmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 13) {
            System.out.print("Enter Potassium Value (mEq/L): ");
            double value = input.nextDouble();

            double lowConv = 3.5, highConv = 5.0;
            double lowSI = 3.5, highSI = 5.0;

            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("Potassium", value, value,
                    "mEq/L", "mmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 14) { 
            System.out.print("Enter Chloride Value (mEq/L): ");
            double value = input.nextDouble();

            double lowConv = 96, highConv = 110;
            double lowSI = 96, highSI = 110;

            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("Chloride", value, value,
                    "mEq/L", "mmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 15) { 
            System.out.print("Enter Total Calcium Value (mg/dL): ");
            double value = input.nextDouble();
            double si = CONVENTIONALtoSI.totalCalciumToSI(value);

            double lowConv = 8.6, highConv = 10.28;
            double lowSI = 2.15, highSI = 2.57;

            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("Total Calcium", value, si,
                    "mg/dL", "mmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 16) {
            System.out.print("Enter Ionized Calcium Value (mg/dL): ");
            double value = input.nextDouble();
            double si = CONVENTIONALtoSI.ionizedCalciumToSI(value);

            double lowConv = 4.4, highConv = 5.2;
            double lowSI = 1.10, highSI = 1.30;

            String interpretation = rangeInterpretation.interpret(value, lowConv, highConv);

            labTests.addTestResult("Ionized Calcium", value, si,
                    "mg/dL", "mmol/L",
                    lowConv, highConv,
                    lowSI, highSI,
                    interpretation);
        }

        else if (choice == 17) {
            labTests.displayResults();
        }

        else {
            System.out.println("Invalid choice. Try again.");
        }
    }
}

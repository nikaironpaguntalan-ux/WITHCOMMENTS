import java.util.ArrayList;  

// Class nga ma store kag manage sang selected lab test results
public class SelectedLabTests {

    // List to hold formatted test results as strings
    private ArrayList<String> chosenTests = new ArrayList<>();

    
      // Method to add a lab test result to the list.
      //Parameters include test name, values in conventional and SI units,
      //reference ranges, units, and interpretation.
     
    public void addTestResult(String testName,
                            double conventional,
                            double siValue,
                            String unitConv,
                            String unitSI,
                            double lowConv,
                            double highConv,
                            double lowSI,
                            double highSI,
                            String interpretation) {

        // Format  para mag store sang test result as a string in sa list
        chosenTests.add(
                testName +
                "\n   Conventional: " + String.format("%.2f", conventional) + " " + unitConv +
                "\n   Conv Ref Range: " + lowConv + " - " + highConv + " " + unitConv +
                "\n   SI Units: " + String.format("%.2f", siValue) + " " + unitSI +
                "\n   SI Ref Range: " + lowSI + " - " + highSI + " " + unitSI +
                "\n   Result: " + interpretation +
                "\n"
        );
    }

    
    // Method to display all stored test results.
      //Prints a header, then either a message if no tests are stored,
      //or each test result in the list.
     
    public void displayResults() {

        System.out.println("\n===== SELECTED LAB TEST RESULTS =====");

        // Check if any test results have been stored, and print them or a message if none
        if (chosenTests.isEmpty()) {
            System.out.println("No tests were selected.");
        } else {
            // Print each stored test result
            for (String test : chosenTests) {
                System.out.println(test);
            }
        }
    }
}
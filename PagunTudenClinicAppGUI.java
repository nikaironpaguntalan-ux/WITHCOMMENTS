import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PagunTudenClinicAppGUI extends JFrame {

    // It will assign a unique patient ID to each new patient entry, starting from 1001 and incrementing for each new patient added to the system. This ensures that every patient has a distinct identifier for record-keeping and reference purposes.
    private static int nextID = 1001;

    //  demographics input fields, JTextFields The JTextField in Java Swing is a graphical user interface (GUI) component that allows a user to enter or edit a single line of text. It is a fundamental component for capturing user input like names, email addresses, or search queries in a GUI application. 
    private JTextField fnameField, mnameField, lnameField;
    private JTextField ageField, provinceField, cityField;
    private JTextField lastMealField, physicianField;

    // Combo boxes for selecting sex and AM/PM for last meal
    private JComboBox<String> sexBox, mealAmPmBox;

    // Table to enter lab test values, with a DefaultTableModel to manage the data
    private JTable testTable;
    private DefaultTableModel tableModel;

    // show the final lab report output in a JTextArea (multi-line text area that can display the formatted report)
    private JTextArea resultArea;

    // Variables to hold patient ID, date and time of collection for the report header
    private int patientID;
    private String dateCollection;
    private String timeCollection;

    // Constructor to build the GUI
    public PagunTudenClinicAppGUI() {

        // Assign unique patient ID
        patientID = nextID++;

        // Get current date
        dateCollection = LocalDate.now().toString();

        // Get current time formatted as hh:mm AM/PM
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
        timeCollection = LocalTime.now().format(timeFormat);

        // Set up the main frame properties like title, size, close operation, and center it on the screen 
        setTitle("PagunTuden Clinic Laboratory Information System");
        setSize(1200, 850);//this is the measurement of the window size in pixels, where 1200 is the width and 850 is the height. This size is chosen to provide ample space for all the components of the GUI, including input fields, tables, and the report area, while still being manageable on most screens.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the window on the screen

        // tHIS IS THE MAIN PANEL THAT HOLDS ALL OTHER COMPONENTS, USING BORDERLayout TO ORGANIZE THEM INTO REGIONS (NORTH, SOUTH, EAST, WEST, CENTER)
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));// THIS IS THE MEASUREMENT OF THE GAP BETWEEN COMPONENTS IN THE BORDERLAYOUT, WHERE 15 IS THE HORIZONTAL GAP AND 15 IS THE VERTICAL GAP. THIS PROVIDES SPACING BETWEEN THE DIFFERENT PANELS AND COMPONENTS IN THE GUI, MAKING IT MORE VISUALLY APPEALING AND EASIER TO READ.
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));// THIS ADDS AN EMPTY BORDER AROUND THE MAIN PANEL WITH 15 PIXELS OF SPACE ON ALL SIDES (TOP, LEFT, BOTTOM, RIGHT). THIS CREATES A MARGIN AROUND THE CONTENT OF THE GUI, PREVENTING COMPONENTS FROM BEING TOO CLOSE TO THE EDGES OF THE WINDOW AND IMPROVING THE OVERALL LAYOUT AND AESTHETIC OF THE APPLICATION.  

        // ------------------- Patient Information Panel -------------------
        JPanel patientPanel = new JPanel(new GridLayout(8, 2, 8, 8));// THIS PANEL USES A GRIDLAYOUT WITH 8 ROWS AND 2 COLUMNS, AND A GAP OF 8 PIXELS BOTH HORIZONTALLY AND VERTICALLY BETWEEN COMPONENTS. THIS LAYOUT ORGANIZES THE LABELS AND INPUT FIELDS IN A NEAT GRID, MAKING IT EASY FOR USERS TO ENTER PATIENT INFORMATION IN A STRUCTURED MANNER.
        patientPanel.setBorder(BorderFactory.createTitledBorder("Patient Information"));//THIS ADDS A TITLED BORDER AROUND THE PATIENT INFORMATION PANEL WITH THE TITLE "Patient Information". THIS HELPS TO VISUALLY SEPARATE THIS SECTION FROM OTHER PARTS OF THE GUI AND PROVIDES A CLEAR LABEL FOR USERS TO UNDERSTAND THAT THIS AREA IS FOR ENTERING PATIENT DETAILS.

        // Initialize text fields
        fnameField = new JTextField();// THIS CREATES A NEW JTextField FOR THE FIRST NAME INPUT. JTextField is a COMPONENT IN JAVA SWING THAT ALLOWS USERS TO ENTER OR EDIT A SINGLE LINE OF TEXT. THIS FIELD WILL BE USED TO CAPTURE THE FIRST NAME OF THE PATIENT IN THE GUI.
        mnameField = new JTextField();// THIS CREATES A NEW JTextField FOR THE MIDDLE NAME INPUT. THIS FIELD WILL BE USED TO CAPTURE THE MIDDLE NAME OF THE PATIENT IN THE GUI.
        lnameField = new JTextField();// THIS CREATES A NEW JTextField FOR THE LAST NAME INPUT. THIS FIELD WILL BE USED TO CAPTURE THE LAST NAME OF THE PATIENT IN THE GUI.
        ageField = new JTextField();// THIS CREATES A NEW JTextField FOR THE AGE INPUT. THIS FIELD WILL BE USED TO CAPTURE THE AGE OF THE PATIENT IN THE GUI.
        provinceField = new JTextField();// THIS CREATES A NEW JTextField FOR THE PROVINCE INPUT. THIS FIELD WILL BE USED TO CAPTURE THE PROVINCE OF THE PATIENT IN THE GUI.
        cityField = new JTextField();// THIS CREATES A NEW JTextField FOR THE CITY INPUT. THIS FIELD WILL BE USED TO CAPTURE THE CITY OF THE PATIENT IN THE GUI.

        // Sex selection combo box
        sexBox = new JComboBox<>(new String[]{"M", "F"});

        // Add labels and fields in GridLayout
        patientPanel.add(new JLabel("Patient ID (Auto):"));// THIS ADDS A NEW JLabel WITH THE TEXT "Patient ID (Auto):" TO THE PATIENT PANEL. THIS LABEL SERVES AS A DESCRIPTOR FOR THE PATIENT ID FIELD, INDICATING TO THE USER THAT THE PATIENT ID IS AUTOMATICALLY GENERATED AND
        patientPanel.add(new JLabel(String.valueOf(patientID)));// THIS ADDS A NEW JLabel TO THE PATIENT PANEL THAT DISPLAYS THE CURRENT VALUE OF patientID. THIS LABEL SHOWS THE AUTO-GENERATED PATIENT ID TO THE USER, PROVIDING IMMEDIATE FEEDBACK ON THE UNIQUE IDENTIFIER ASSIGNED TO THE PATIENT BEING ENTERED INTO THE SYSTEM.

        patientPanel.add(new JLabel("First Name:"));//
        patientPanel.add(fnameField);//this adds the first name label and text field to the patient information panel, allowing users to input the patient's first name.

        patientPanel.add(new JLabel("Middle Name:"));
        patientPanel.add(mnameField);//this adds the middle name label and text field to the patient information panel, allowing users to input the patient's middle name.

        patientPanel.add(new JLabel("Last Name:"));
        patientPanel.add(lnameField);//this adds the last name label and text field to the patient information panel, allowing users to input the patient's last name.

        patientPanel.add(new JLabel("Age:"));
        patientPanel.add(ageField);//this adds the age label and text field to the patient information panel, allowing users to input the patient's age.

        patientPanel.add(new JLabel("Sex:"));
        patientPanel.add(sexBox);//this adds the sex label and combo box to the patient information panel, allowing users to select the

        patientPanel.add(new JLabel("Province:"));
        patientPanel.add(provinceField);

        patientPanel.add(new JLabel("City:"));
        patientPanel.add(cityField);

        // ------------------- Request Details Panel -------------------
        JPanel requestPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        requestPanel.setBorder(BorderFactory.createTitledBorder("Request Details"));//THIS ADDS A TITLED BORDER AROUND THE REQUEST DETAILS PANEL WITH THE TITLE "Request Details". THIS HELPS TO VISUALLY SEPARATE THIS SECTION FROM OTHER PARTS OF THE GUI AND PROVIDES A CLEAR LABEL FOR USERS TO UNDERSTAND THAT THIS AREA IS FOR ENTERING DETAILS RELATED TO THE LAB TEST REQUEST, SUCH AS LAST MEAL TIME AND REQUESTING PHYSICIAN.

        // Initialize fields
        lastMealField = new JTextField();
        physicianField = new JTextField();
        mealAmPmBox = new JComboBox<>(new String[]{"AM", "PM"});

        // Add labels and fields
        requestPanel.add(new JLabel("Last Meal Time:"));
        requestPanel.add(lastMealField);//this adds the last meal time label and text field to the request details panel, allowing users to input the time of the patient's last meal.

        requestPanel.add(new JLabel("AM/PM:"));
        requestPanel.add(mealAmPmBox);//this adds the AM/PM label and combo box to the request details panel, allowing users to select whether the last meal time entered is in the morning (AM) or afternoon/evening (PM).

        requestPanel.add(new JLabel("Requesting Physician:"));
        requestPanel.add(physicianField);//this adds the requesting physician label and text field to the request details panel, allowing users to input the name of the physician who requested the lab tests.

        requestPanel.add(new JLabel("Date & Time Collected:"));
        requestPanel.add(new JLabel(dateCollection + " " + timeCollection));// this add the date and time collected label to the request details panel, displaying the current date and time when the patient information is being entered, providing a timestamp for when the lab test request was made.

        // ------------------- Lab Tests Panel -------------------
        JPanel labPanel = new JPanel(new BorderLayout());// it creates lab panel
        labPanel.setBorder(BorderFactory.createTitledBorder("Laboratory Tests (Enter Value Per Test)"));

        // Table model with columns "Test Name" and "Conventional Value"
        tableModel = new DefaultTableModel(new Object[]{"Test Name", "Conventional Value"}, 0);

        // Array of lab tests
        String[] tests = {
                "FBS", "RBS", "Total Cholesterol", "HDL", "LDL",
                "Triglycerides", "Creatinine", "Uric Acid",
                "BUN", "AST", "ALT", "Sodium",
                "Potassium", "Chloride",
                "Total Calcium", "Ionized Calcium"
        };

        // it adds tests to the table model as rows, with the test name in the first column and an empty string in the second column for user input
        for (String test : tests) {
            tableModel.addRow(new Object[]{test, ""});
        }

        // Initialize JTable with model
        testTable = new JTable(tableModel);
        testTable.getColumnModel().getColumn(0).setPreferredWidth(250);//measurement of the preferred width of the first column (Test Name) in pixels, setting it to 250 pixels. This ensures that the test names have enough space to be fully visible without being cut off, improving readability and the overall appearance of the table in the GUI.
        testTable.getColumnModel().getColumn(1).setPreferredWidth(500);//
        testTable.setRowHeight(30); // this is the height of row

        JScrollPane tableScroll = new JScrollPane(testTable);
        labPanel.add(tableScroll, BorderLayout.CENTER);

        // Text area to show final lab report
        resultArea = new JTextArea(15, 70);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Laboratory Report Output"));

        // Button to process all entered lab tests
        JButton processButton = new JButton("PROCESS ALL ENTERED TESTS");
        processButton.setFont(new Font("Arial", Font.BOLD, 14));
        processButton.addActionListener(e -> processMultipleTests());

        // Layout top panels: patient info + request details
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        topPanel.add(patientPanel);
        topPanel.add(requestPanel);

        // Add components to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(labPanel, BorderLayout.CENTER);
        mainPanel.add(processButton, BorderLayout.WEST);
        mainPanel.add(scroll, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);
    }

    // ------------------- Process Lab Test Values -------------------
    private void processMultipleTests() {

        String sex = (String) sexBox.getSelectedItem();

        // Header for lab report
        resultArea.setText(
                "=========== PAGUNTUDEN CLINIC LAB REPORT ===========\n\n" +
                        "Patient ID: " + patientID +
                        "\nName      : " + fnameField.getText() + " " +
                        mnameField.getText() + " " +
                        lnameField.getText() +
                        "\nAge       : " + ageField.getText() +
                        "\nSex       : " + sex +
                        "\nAddress   : " + provinceField.getText() + ", " +
                        cityField.getText() +
                        "\nPhysician : " + physicianField.getText() +
                        "\nLast Meal : " + lastMealField.getText() + " " +
                        mealAmPmBox.getSelectedItem() +
                        "\nCollected : " + dateCollection + " " + timeCollection +
                        "\n\n=================================================\n\n"
        );

       // Loop through each test in the table
for (int i = 0; i < tableModel.getRowCount(); i++) {

    String testName = tableModel.getValueAt(i, 0).toString();
    String valueText = tableModel.getValueAt(i, 1).toString();

    // Skip if no value entered
    if (valueText.isEmpty()) continue;

    try {
        double value = Double.parseDouble(valueText); // Convert string to double
        double si = 0; // SI converted value
        double lowConv = 0, highConv = 0; // reference conventional range
        double lowSI = 0, highSI = 0; // reference SI range
        String unitConv = "", unitSI = ""; // units
        String status = ""; // NORMAL/LOW/HIGH

        // If-else statements to handle each lab test
        if (testName.equals("FBS")) {
            si = value / 18.0; lowConv = 74; highConv = 100; lowSI = 4.07; highSI = 5.5;
            unitConv = "mg/dL"; unitSI = "mmol/L";
            status = interpretRange(value, lowConv, highConv);
        } 
        else if (testName.equals("RBS")) {
            si = value / 18.0; lowConv = 70; highConv = 139; lowSI = 3.9; highSI = 7.7;//This is the range for random blood sugar in conventional and SI units
            unitConv = "mg/dL"; unitSI = "mmol/L";
            status = interpretRange(value, lowConv, highConv);
        } 
        else if (testName.equals("Total Cholesterol")) {
            si = value / 38.67; lowConv = 150; highConv = 200; lowSI = 3.9; highSI = 5.72;//This is the range for total cholesterol in conventional and SI units
            unitConv = "mg/dL"; unitSI = "mmol/L";
            status = interpretRange(value, lowConv, highConv);
        } 
        else if (testName.equals("HDL")) {
            si = value / 38.67; unitConv = "mg/dL"; unitSI = "mmol/L";
            if (sex.equals("M")) { lowConv = 35; highConv = 80; lowSI = 0.91; highSI = 2.08; }//This is the range for HDL cholesterol in conventional and
            else { lowConv = 42; highConv = 88; lowSI = 1.09; highSI = 2.29; }
            status = interpretRange(value, lowConv, highConv);
        } 
        else if (testName.equals("AST")) {
            si = value * 0.0167; highConv = 45; highSI = 0.75;
            unitConv = "U/L"; unitSI = "µkat/L";
            status = (value > highConv) ? "HIGH" : "NORMAL"; 
        } 
        else if (testName.equals("ALT")) {
            si = value * 0.0167; highConv = 48; highSI = 0.80;
            unitConv = "U/L"; unitSI = "µkat/L";
            status = (value > highConv) ? "HIGH" : "NORMAL";
        } 
        else if (testName.equals("Sodium")) { 
            si = value; lowConv = 135; highConv = 145; lowSI = 135; highSI = 145; unitConv = "mEq/L"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);
        } 
        else if (testName.equals("Potassium")) { 
            si = value; lowConv = 3.5; highConv = 5.0; lowSI = 3.5; highSI = 5.0; unitConv = "mEq/L"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);
        } 
        else if (testName.equals("Chloride")) { 
            si = value; lowConv = 96; highConv = 110; lowSI = 96; highSI = 110; unitConv = "mEq/L"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);
        } 
        else if (testName.equals("Total Calcium")) { 
            si = value / 4.0; lowConv = 8.6; highConv = 10.28; lowSI = 2.15; highSI = 2.57; unitConv = "mg/dL"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);
        } 
        else if (testName.equals("Ionized Calcium")) { 
            si = value / 4.0; lowConv = 4.4; highConv = 5.2; lowSI = 1.10; highSI = 1.30; unitConv = "mg/dL"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);
        }

        // Append results for this test to the report
        resultArea.append(
                "Test Name            : " + testName +
                        "\nValue Entered        : " + value + " " + unitConv +
                        "\nConventional Range   : " + lowConv + (testName.equals("AST") || testName.equals("ALT") ? "" : " - " + highConv) + " " + unitConv +
                        "\nSI Converted Value   : " + String.format("%.2f", si) + " " + unitSI +
                        (testName.equals("AST") || testName.equals("ALT") ? "" : "\nSI Range             : " + lowSI + " - " + highSI + " " + unitSI) +
                        "\nStatus               : " + status +
                        "\n-------------------------------------------------\n\n"
        );

    } catch (NumberFormatException ex) {
        // If the user entered text instead of numbers
        resultArea.append("Invalid Input for: " + testName + "\n\n");
    }
}
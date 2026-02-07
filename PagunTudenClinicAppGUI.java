import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PagunTudenClinicAppGUI extends JFrame {

    private static int nextID = 1001;

    private JTextField fnameField, mnameField, lnameField;
    private JTextField ageField, provinceField, cityField;
    private JTextField lastMealField, physicianField;

    private JComboBox<String> sexBox, mealAmPmBox;

    private JTable testTable;
    private DefaultTableModel tableModel;

    private JTextArea resultArea;

    private int patientID;
    private String dateCollection;
    private String timeCollection;

    public PagunTudenClinicAppGUI() {

        patientID = nextID++;

        dateCollection = LocalDate.now().toString();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
        timeCollection = LocalTime.now().format(timeFormat);

        setTitle("PagunTuden Clinic Laboratory Information System");
        setSize(1200, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Patient Panel
        JPanel patientPanel = new JPanel(new GridLayout(8, 2, 8, 8));
        patientPanel.setBorder(BorderFactory.createTitledBorder("Patient Information"));

        fnameField = new JTextField();
        mnameField = new JTextField();
        lnameField = new JTextField();
        ageField = new JTextField();
        provinceField = new JTextField();
        cityField = new JTextField();

        sexBox = new JComboBox<>(new String[]{"M", "F"});

        patientPanel.add(new JLabel("Patient ID (Auto):"));
        patientPanel.add(new JLabel(String.valueOf(patientID)));

        patientPanel.add(new JLabel("First Name:"));
        patientPanel.add(fnameField);

        patientPanel.add(new JLabel("Middle Name:"));
        patientPanel.add(mnameField);

        patientPanel.add(new JLabel("Last Name:"));
        patientPanel.add(lnameField);

        patientPanel.add(new JLabel("Age:"));
        patientPanel.add(ageField);

        patientPanel.add(new JLabel("Sex:"));
        patientPanel.add(sexBox);

        patientPanel.add(new JLabel("Province:"));
        patientPanel.add(provinceField);

        patientPanel.add(new JLabel("City:"));
        patientPanel.add(cityField);

        // Request Panel
        JPanel requestPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        requestPanel.setBorder(BorderFactory.createTitledBorder("Request Details"));

        lastMealField = new JTextField();
        physicianField = new JTextField();
        mealAmPmBox = new JComboBox<>(new String[]{"AM", "PM"});

        requestPanel.add(new JLabel("Last Meal Time:"));
        requestPanel.add(lastMealField);

        requestPanel.add(new JLabel("AM/PM:"));
        requestPanel.add(mealAmPmBox);

        requestPanel.add(new JLabel("Requesting Physician:"));
        requestPanel.add(physicianField);

        requestPanel.add(new JLabel("Date & Time Collected:"));
        requestPanel.add(new JLabel(dateCollection + " " + timeCollection));

        // Lab Panel
        JPanel labPanel = new JPanel(new BorderLayout());
        labPanel.setBorder(BorderFactory.createTitledBorder("Laboratory Tests (Enter Value Per Test)"));

        tableModel = new DefaultTableModel(new Object[]{"Test Name", "Conventional Value"}, 0);
        String[] tests = {
                "FBS", "RBS", "Total Cholesterol", "HDL", "LDL",
                "Triglycerides", "Creatinine", "Uric Acid",
                "BUN", "AST", "ALT", "Sodium",
                "Potassium", "Chloride",
                "Total Calcium", "Ionized Calcium"
        };
        for (String test : tests) {
            tableModel.addRow(new Object[]{test, ""});
        }

        testTable = new JTable(tableModel);
        testTable.getColumnModel().getColumn(0).setPreferredWidth(250);
        testTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        testTable.setRowHeight(30);

        JScrollPane tableScroll = new JScrollPane(testTable);
        labPanel.add(tableScroll, BorderLayout.CENTER);

        resultArea = new JTextArea(15, 70);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Laboratory Report Output"));

        JButton processButton = new JButton("PROCESS ALL ENTERED TESTS");
        processButton.setFont(new Font("Arial", Font.BOLD, 14));
        processButton.addActionListener(e -> processMultipleTests());

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        topPanel.add(patientPanel);
        topPanel.add(requestPanel);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(labPanel, BorderLayout.CENTER);
        mainPanel.add(processButton, BorderLayout.WEST);
        mainPanel.add(scroll, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void processMultipleTests() {

        String sex = (String) sexBox.getSelectedItem();

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

        for (int i = 0; i < tableModel.getRowCount(); i++) {

            String testName = tableModel.getValueAt(i, 0).toString();
            String valueText = tableModel.getValueAt(i, 1).toString();

            if (valueText.isEmpty()) continue;

            try {
                double value = Double.parseDouble(valueText);
                double si = 0;
                double lowConv = 0, highConv = 0;
                double lowSI = 0, highSI = 0;
                String unitConv = "", unitSI = "";
                String status = "";

                switch (testName) {
                    case "FBS" -> {
                        si = value / 18.0; lowConv = 74; highConv = 100; lowSI = 4.07; highSI = 5.5;
                        unitConv = "mg/dL"; unitSI = "mmol/L";
                        status = interpretRange(value, lowConv, highConv);
                    }
                    case "RBS" -> {
                        si = value / 18.0; lowConv = 70; highConv = 139; lowSI = 3.9; highSI = 7.7;
                        unitConv = "mg/dL"; unitSI = "mmol/L";
                        status = interpretRange(value, lowConv, highConv);
                    }
                    case "Total Cholesterol" -> {
                        si = value / 38.67; lowConv = 150; highConv = 200; lowSI = 3.9; highSI = 5.72;
                        unitConv = "mg/dL"; unitSI = "mmol/L";
                        status = interpretRange(value, lowConv, highConv);
                    }
                    case "HDL" -> {
                        si = value / 38.67; unitConv = "mg/dL"; unitSI = "mmol/L";
                        if (sex.equals("M")) { lowConv = 35; highConv = 80; lowSI = 0.91; highSI = 2.08; }
                        else { lowConv = 42; highConv = 88; lowSI = 1.09; highSI = 2.29; }
                        status = interpretRange(value, lowConv, highConv);
                    }
                    case "LDL" -> {
                        si = value / 38.67; lowConv = 50; highConv = 130; lowSI = 1.3; highSI = 3.38;
                        unitConv = "mg/dL"; unitSI = "mmol/L";
                        status = interpretRange(value, lowConv, highConv);
                    }
                    case "Triglycerides" -> {
                        si = value / 88.57; unitConv = "mg/dL"; unitSI = "mmol/L";
                        if (sex.equals("M")) { lowConv = 60; highConv = 165; lowSI = 0.68; highSI = 1.88; }
                        else { lowConv = 40; highConv = 140; lowSI = 0.46; highSI = 1.6; }
                        status = interpretRange(value, lowConv, highConv);
                    }
                    case "Creatinine" -> {
                        si = value * 88.4; unitConv = "mg/dL"; unitSI = "µmol/L";
                        if (sex.equals("M")) { lowConv = 0.9; highConv = 1.3; lowSI = 79.6; highSI = 114.9; }
                        else { lowConv = 0.6; highConv = 1.2; lowSI = 53.04; highSI = 106.08; }
                        status = interpretRange(value, lowConv, highConv);
                    }
                    case "Uric Acid" -> {
                        si = value / 16.81; unitConv = "mg/dL"; unitSI = "mmol/L";
                        if (sex.equals("M")) { lowConv = 3.5; highConv = 7.2; lowSI = 0.21; highSI = 0.42; }
                        else { lowConv = 2.6; highConv = 6.0; lowSI = 0.15; highSI = 0.35; }
                        status = interpretRange(value, lowConv, highConv);
                    }
                    case "BUN" -> {
                        si = value / 2.8; lowConv = 6; highConv = 20; lowSI = 2.14; highSI = 7.14;
                        unitConv = "mg/dL"; unitSI = "mmol/L";
                        status = interpretRange(value, lowConv, highConv);
                    }
                    case "AST" -> {
                        si = value * 0.0167; highConv = 45; highSI = 0.75;
                        unitConv = "U/L"; unitSI = "µkat/L";
                        status = (value > highConv) ? "HIGH" : "NORMAL"; 
                    }
                    case "ALT" -> {
                        si = value * 0.0167; highConv = 48; highSI = 0.80;
                        unitConv = "U/L"; unitSI = "µkat/L";
                        status = (value > highConv) ? "HIGH" : "NORMAL";
                    }
                    case "Sodium" -> { si = value; lowConv = 135; highConv = 145; lowSI = 135; highSI = 145; unitConv = "mEq/L"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);}
                    case "Potassium" -> { si = value; lowConv = 3.5; highConv = 5.0; lowSI = 3.5; highSI = 5.0; unitConv = "mEq/L"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);}
                    case "Chloride" -> { si = value; lowConv = 96; highConv = 110; lowSI = 96; highSI = 110; unitConv = "mEq/L"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);}
                    case "Total Calcium" -> { si = value / 4.0; lowConv = 8.6; highConv = 10.28; lowSI = 2.15; highSI = 2.57; unitConv = "mg/dL"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);}
                    case "Ionized Calcium" -> { si = value / 4.0; lowConv = 4.4; highConv = 5.2; lowSI = 1.10; highSI = 1.30; unitConv = "mg/dL"; unitSI = "mmol/L"; status = interpretRange(value, lowConv, highConv);}
                }

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
                resultArea.append("Invalid Input for: " + testName + "\n\n");
            }
        }

        resultArea.append("=========== END OF REPORT ===========\n");
    }

    private String interpretRange(double value, double low, double high) {
        if (value < low) return "LOW";
        else if (value > high) return "HIGH";
        else return "NORMAL";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PagunTudenClinicAppGUI().setVisible(true));
    }
}

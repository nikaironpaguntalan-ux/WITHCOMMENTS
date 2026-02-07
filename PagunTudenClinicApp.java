import java.util.Scanner;

public class PagunTudenClinicApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter First Name: ");
        String fname = input.nextLine();

        System.out.print("Enter Middle Name: ");
        String mname = input.nextLine();

        System.out.print("Enter Last Name: ");
        String lname = input.nextLine();

        System.out.print("Enter Age: ");
        int age = input.nextInt();
        input.nextLine(); 

        System.out.print("Enter Sex (F/M): ");
        String sex = input.nextLine();

        System.out.print("Enter Province: ");
        String province = input.nextLine();

        System.out.print("Enter City: ");
        String city = input.nextLine();

        System.out.print("Enter Baranggay: ");
        String baranggay = input.nextLine();

        System.out.print("Enter Time of Last Meal: ");
        String lastMeal = input.nextLine();

        System.out.print("Enter (AM/PM): ");
        String amorpm = input.nextLine();

        System.out.print("Enter Requesting Physician: ");
        String physician = input.nextLine();

        PatientsDemographicsInfo demoinfo =
                new PatientsDemographicsInfo(
                        fname, mname, lname,
                        age, sex,
                        province, city, baranggay,
                        lastMeal, amorpm, physician
                );

        System.out.println("\n===== PATIENT INFORMATION =====");
        System.out.println(demoinfo);

        LabTestMenuSystem menu =
                new LabTestMenuSystem(demoinfo.getSex());
        menu.startLabMenu();

        input.close();
    }
}
import java.util.*;
import java.io.*;

abstract class HospitalStaff {
    public String FirstName;
    public String LastName;
    public int Age;
    public double Salary;

    //constructor
    public HospitalStaff(String FirstName, String LastName, int Age, double Salary) {
        if (Age <= 0) throw new IllegalArgumentException("Age must be a positive number.");
        if (Salary <= 0) throw new IllegalArgumentException("Salary must be a positive number!");
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.Age=Age;
        this.Salary=Salary;
    }

    public int getAge(){
        return Age;
    }

    public double getSalary(){
        return Salary;
    }

    public abstract void info();
}

class Doctor extends HospitalStaff {
    private String Specialization;
    private int PatientsTreated;

    public Doctor(String FirstName, String LastName, int Age, double Salary, String Specialization, int PatientsTreated){
        super(FirstName, LastName, Age, Salary);
        this.Specialization=Specialization;
        this.PatientsTreated=PatientsTreated;
    }

    public int getPatientsTreated(){
        return PatientsTreated;
    }

    @Override
    public void info(){
        System.out.println("Doctor: " + FirstName + " " + LastName + " - " + Specialization);
        System.out.printf("Salary: %.2f lv.\n", Salary);
        System.out.println("Age: " + Age);
        System.out.println("Patients treated: " + PatientsTreated);
    }
}

class Nurse extends HospitalStaff {
    private String Department;
    private int ShiftsWorked;

    public Nurse(String FirstName, String LastName, int Age, double Salary, String Department, int ShiftsWorked){
        super(FirstName, LastName, Age, Salary);
        this.Department=Department;
        this.ShiftsWorked=ShiftsWorked;
    }

    public int getShiftsWorked(){
        return ShiftsWorked;
    }

    @Override
    public void info(){
        System.out.println("Nurse: " + FirstName + " " + LastName + " - " + Department);
        System.out.printf("Salary: %.2f lv.\n", Salary);
        System.out.println("Age: " + Age);
        System.out.println("Shifts worked: " + ShiftsWorked);
    }
}

class Janitor extends HospitalStaff {
    private int AreaCovered;

    public Janitor(String FirstName, String LastName, int Age, double Salary, int AreaCovered){
        super(FirstName, LastName, Age, Salary);
        this.AreaCovered=AreaCovered;
    }

    public int getAreaCovered(){
        return AreaCovered;
    }

    @Override
    public void info(){
        System.out.println("Janitor: " + FirstName + " " + LastName);
        System.out.printf("Salary: %.2f lv.\n", Salary);
        System.out.println("Age: " + Age);
        System.out.println("Area covered: " + AreaCovered);
    }
}


public class zad28 {
    public static void main(String[] args) {
        List<HospitalStaff> staff = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                String type = parts[0].trim().toLowerCase();
                String fn = parts[1].trim();
                String ln = parts[2].trim();
                int age = Integer.parseInt(parts[3].trim());
                double salary = Double.parseDouble(parts[4].trim());

                switch (type) {
                    case "doctor":
                        String spec = parts[5].trim();
                        int patients = Integer.parseInt(parts[6].trim());
                        staff.add(new Doctor(fn, ln, age, salary, spec, patients));
                        break;
                    case "nurse":
                        String dep = parts[5].trim();
                        int shifts = Integer.parseInt(parts[6].trim());
                        staff.add(new Nurse(fn, ln, age, salary, dep, shifts));
                        break;
                    case "janitor":
                        int area = Integer.parseInt(parts[5].trim());
                        staff.add(new Janitor(fn, ln, age, salary, area));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        //sort by age
        for (int i = 0; i < staff.size() - 1; i++) {
            for (int j = 0; j < staff.size() - i - 1; j++) {
                if (staff.get(j).getAge() > staff.get(j + 1).getAge()) {
                    HospitalStaff temp = staff.get(j);
                    staff.set(j, staff.get(j + 1));
                    staff.set(j + 1, temp);
                }
            }
        }

        System.out.println("Employees sorted by age:");
        for (HospitalStaff s : staff) {
            s.info();
            System.out.println();
        }

        //highest salary
        HospitalStaff highestSalary = staff.get(0);
        for (HospitalStaff s : staff) {
            if (s.getSalary() > highestSalary.getSalary()) {
                highestSalary = s;
            }
        }
        System.out.println("Employee with the highest salary:");
        highestSalary.info();
        System.out.println();

        //doctor with most patients
        Doctor bestDoctor = null;
        for (HospitalStaff s : staff) {
            if (s instanceof Doctor) {
                Doctor d = (Doctor) s;
                if (bestDoctor == null || d.getPatientsTreated() > bestDoctor.getPatientsTreated()) {
                    bestDoctor = d;
                }
            }
        }
        if (bestDoctor != null) {
            System.out.println("Doctor with most patients treated:");
            bestDoctor.info();
            System.out.println();
        }

        //most hardworking Nurse or Janitor
        HospitalStaff hardworking = null;
        for (HospitalStaff s : staff) {
            if (s instanceof Nurse || s instanceof Janitor) {
                if (hardworking == null) {
                    hardworking = s;
                } else {
                    int scoreS = (s instanceof Nurse) ? ((Nurse) s).getShiftsWorked() : ((Janitor) s).getAreaCovered();
                    int scoreH = (hardworking instanceof Nurse) ? ((Nurse) hardworking).getShiftsWorked() : ((Janitor) hardworking).getAreaCovered();
                    if (scoreS > scoreH) {
                        hardworking = s;
                    }
                }
            }
        }
        if (hardworking != null) {
            System.out.println("Most hardworking employee (Nurse or Janitor):");
            hardworking.info();
            System.out.println();
        }
    }
}



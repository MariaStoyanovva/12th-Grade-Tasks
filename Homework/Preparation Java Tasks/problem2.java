import java.io.*;
import java.util.*;

abstract class ClubMember {
    protected String firstName;
    protected String lastName;
    protected int age;
    protected double salary;

    public ClubMember(String firstName, String lastName, int age, double salary) {
        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("The name can't be an empty string!");
        }
        if (age<=17) {
            throw new IllegalArgumentException("Age must be greater than 17 years!");
        }
        if (salary<=0) {
            throw new IllegalArgumentException("Salary must be a positive number!");
        }
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.salary=salary;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public abstract void info();
}

// Director class
class Director extends ClubMember {
    private String directorType; // executive, technical, sports

    public Director(String firstName, String lastName, int age, double salary, String directorType) {
        super(firstName, lastName, age, salary);
        this.directorType=directorType;
    }

    @Override
    public void info() {
        System.out.printf("%s director: %s %s%n", directorType, firstName, lastName);
        System.out.printf("salary: %.2f lv%n", salary);
        System.out.printf("age: %d years%n", age);
    }
}

class Coach extends ClubMember {
    private String coachType;
    private int contractLength;

    public Coach(String firstName, String lastName, int age, double salary, String coachType, int contractLength) {
        super(firstName, lastName, age, salary);
        this.coachType=coachType;
        this.contractLength=contractLength;
    }

    @Override
    public void info() {
        System.out.printf("%s coach: %s %s%n", coachType, firstName, lastName);
        System.out.printf("salary: %.2f lv%n", salary);
        System.out.printf("age: %d years%n", age);
    }
}

// FootballPlayer class
class FootballPlayer extends ClubMember {
    private String position;       // forward, midfielder, defender, goalkeeper
    private int contractLength;
    private int matches;
    private int goals;
    private int assists;

    public FootballPlayer(String firstName, String lastName, int age, double salary,
                          String position, int contractLength, int matches, int goals, int assists) {
        super(firstName, lastName, age, salary);
        this.position=position;
        this.contractLength=contractLength;
        this.matches=matches;
        this.goals=goals;
        this.assists=assists;
    }

    public int getGoals() {
        return goals;
    }

    @Override
    public void info() {
        System.out.printf("%s %s - %s%n", firstName, lastName, position);
        System.out.printf("salary: %.2f lv%n", salary);
        System.out.printf("age: %d years%n", age);
        System.out.printf("%d goals and %d assists in %d matches%n", goals, assists, matches);
    }
}

// Main application
public class problem2 {
    public static void main(String[] args) {
        List<ClubMember> members = new ArrayList<>();
        ClubMember highestSalaryMember = null;
        FootballPlayer topScorer = null;

        try(Scanner sc = new Scanner(new File("input.txt"))) {
            while(sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if(line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                String type = parts[0].toLowerCase();

                try{
                    switch(type) {
                        case "player": {
                            //player firstName lastName age salary position contractLength matches goals assists
                            String firstName = parts[1];
                            String lastName = parts[2];
                            int age = Integer.parseInt(parts[3]);
                            double salary = Double.parseDouble(parts[4]);
                            String position = parts[5];
                            int contractLength = Integer.parseInt(parts[6]);
                            int matches = Integer.parseInt(parts[7]);
                            int goals = Integer.parseInt(parts[8]);
                            int assists = Integer.parseInt(parts[9]);
                            FootballPlayer p = new FootballPlayer(firstName, lastName, age, salary,
                                    position, contractLength, matches, goals, assists);
                            members.add(p);
                            if (topScorer==null || p.getGoals()>topScorer.getGoals()) {
                                topScorer = p;
                            }
                            if (highestSalaryMember==null || p.getSalary()>highestSalaryMember.getSalary()) {
                                highestSalaryMember=p;
                            }
                            break;
                        }
                        case "coach": {
                            //coach firstName lastName age salary coachType contractLength
                            String firstName = parts[1];
                            String lastName = parts[2];
                            int age = Integer.parseInt(parts[3]);
                            double salary = Double.parseDouble(parts[4]);
                            String coachType = parts[5];
                            int contractLength = Integer.parseInt(parts[6]);
                            Coach c = new Coach(firstName, lastName, age, salary, coachType, contractLength);
                            members.add(c);
                            if(highestSalaryMember==null || c.getSalary()>highestSalaryMember.getSalary()) {
                                highestSalaryMember=c;
                            }
                            break;
                        }
                        case "director": {
                            // director firstName lastName age salary directorType
                            String firstName = parts[1];
                            String lastName = parts[2];
                            int age = Integer.parseInt(parts[3]);
                            double salary = Double.parseDouble(parts[4]);
                            String directorType = parts[5];
                            Director d = new Director(firstName, lastName, age, salary, directorType);
                            members.add(d);
                            if(highestSalaryMember==null || d.getSalary()>highestSalaryMember.getSalary()) {
                                highestSalaryMember=d;
                            }
                            break;
                        }
                        default:
                            System.err.println("Unknown member type: " + type);
                    }
                } catch(Exception e) {
                    System.err.println("Error reading line: " + line);
                    System.err.println("Reason: " + e.getMessage());
                }
            }
        } catch(FileNotFoundException e) {
            System.err.println("File input.txt not found!");
            return;
        }

        //sort by age ascending
        members.sort(Comparator.comparingInt(ClubMember::getAge));

        for(int i = 0; i < members.size(); i++) {
            members.get(i).info();
            if(i<members.size()-1) {
                System.out.println("********************");
            }
        }

        if(highestSalaryMember != null) {
            System.out.printf("%nThe person with the highest salary in the club is %s with %.2f lv salary.%n",
                    highestSalaryMember.getFullName(), highestSalaryMember.getSalary());
        }
        if(topScorer != null) {
            System.out.printf("The team's top scorer is %s with %d goals.%n",
                    topScorer.getFullName(), topScorer.getGoals());
        }
    }
}


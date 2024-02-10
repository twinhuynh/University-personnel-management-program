// Final Project - COP3330
// Tuyen Huynh

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class FinalProject {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\tWelcome to my Personal Management Program\n");
        while (true) {
            System.out.println("\nChoose one of the options: ");
            System.out.println("1 - Enter the information of a faculty member");
            System.out.println("2 - Enter the information of a student");
            System.out.println("3 - Print tuition invoice for a student");
            System.out.println("4 - Print faculty member information");
            System.out.println("5 - Enter the information of a staff member");
            System.out.println("6 - Print the information of a staff member");
            System.out.println("7 - Delete a person");
            System.out.println("8 - Exit Program");
            System.out.print("\n\tEnter your selection: ");

            try {
             
                int selection = scanner.nextInt();
                scanner.nextLine();

                switch (selection) {
                    case 1:
                    	System.out.println("\nEnter the faculty info: ");
                    	System.out.print("\tName of faculty: ");
                    	String facultyName = scanner.nextLine();
                    	System.out.print("\tID: ");
                    	String facultyId = scanner.nextLine();
                    	System.out.print("\tRank: ");
                    	String facultyRank = scanner.nextLine();
                    	while (!facultyRank.equalsIgnoreCase("Professor") && !facultyRank.equalsIgnoreCase("Adjunct")) {
                    		System.out.println('"'+ facultyRank + '"' + " is invalid");
                    		System.out.print("\tRank: ");
                    		facultyRank = scanner.nextLine();
                    	}
                    	System.out.print("\tDepartment: ");
                    	String facultyDepartment = scanner.nextLine();
                    	while (!facultyDepartment.equalsIgnoreCase("Mathematics") && !facultyDepartment.equalsIgnoreCase("English") && !facultyDepartment.equalsIgnoreCase("Engineering")) {
                    		System.out.println('"'+ facultyDepartment + '"' + " is invalid");
                    		System.out.print("\n\tDepartment: ");
                    		facultyDepartment = scanner.nextLine();
                    	}
                    	
                    	Faculty faculty = new Faculty(facultyName, facultyId, facultyDepartment, facultyRank);
                    	people.add(faculty);
                    	System.out.println("Faculty added!");
                        break;
                        
                    case 2:
                    	System.out.println("\nEnter the student info: ");
                    	System.out.print("\tName of student: ");
                    	String studentName = scanner.nextLine();
                    	String studentId;
                    	while (true) {
                    		System.out.print("\tID: ");
                    		studentId = scanner.nextLine();
                    		if(studentId.matches("^[A-z]{2}[0-9]{4}$")){
                    			boolean duplicate = false;
                    			for(Person person : people) {
                    				if (person.getId().equals(studentId)){
                    					duplicate = true;
                    					
                    					break;
                    				}
                    			}
                    			if (!duplicate) {
                    				break;
                    			} else {
                    				System.out.println("\tID alread exists. Please enter a unique ID. ");
                    			}
                    		} else {
                    			System.out.println("\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit. ");
                    		}
                    	}	
                    	System.out.print("\tGPA: ");
                    	double GPA = scanner.nextDouble();
                    	System.out.print("\tCredit Hours: ");
                    	int creditHours = scanner.nextInt();
                    		
                    	Student student = new Student(studentName, studentId, GPA, creditHours);
                    	people.add(student);
                    	System.out.println("Student added! ");
                    	break;
 
                    case 3:
                    	System.out.print("\tEnter the student's ID: ");
                    	String tuition = scanner.nextLine();
                    	
                    	boolean exist = false;
                    	for (Person person : people) {
                    		if (person instanceof Student && person.getId().equalsIgnoreCase(tuition)) {
                    			Student studentTuition = (Student) person;
                    			studentTuition.print();
                    			exist = true;
                    			break;
                     		}
                    	}
                    	if (!exist) {
                    		System.out.print("\tNo student matched.");
                    	}
                        break;
                        
                    case 4:
                    	System.out.print("\tEnter the ID of the faculty for information: ");
                    	String facultyInfoId = scanner.nextLine();
                    	exist = false;
                    	
                    	for (Person person : people) {
                    		if (person instanceof Faculty && person.getId().equalsIgnoreCase(facultyInfoId)) {
                    			Faculty facultyInfo = (Faculty) person;
                    			facultyInfo.print();
                    			exist = true;
                    			break;
                    		}
                    	}
                    	if (!exist) {
                    		System.out.println("\tNo faculty matched.\n");
                    	}
                        break;
                        
                    case 5:
                    	System.out.print("\tName of Staff Member: ");
                    	String staffName = scanner.nextLine();
                    	String staffId;
                    	while (true) {
                    		System.out.print("\tID: ");
                    		staffId = scanner.nextLine();
                    		if (staffId.matches("^[A-z]{2}[0-9]{4}$")) {
                        		boolean duplicate = false;
                        		for (Person person : people) {
                        			if (person.getId().equals(staffId)) {
                        				duplicate = true;
                        				break;
                        			}
                        		}
                        		if (!duplicate) {
                        			break;
                        		}else {
                        			System.out.println("\tID already exists. Please enter a unique ID.");
                        		}
                        	}else {
                        		System.out.println("\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit");
                        	}
                    	}
                    	System.out.print("\tDepartment: ");
                    	String staffDep = scanner.nextLine();
                    	System.out.print("\tStatus(Enter P for Part Time, or Enter F for Full Time): ");
                    	String sStatus = scanner.nextLine();
                    	String staffStatus;
                    	if (sStatus.equalsIgnoreCase("F")) {
                    		staffStatus = "Full Time";
                    	}else {
                    		staffStatus = "Part Time";
                    	}
                    	Staff staff = new Staff(staffName, staffId, staffDep, staffStatus);
                    	people.add(staff);
                    	System.out.println("\tStaff member added!");
                        break;
                        
                    case 6:
                    	System.out.print("\tEnter the Staff's ID: ");
                    	String staffInfoID = scanner.nextLine();
                    	exist = false;
                    	for (Person person : people) {
                    		if (person instanceof Staff && person.getId().equalsIgnoreCase(staffInfoID)) {
                    			Staff staffInfo = (Staff) person;
                    			staffInfo.print();
                    			exist = true;
                    			break;
                    		}
                    	}
                    	if (!exist) {
                    		System.out.println("\tNo staff matched.\n");
                    	}
                        break;
                       
                    case 7:
                        System.out.print("Enter the id of the person to delete: ");
                        String idToDelete = scanner.nextLine();
                        boolean removed = false;
                        Iterator<Person> iterator = people.iterator();
                        while (iterator.hasNext()) {
                            Person person = iterator.next();
                            if (person.getId().equalsIgnoreCase(idToDelete)) {
                                iterator.remove();
                                System.out.println("Person with ID " + idToDelete + " has been removed.");
                                removed = true;
                                break;
                            }
                        }
                        if (!removed) {
                            System.out.println("Sorry, no such person exists.\n");
                        }
                        break;
                    case 8:
                        System.out.print("Would you like to create the report? (Y/N): ");
                        char createReport = Character.toLowerCase(scanner.next().charAt(0));
                        scanner.nextLine();
                        if (createReport == 'y') {
                            System.out.print("Would like to sort your students by descending gpa or name (1 for gpa, 2 for name): ");
                            int sortOption = scanner.nextInt();
                            scanner.nextLine();
                            try {
                                generateReport(people, sortOption);
                                System.out.println("Report created and saved on your hard drive! Goodbye!");
                                return; // Exit the program
                            } catch (Exception e) {
                                System.out.println("Error while generating the report: " + e.getMessage());
                            }
                        }
                        break;
                    default:
                        System.out.println("Invalid entry- please try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a number for your choice.\n");
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

    private static void generateReport(ArrayList<Person> people, int sortOption) throws Exception {
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                if (p1 instanceof Student && p2 instanceof Student) {
                    Student s1 = (Student) p1;
                    Student s2 = (Student) p2;
                    if (sortOption == 1) {
                        int result = Double.compare(s2.getGPA(), s1.getGPA());
                        if (result == 0) {
                            return s1.getFullName().compareToIgnoreCase(s2.getFullName());
                        }
                        return result;
                    } else if (sortOption == 2) {
                        return s1.getFullName().compareToIgnoreCase(s2.getFullName());
                    }
                } else {
                    return p1.getFullName().compareToIgnoreCase(p2.getFullName());
                }
                return 0;
            }
        });

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(new Date());

        FileWriter fileWriter = new FileWriter("report.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("Report created on " + date);

        printWriter.println("Faculty Members");
        int facultyCount = 0;
        for (Person person : people) {
            if (person instanceof Faculty) {
                facultyCount++;
                Faculty faculty = (Faculty) person;
                printWriter.println(facultyCount + ". " + faculty.getFullName() + " ID: " + faculty.getId() +
                        " " + faculty.getDepartment() + " Department, " + faculty.getRank());
            }
        }

        printWriter.println("Staff Members");
        int staffCount = 0;
        for (Person person : people) {
            if (person instanceof Staff) {
                staffCount++;
                Staff staff = (Staff) person;
                printWriter.println(staffCount + ". " + staff.getFullName() + " ID: " + staff.getId() +
                        " " + staff.getDepartment() + ", " + staff.getStatus() + " Time");
            }
        }

        printWriter.println("Students (Sorted by ");
        if (sortOption == 1) {
            printWriter.println("gpa in descending order)");
        } else if (sortOption == 2) {
            printWriter.println("name)");
        }

        int studentCount = 0;
        for (Person person : people) {
            if (person instanceof Student) {
                studentCount++;
                Student student = (Student) person;
                printWriter.println(studentCount + ". " + student.getFullName() + " ID: " + student.getId() +
                        " Gpa: " + student.getGPA() + " Credit hours: " + student.getCreditHours());
            }
        }

        printWriter.close();
    }
}

abstract class Person {
	private String fullName;
	private String Id;
	
	public Person(String fullName, String Id) {
		this.fullName = fullName;
		this.Id = Id;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getId() {
		return Id;
	}
	
	public void setId(String Id) {
		this.Id = Id;
	}
	
	public abstract void print();
	
}

class Student extends Person{
	private double GPA;
	private int creditHours;
	
	public Student(String fullName, String Id, double GPA, int creditHours) {
		super(fullName, Id);
		this.GPA = GPA;
		this.creditHours = creditHours;
	}
	
	@Override
	public void print() {
		double payment;
		double discount;
		System.out.println("Here is the tuition invoice for" + getFullName() + ":");
		System.out.println("-----------------------------------------------------------");
		System.out.println(getFullName() + "\t\t" + getId());
		System.out.println("Credit Hours: " + getCreditHours() + "\t($236.45/credit hour)");
		System.out.println("Fees: $52");
		if (getGPA() >= 3.85) {
			payment = (((getCreditHours() * 236.45) + 52.00) * 0.75);
			discount = ((getCreditHours() * 236.45) + 52.00) - payment;
		} else {
			payment = (getCreditHours() * 236.45) + 52.00;
			discount = 0;
		}
		
		System.out.println("Total payment (after discount): " + String.format("$%.2f", payment) +"\t(" + String.format("$%.2f", discount) + " discount applied");
	}
	
	public double getGPA() {
		return GPA;
	}
	
	public void setGPA(double GPA) {
		this.GPA = GPA;
	}
	
	public int getCreditHours() {
		return creditHours;
	}
	
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}	
}

abstract class Employee extends Person {
	private String department;
	public Employee(String fullName, String Id, String department) {
		super(fullName, Id);
		this.department = department;
	}
	
	@Override
	public abstract void print();
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
}

class Faculty extends Employee {
	public String fullName;
	public String Id;
	public String department;
	private String rank;
	
	public Faculty(String fullName, String Id, String department, String rank) {
		super(fullName, Id, department);
		this.rank = rank;
	}
	
	@Override
	public void print() {
		System.out.println("Faculty Information: ");
		System.out.println("--------------------------");
		System.out.println(getFullName() + "\t\t" + getId());
		System.out.println(getDepartment() + " Department, \t" + rank);
	}
	
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
}

class Staff extends Employee{
	public String fullName;
	public String Id;
	public String department;
	private String status;
	
	public Staff(String fullName, String Id, String department, String status) {
		super(fullName, Id, department);
		this.status = status;
	}
	
	@Override
	public void print() {
		System.out.println("-------------------------------");
		System.out.println(getFullName() + "\t\t" + getId());
		System.out.println(getDepartment() + " Department, \t" + status);
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
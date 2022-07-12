package com.cognixia.jump.extra;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.exceptions.EmployeeNotFoundException;
import com.cognixia.jump.model.Employee;
import com.cognixia.jump.util.EmployeeManager;
import com.cognixia.jump.util.EmployeeManagerinMemory;

// CRUD Operations - Create Read Update Delete

// Create an EMS that allows us to:
// 1. view all employees
// 2. view particular employee
// 3. create employees
// 4. delete employees
// 5. update employees
// 6. view all employees in a singular department (ex: all employees in HR)
// Expect: Console Based Menu

public class Main {

	private static EmployeeManager manager;
	private static Scanner sc;

	public static void main(String[] args) {

		manager = new EmployeeManagerinMemory();
		sc = new Scanner(System.in);

		System.out.println("WELCOME TO THE EMPLOYEE MANAGEMENT SYSTEM (EMS)\n");

		mainMenu();

	}

	public static void mainMenu() {

		while (true) {

			try {
				System.out.println("\nPlease enter one of the following options :" 
									+ "\n1.) View Employees"
									+ "\n2.) Select Employee By Id" 
									+ "\n3.) Create Employee" 
									+ "\n4.) Update Employee"
									+ "\n5.) Delete Employee" 
									+ "\n6.) Exit");

				int option = sc.nextInt();
				sc.nextLine(); 

				switch (option) {
				case 1:
					viewEmployees();
					break;
				case 2: 
					selectEmployeeById();
					break;
				case 3:
					createEmployee();
					break;
				case 4: 
					updateEmployee();
					break;
				case 5: 
					deleteEmployee();
					break;
				case 6:
					break;

				default:
					System.out.println("\nPlease enter a number between 1 and 6 : ");
					break;
				}

				if (option == 6) { 
					System.out.println("Please come back again! ");
					sc.close();
					break; 
				}

			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("\nPlease input a number between 1 and 6: ");
			}
​			
		}
​
	}
	
	private static void deleteEmployee() {
		// TODO Auto-generated method stub
		if(manager.getAllEmployees().isEmpty()) {
			System.out.println("There are no employees in the EMS");
		}
		else {
	
			try {
				System.out.println("\nEnter ID of employee that you wish to delete: ");
				int id = sc.nextInt();
				sc.nextLine();				
				Employee empl = manager.findEmployeeById(id); 
				
				System.out.println("ARE YOU SURE YOU WANT TO DELETE? ENTER Y FOR YES, ANYTHING ELSE FOR NO");
				String decision = sc.next();
				sc.nextLine();
				
				switch (decision.toUpperCase()) {
				case "Y":
					manager.deleteEmployee(empl.getId());
					System.out.println("\nEmployee ("+empl.getName()+") with ID number (" + empl.getId() + ") has been deleted");
					break;

				default:
					System.out.println("DELETION WAS NOT PROCESSED");
					break;
				}
				
			}
			catch(InputMismatchException e) {
				System.out.println("\nPlease enter a number next time");
				sc.nextLine();
			}
			catch(EmployeeNotFoundException e) {
				System.out.println("ID number you entered couldn't be found");
			}
			
		}

	}

	private static void updateEmployee() {
		// TODO Auto-generated method stub
		int id;
		
		try {
			System.out.println("Please input ID of employee that you want to update: ");
			id = sc.nextInt();
			sc.nextLine();
			Employee empl = manager.findEmployeeById(id);
			
			while(true) {
				
				System.out.println("Employee info to be updated:\n" + empl);
				System.out.println("\nPlease select from the following to update: \n1. Name \n2. Department \n3. Salary \n4. Email \n5. EXIT");
				int option = sc.nextInt();
				sc.nextLine();
				
				switch (option) {
				case 1:
					System.out.println("Update name or enter int to continue:");
					String name = sc.next();
					sc.nextLine();
					if(name.equals("int")) {
						System.out.println("NO UPDATES INPUT\n");
						break;
					}
					empl.setName(name);
					manager.updateEmployee(empl);
					mainMenu();
					break;
					
				case 2: 
					System.out.println("Update department or enter  to continue:");
					String dept = sc.next();
					sc.nextLine();
					if(dept.equals("n")) {
						System.out.println("NO UPDATES INPUT\n");
						break;
					}
					empl.setDepartment(dept);
					manager.updateEmployee(empl);
					break;
					
				case 3:
					System.out.println("Update salary or enter 0 to continue:");
					int salary = sc.nextInt();
					sc.nextLine();
					if(salary == 0) {
						System.out.println("NO UPDATES INPUT\n");
						break;
					}
					empl.setSalary(salary);
					manager.updateEmployee(empl);
					mainMenu();
					break;
					
				case 4: 
					System.out.println("Update email or enter to continue:");
					String email = sc.next();
					sc.nextLine();
					if(email.equals("n")) {
						System.out.println("NO UPDATES INPUT\n");
						break;
					}
					empl.setEmail(email);
					manager.updateEmployee(empl);
					mainMenu();
					break;
					
				case 5:
					System.out.println("\nUPDATE FINISHED");
					break;
					
				default:
					System.out.println("Please enter a number between 1 and 5");
					break;
				}
				
				if(option == 5) {
					break;
				}
				
			}
			
		}catch(InputMismatchException e) {
			System.out.println("Incorrect input for one of the data inputs\n Try again next time");
			sc.nextLine();
		}catch(EmployeeNotFoundException e) {
			System.out.println("Employee id could not be found");
		}
	}

	private static void createEmployee() {
		// TODO Auto-generated method stub
		String name = "";
		String dept = "";
		int salary = 0;
		String email = "";
		int finish = 0;
		
		while(finish < 4) {
			try {
				if(!name.matches("^[A-Za-z]{2,}")) {
					System.out.println("Please enter name of new employee: ");
					name = sc.next();
					sc.nextLine();
					
				}else {finish++;}
						
				if(!dept.matches("^[A-Za-z]{2,}")) {
					System.out.println("\nPlease enter department of new employee:");
					dept = sc.next();
					sc.nextLine();
					
				}
				else {finish++;}
				
				if(salary <= 0) {
					System.out.println("\nPlease enter salary of new employee: ");
					salary = sc.nextInt();
					sc.nextLine();
					if(salary<=0)
						continue;
					finish++;
				}
				
				if(!email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
					System.out.println("\nPlease enter email of new employee: ");
					email = sc.next();
					sc.nextLine();
				}
				else {finish++;}
				
								
			}catch(InputMismatchException e) {
				System.out.println("Invalid input entered. Try again\n");
				sc.nextLine();
			}
		}
		System.out.println("\nAre you sure you want to create new employee? \nEnter 1 to proceed. \nEmployee info below: \nName: " + name  + "\nDepartment: " + dept + "\nSalary: "+salary + "\nEmail: " + email);
		String option = sc.next();
		switch (option.toUpperCase()) {
		case "1":
			manager.createEmployee( new Employee(1,name,dept,salary,email));
			System.out.println("New employee successfully created\n");
			break;

		default:
			System.out.println("No new employee was created");
			break;
		}
		
	}

	private static void selectEmployeeById() {
		// TODO Auto-generated method stub
		if(manager.getAllEmployees().isEmpty()) {
			System.out.println("There are no employees in the EMS");
		}
		else {
		
			try {
				
				System.out.println("Please enter ID of employee: ");
				int id = sc.nextInt(); 
				sc.nextLine();
				Employee empl = manager.findEmployeeById(id);
				System.out.println("\nEmployee with ID of " + id + "\n" +empl);
				
			}catch(InputMismatchException e) {
				System.out.println("ID number given is not valid number: ");
				sc.nextLine();
			}catch(EmployeeNotFoundException e) {
				System.out.println("Employee ID not found");
			}
			finally {
				System.out.println("Process of selecting complete");
			}
		
		}
	}

	public static void viewEmployees() {
		
		List<Employee> employees = manager.getAllEmployees();
		
		
		if(employees.isEmpty()) {
			System.out.println("No employees data currently in the EMS");
		}
		else {
			for(Employee e : employees) {
				System.out.println(e);
			}
		}
	}

}
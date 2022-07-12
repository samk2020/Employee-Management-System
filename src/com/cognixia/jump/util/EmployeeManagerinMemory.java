package com.cognixia.jump.util;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.exceptions.EmployeeNotFoundException;
import com.cognixia.jump.model.Employee;

public class EmployeeManagerinMemory implements EmployeeManager {

	private static int idCounter = 0;
	private static List<Employee> employeeList = new ArrayList<Employee>();
	
	static {
		employeeList.add(new Employee(idCounter++, "Anthony B", "Maintenance", 45000, "tonyb@exampleemail.com"));
		employeeList.add(new Employee(idCounter++, "Kevin S", "Art", 35000, "kevins@exampleemail.com"));
		employeeList.add(new Employee(idCounter++, "Justin S", "IT", 55000, "justins@exampleemail.com")); 
		employeeList.add(new Employee(idCounter++, "Trevor S", "Maintenance", 60000, "trevors@exampleemail.com"));
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeList;
	}

	@Override
	public Employee findEmployeeById(int id) throws EmployeeNotFoundException {
		
		for(Employee e : employeeList) {
			if(e.getId() == id) {
				return e;
			}
		}
		
		throw new EmployeeNotFoundException(id);
	}

	@Override
	public boolean createEmployee(Employee empl) {
		
		// reset id to be unique using the counter
		empl.setId(idCounter++);
		
		employeeList.add(empl);
		
		return false;
	}
	// Needs completion before friday may 6th, 2pm pdt
	@Override
	public boolean deleteEmployee(int id) {
		// TODO Auto-generated method stub 
		Employee delEmployee = employeeList.stream()
				.filter( e -> e.getId() == id)
				.findFirst()
				.get(); 
		
		employeeList.remove(delEmployee);
		
		return false;
		/* old code below
		boolean indexOfEmployeeId = deleteEmployee(id);
        if (indexOfEmployeeId > -1) { //!= instead of >
        	employeeList.remove(indexOfEmployeeId);
            return true;
        }
		return false; 
		*/
	}

	@Override
	public boolean updateEmployee(Employee empl) {
		Object employee;
		// TODO Auto-generated method stub  
		/* old code below
		if (this.employeeList.contains(((Object) employee).getEmployeeId())) {
            this.employeeList.put(((Object) employee).getEmployeeId(), employee);
            return true;
        } else {
            return false;
        }
	
	}
	*/ 
		if(!empl.getName().contains(employeeList.get(empl.getId()).getName())) {
		
			System.out.println("\nEmployee's name changed to " + empl.getName());
			employeeList.get(empl.getId()).setName(empl.getName());
			return false;
			
		}
		
		if(!empl.getDepartment().contains(employeeList.get(empl.getId()).getDepartment())) {
		
			System.out.println("\nEmployee's department changed to " + empl.getDepartment());
			employeeList.get(empl.getId()).setDepartment(empl.getDepartment());
			return false;
		}
		
		if(empl.getSalary() != (employeeList.get(empl.getId()).getSalary())) {
		
			System.out.println("\nEmployee's salary changed to " + empl.getSalary());
			employeeList.get(empl.getId()).setSalary(empl.getSalary());
			return false;
		}
		
		if(!empl.getEmail().contains(employeeList.get(empl.getId()).getEmail())) {
		
			System.out.println("\nEmployee's email changed to " + empl.getEmail());
			employeeList.get(empl.getId()).setEmail(empl.getEmail());
			return false;
		} 
		return false;
	}
	
	
	@Override
	public List<Employee> getEmployeesByDepartment(String dept) {
		// TODO Auto-generated method stub 
		if (this.employeeList.contains(dept)) {
            List<Employee> list = (List<Employee>) EmployeeManagerinMemory.employeeList.get(0);
			return list;
        } else {
            return null;
        }
		
	}

}
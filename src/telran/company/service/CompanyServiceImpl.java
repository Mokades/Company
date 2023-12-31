package telran.company.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import telran.company.dto.DepartmentAvgSalary;
import telran.company.dto.Employee;
import telran.company.dto.SalaryIntervalDistribution;

public class CompanyServiceImpl implements CompanyService {
	HashMap<Long, Employee> employeesMap = new HashMap<>();
	/***********************************************************/
	HashMap<String, Set<Employee>> employeesDepartment = new HashMap<>();
	//key - department, value- Set of employees working in the department
	/*************************************************************/
	TreeMap<Integer, Set<Employee>> employeesSalary = new TreeMap<>();
	//key - salary, value - set of employees having the salary value
	/****************************************************************/
	TreeMap<LocalDate, Set<Employee>> employeesAge = new TreeMap<>();
	//key birth date; value set of employees born at the date
	/*******************************************************************/
	@Override
	/**
	 * adds new Employee into a company
	 * In the case an employee with the given ID already exists,
	 *  the exception IllegalStateException must be thrown
	 *  returns reference to the being added Employee object
	 */
	public Employee hireEmployee(Employee empl) {
		long id = empl.id();
		if(employeesMap.containsKey(id)){
			throw new IllegalStateException("Employee already exists " + id);
		}
		employeesMap.put(id, empl);
		addEmployeeSalary(empl);
		addEmployeeDepartment(empl);
		addEmployeeAge(empl);
		return empl;
	}

	private void addEmployeeAge(Employee empl) {
		LocalDate birthdate = empl.birthDate();
		Set<Employee> set =
				employeesAge.computeIfAbsent(birthdate, k -> new HashSet<>());
		set.add(empl);
	}

	private void addEmployeeDepartment(Employee empl) {
		String department = empl.department();
//		Set<Employee> set = employeesDepartment.computeIfAbsent(department, k -> new HashSet<>());
//		set.add(empl);
		Set<Employee> set = employeesDepartment.get(department);
		if (set == null) {
			set = new HashSet<>();
			employeesDepartment.put(department, set);
		}
		set.add(empl);
		
	}

	private void addEmployeeSalary(Employee empl) {
		employeesSalary.computeIfAbsent(empl.salary(), k -> new HashSet<>())
		.add(empl);
		
	}
	

	@Override
	/**
	 * removes employee object from company according to a given ID
	 * In the case an employee with the given ID doesn't exist 
	 * the method must throw IllegalStateException
	 */
	public Employee fireEmployee(long id) {
		Employee empl = employeesMap.remove(id);
		if(empl == null) {
			throw new IllegalStateException("Employee not found " + id);
		}
		removeEmployeesDepartment(empl);
		removeEmployeesSalary(empl);
		removeEmployeesAge(empl);
		return empl;
	}

	private void removeEmployeesAge(Employee empl) {
		LocalDate birthDate = empl.birthDate();
		Set<Employee> set = employeesAge.get(birthDate);
		set.remove(empl); //removing reference to being
		//removed
		//employee from the set
		// of employees with the given birth date
		if (set.isEmpty()) {
			employeesAge.remove(birthDate);
		}
		
	}

	private void removeEmployeesSalary(Employee empl) {
		int salary = empl.salary();
		Set<Employee> set = employeesSalary.get(salary);
		set.remove(empl);
		if(set.isEmpty()) {
			employeesSalary.remove(salary);
		}
		
	}

	private void removeEmployeesDepartment(Employee empl) {
		String department = empl.department();
		Set<Employee> set = employeesDepartment.get(department);
		set.remove(empl);
		if(set.isEmpty()) {
			employeesDepartment.remove(department);
		}
		
	}

	@Override
	/**
	 * returns reference to Employee object with a given ID value
	 * in the case employee with the ID doesn't exist
	 * the method returns null
	 */
	public Employee getEmployee(long id) {
		
		return employeesMap.get(id);
	}

	@Override
	/**
	 * returns list of employee objects working in a given department
	 * in the case none employees in the department, the method returns empty list
	 */
	public List<Employee> getEmployeesByDepartment(String department) {
		//in the case no employees in the given department the empty collection should be returned
		Set<Employee> setEmployeesDep =
				employeesDepartment.getOrDefault(department, new HashSet<>());
		
		return new ArrayList<>(setEmployeesDep);
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return new ArrayList<>(employeesMap.values());
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		Collection<Set<Employee>> col = employeesSalary.subMap(salaryFrom, salaryTo).values();
		ArrayList<Employee> res = new ArrayList<>();
		for(Set<Employee> set: col) {
			res.addAll(set);
		}
		
		return res;
	}

	@Override
	public List<Employee> getEmployeeByAge(int ageFrom, int ageTo) {
		LocalDate dateFrom = getBirthDate(ageTo);
		LocalDate dateTo = getBirthDate(ageFrom);
		Collection<Set<Employee>> col = employeesAge.subMap(dateFrom, dateTo).values();
		ArrayList<Employee> res = new ArrayList<>();
		for(Set<Employee> set: col) {
			res.addAll(set);
		}
		return res;
	}

	private LocalDate getBirthDate(int age) {
		
		return LocalDate.now().minusYears(age);
	}

	@Override
	public List<DepartmentAvgSalary> salaryDistributionByDepartments() {
	    Map<String, Double> map = employeesMap.values().stream().collect(Collectors.groupingBy(empl -> empl.department(), Collectors.averagingLong(empl -> empl.salary())));
	    //.values() ключ id, значение employee, получаем всех, группируем по отделам
	    //.averagingInt для указания, что усреднять работников нужно по зп
	   
	    return map.entrySet().stream().map(e -> new DepartmentAvgSalary(e.getKey(), e.getValue().intValue())).toList();
	  }

	@Override
	public List<SalaryIntervalDistribution> getSalaryDistribution(int interval) {
		// TODO Auto-generated method stub O[N]
		return null;
	}

	@Override
	public Employee updateDepartment(long id, String newDepartment) {
		Employee empl = fireEmployee(id);
		Employee newEmployee = new Employee(id, empl.name(), empl.salary(),
				newDepartment, empl.birthDate());
		
		return hireEmployee(newEmployee);
	}

	@Override
	public Employee updateSalary(long id, int newSalary) {
		Employee empl = fireEmployee(id);
		Employee newEmployee = new Employee(id, empl.name(), newSalary,
				empl.department(), empl.birthDate());
		
		return hireEmployee(newEmployee);
	}

	@Override
	public void save(String filePath) {
		// TODO Auto-generated method stub O[N]

	}

	@Override
	public void restore(String filePath) {
		// TODO Auto-generated method stub O[N]

	}

}
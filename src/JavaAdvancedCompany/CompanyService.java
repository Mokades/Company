package JavaAdvancedCompany;

import java.util.*;

import JavaAdvanceddto.DepartmentAvgSalary;
import JavaAdvanceddto.Employee;
import JavaAdvanceddto.SalaryIntervalDistribution;

public interface CompanyService {
	Employee hireEmployee (Employee empl);
	Employee fireEmployee (long id);
	Employee getEmployee(long id);
	List<Employee> getEmployeesByDepartment(String department);
	List<Employee> getAllEmployees();
	List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo);
	List<Employee> getEmployeeByAge(int ageFrom, int ageTo);
	List<DepartmentAvgSalary> salaryDistributionByDepartments();
	List<SalaryIntervalDistribution> getSalaryDistribution(int interval);
	Employee updateDeaprtment(long id, String newDepartment);
	void save(String filepath);
	void restore(String filepath);

}

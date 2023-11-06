package JavaAdvancedTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import JavaAdvanceddto.Employee;

class CompanyTest {
	
private static final long id1 = 0;
private static final int salary1 = 0;
private static final String department1 = null;
private static final LocalDate date1 = null;
private static final long id2 = 0;
private static final int salary2 = 0;
private static final String department2 = null;
private static final LocalDate date2 = null;

Employee empl1 = new Employee(id1, "name1", salary1, department1, date1);
Employee empl2 = new Employee(id2, "name1", salary2, department2, date2);
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testHireEmployee() {
		fail("Not yet implemented");
	}

	@Test
	void testFireEmployee() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEmployee() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEmployeesByDepartment() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllEmployees() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEmployeesBySalary() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEmployeeByAge() {
		fail("Not yet implemented");
	}

	@Test
	void testSalaryDistributionByDepartments() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSalaryDistribution() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateDeaprtment() {
		fail("Not yet implemented");
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testRestore() {
		fail("Not yet implemented");
	}

}

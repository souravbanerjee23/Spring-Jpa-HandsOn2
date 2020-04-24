package com.cognizant.ormlearn;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.StockService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

	private static CountryService countryService;
	private static StockRepository stockRepository;

	private static StockService stockService;
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static SkillService skillService;

	public static StockRepository getStockRepository() {
		return stockRepository;
	}

	@Autowired
	public void setStockRepository(StockRepository stockRepository) {
		OrmLearnApplication.stockRepository = stockRepository;
	}

	@SuppressWarnings("unused")
	private static void display() {
		stockRepository.findAll();
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		countryService = context.getBean(CountryService.class);
		stockService = context.getBean(StockService.class);
		employeeService = context.getBean(EmployeeService.class);
		departmentService = context.getBean(DepartmentService.class);
		skillService = context.getBean(SkillService.class);
		// System.out.println("hello");
		// LOGGER.info("Inside main");
		// testGetAllCountries();
		// testAddCountry();
		// testDeleteCountry();
		// testGetCountry();
		// testUpadateCountry();
		// testFindByNameContains();
		// display();
		// System.out.println(stockService.getAllStock());
		// System.out.println(stockService.getDate());
		// System.out.println(stockService.getGoogle());
		// System.out.println(stockService.getMax());
		// System.out.println(stockService.getLowest());
		// testGetEmployee();
		// testAddEmployee();
		// testUpdateEmployee();
		// testGetDepartment();
		testAddSkillToEmployee();
	}

	private static void testGetAllCountries() {

		LOGGER.info("Start");

		List<Country> countries = countryService.getAllCountries();

		LOGGER.debug("countries={}", countries);

		LOGGER.info("End");

	}

	private static void testGetCountry() throws CountryNotFoundException {
		LOGGER.info("Start");
		Country country = countryService.findCountryByCode("IN");
		LOGGER.debug("Country:{}", country);
		LOGGER.info("End");

	}

	private static void testAddCountry() throws CountryNotFoundException {
		LOGGER.info("Start");
		Country country1 = new Country();
		country1.setCode("SS");
		country1.setName("South Sudan");
		countryService.addCountry(country1);
		Country country2 = countryService.findCountryByCode("SS");
		LOGGER.debug("Country:{}", country2);
		LOGGER.info("End");
	}

	private static void testUpadateCountry() throws CountryNotFoundException {
		LOGGER.info("Start");
		countryService.updateCountry("SS", "Kingdom of South Sudan");
		LOGGER.info("SS updated");
		Country country = countryService.findCountryByCode("SS");
		LOGGER.debug("Country:{}", country);
		LOGGER.info("End");
	}

	private static void testDeleteCountry() {
		LOGGER.info("Start");
		countryService.deleteCountry("SS");
		LOGGER.info("SS deleted");
		System.out.println("SS deleted");
		testGetAllCountries();
		LOGGER.info("End");
	}

	private static void testFindByNameContains() throws CountryNotFoundException {
		LOGGER.info("Start");
		List<Country> result = countryService.findByName();
		result.forEach(System.out::println);

		LOGGER.info("End");

	}

	private static void testGetEmployee() {

		LOGGER.info("Start");

		Employee employee = employeeService.get(1);

		LOGGER.debug("Employee:{}", employee);

		LOGGER.debug("Department:{}", employee.getDepartment());
		LOGGER.debug("Skills:{}", employee.getSkillList());

		LOGGER.info("End");

	}

	private static void testAddEmployee() {
		LOGGER.info("Start");
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("Sourav");
		emp.setSalary(100000);
		emp.setPermanent(true);
		emp.setDateOfBirth(new Date(1997 - 01 - 23));
		Department department = departmentService.get(1);
		emp.setDepartment(department);
		employeeService.save(emp);
		LOGGER.debug("Employee:{}", emp);
		LOGGER.info("Start");
	}

	private static void testUpdateEmployee() {
		LOGGER.info("Start");
		Employee emp2 = employeeService.get(1);
		Department dept = departmentService.get(2);
		emp2.setDepartment(dept);
		employeeService.save(emp2);

		LOGGER.debug("Employee:{}", emp2);
		LOGGER.info("End");

	}

	private static void testGetDepartment() {
		LOGGER.info("Start");
		Department dept2 = departmentService.get(2);
		Set<Employee> employeeList = dept2.getEmployeeList();
		LOGGER.info("End");
	}

	private static void testAddSkillToEmployee() {
		LOGGER.info("Start");
		Employee emp3 = employeeService.get(1);
		Skill skill = skillService.get(2);
		Set<Skill> skillList = emp3.getSkillList();
		skillList.add(skill);
		employeeService.save(emp3);
		LOGGER.debug("Employee:{}", emp3);
		LOGGER.info("End");
	}
}

package vn.techmaster.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import vn.techmaster.example.entity.Employee;
import vn.techmaster.example.repository.EmployeeRepository;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Rollback(value = false)
    void save_employee() {
        Employee emp1 = new Employee(null, "hien@gmail.com", "Bui", "Hien");
        Employee emp2 = new Employee(null, "hang@gmail.com", "Nguyen", "Hang");
        Employee emp3 = new Employee(null, "thuhang@gmail.com", "Nguyen", "Hang");
        Employee emp4 = new Employee(null, "duy@gmail.com", "Minh", "Duy");
        Employee emp5 = new Employee(null, "an@gmail.com", "Minh", "An");
        Employee emp6 = new Employee(null, "kien@gmail.com", "Hoang", "Kien");

        employeeRepository.saveAll(List.of(emp1, emp2, emp3, emp4, emp5, emp6));
    }

    @Test
    void test_getByEmailAddressAndLastName() {
        List<Employee> employees = employeeRepository.getByEmailAddressAndLastName("hien@gmail.com", "Hien");
        employees.forEach(System.out::println);

        List<Employee> employees1 = employeeRepository.getByEmailAddressAndLastName("an@gmail.com", "Hien");
        employees1.forEach(System.out::println);
    }

    @Test
    void test_getDistinctByFirstNameOrLastName() {
    }

    @Test
    void test_getByLastNameOrderByFirstNameDesc() {
    }

    @Test
    void test_getByFirstNameIgnoreCase() {

    }

    @Test
    void test_getUserInfoByIdByNamedQuery() {
        Employee employee = employeeRepository.getUserInfoByIdByNamedQuery(7L);
        System.out.println(employee);
    }

    @Test
    void test_getUserInfoByIdByJpaQuery() {
        Employee employee = employeeRepository.getUserInfoByIdByJpaQuery(7L);
        System.out.println(employee);
    }

    @Test
    void test_getUserInfoByIdNativeQuery() {
        Employee employee = employeeRepository.getUserInfoByIdNativeQuery(7L);
        System.out.println(employee);
    }

    @Test
    void test_getUserInfoByIdNativeQueryOther() {
        Employee employee = employeeRepository.getUserInfoByIdNativeQueryOther(7L);
        System.out.println(employee);
    }

    @Test
    void test_getEmployeeInfo() {
        Employee employee = employeeRepository.getEmployeeInfo(7L);
        System.out.println(employee);
    }
}

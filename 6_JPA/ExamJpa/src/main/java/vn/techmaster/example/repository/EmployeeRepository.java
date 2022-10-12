package vn.techmaster.example.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.techmaster.example.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeCustomRepository {
    // Câu 8
    List<Employee> getByEmailAddressAndLastName(String emailAddress, String lastName);

    List<Employee> getDistinctByFirstNameOrLastName(String firstName, String lastName);

    List<Employee> getByLastNameOrderByFirstNameDesc(String lastName);

    List<Employee> getByFirstNameIgnoreCase(String firstName);

    // Câu 9
    @Query(name = "getUserInfoByIdNamedQuery")
    Employee getUserInfoByIdByNamedQuery(Long id);

    @Query("select e from Employee e where e.id = ?1")
    Employee getUserInfoByIdByJpaQuery(Long id);

    @Query(nativeQuery = true, name = "getUserInfoByIdNativeQuery")
    Employee getUserInfoByIdNativeQuery(Long id);

    @Query(nativeQuery = true, name = "getUserInfoByIdNativeQueryOther")
    Employee getUserInfoByIdNativeQueryOther(Long id);

    // Câu 11
    // List<Employee> getByEmailAddress(String emailAddress, Sort sort);

    // List<Employee> getByEmailAddress(String emailAddress, Sort sort, Pageable pageable);
}

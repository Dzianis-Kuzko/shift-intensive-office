package by.koronatech.office.core.repository;

import by.koronatech.office.core.entity.Department;
import by.koronatech.office.core.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Page<Employee> findByDepartment(Department department, Pageable pageable);
}

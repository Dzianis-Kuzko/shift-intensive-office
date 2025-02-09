package by.koronatech.office.core.service;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.api.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    Page<DepartmentDTO> getAll(Pageable pageable);

    Page<EmployeeDTO> getEmployeesByDepartment(Integer id, Pageable pageable);
}

package by.koronatech.office.core.service;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.api.dto.EmployeeDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> get();

    List<EmployeeDTO> getEmployeesByDepartment(Integer id);
}

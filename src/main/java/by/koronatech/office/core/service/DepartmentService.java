package by.koronatech.office.core.service;

import by.koronatech.office.api.dto.GetDepartmentDTO;
import by.koronatech.office.api.dto.GetEmployeeDTO;

import java.util.List;

public interface DepartmentService {
    List<GetDepartmentDTO> get();

    List<GetEmployeeDTO> getEmployeesByDepartment(Integer id);
}

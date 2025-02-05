package by.koronatech.office.core.service;

import by.koronatech.office.api.dto.CreateEmployeeDTO;
import by.koronatech.office.api.dto.GetEmployeeDTO;

public interface EmployeeService {
    GetEmployeeDTO create(CreateEmployeeDTO createEmployeeDTO);

    GetEmployeeDTO promoteToManager(Integer id);

    GetEmployeeDTO update(Integer id, CreateEmployeeDTO updateEmployeeDTO);

    void delete(Integer id);
}

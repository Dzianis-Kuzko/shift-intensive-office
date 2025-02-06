package by.koronatech.office.core.service;

import by.koronatech.office.api.dto.SaveEmployeeDTO;
import by.koronatech.office.api.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO create(SaveEmployeeDTO saveEmployeeDTO);

    EmployeeDTO promoteToManager(Integer id);

    EmployeeDTO update(Integer id, SaveEmployeeDTO updateEmployeeDTO);

    void delete(Integer id);
}

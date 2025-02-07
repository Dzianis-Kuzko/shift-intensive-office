package by.koronatech.office.core.mapper;

import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.api.dto.SaveEmployeeDTO;
import by.koronatech.office.core.entity.Department;
import by.koronatech.office.core.entity.Employee;


public class EmployeeMapper {

    public static EmployeeDTO toGetEmployeeDTO(Employee employee) {
        if (employee == null) {
            return null;
        }

        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getSalary(),
                employee.getDepartment().getName(),
                employee.isManager()
        );
    }

    public static Employee fromSaveEmployeeDTO(SaveEmployeeDTO saveEmployeeDTO, Department department) {
        if (saveEmployeeDTO == null) {
            return null;
        }

        return new Employee(
                null,
                saveEmployeeDTO.getName(),
                saveEmployeeDTO.getSalary(),
                department,
                saveEmployeeDTO.isManager()
        );
    }
}

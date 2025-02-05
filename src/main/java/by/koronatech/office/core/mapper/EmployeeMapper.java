package by.koronatech.office.core.mapper;

import by.koronatech.office.api.dto.CreateEmployeeDTO;
import by.koronatech.office.api.dto.GetEmployeeDTO;
import by.koronatech.office.core.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;


public class EmployeeMapper {
    public static GetEmployeeDTO toGetEmployeeDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        return new GetEmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getSalary(),
                employee.getDepartment(),
                employee.getManager()
        );
    }

    public static List<GetEmployeeDTO> toEmployeeDTOList(List<Employee> employees) {
        if (employees == null) {
            return null;
        }
        return employees.stream()
                .map(EmployeeMapper::toGetEmployeeDTO)
                .collect(Collectors.toList());
    }

    public static Employee fromCreateEmployeeDTO(CreateEmployeeDTO createEmployeeDTO) {
        if (createEmployeeDTO == null) {
            return null;
        }
        return new Employee(
                null,
                createEmployeeDTO.getName(),
                createEmployeeDTO.getSalary(),
                createEmployeeDTO.getDepartment(),
                createEmployeeDTO.getManager()
        );
    }
}

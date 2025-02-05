package by.koronatech.office.core.service.inpl;

import by.koronatech.office.api.dto.GetDepartmentDTO;
import by.koronatech.office.api.dto.GetEmployeeDTO;
import by.koronatech.office.core.entity.Department;
import by.koronatech.office.core.mapper.DepartmentMapper;
import by.koronatech.office.core.mapper.EmployeeMapper;
import by.koronatech.office.core.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final List<Department> departments = new ArrayList<>();
    private final EmployeeServiceImpl employeeService;


    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;

        departments.add(new Department(1, "Бухгалтерия"));
        departments.add(new Department(2, "Маркетинг"));
        departments.add(new Department(3, "Продажи"));
        departments.add(new Department(4, "HR"));
        departments.add(new Department(5, "IT"));
        departments.add(new Department(6, "Юридический"));
        departments.add(new Department(7, "Логистика"));
        departments.add(new Department(8, "Разработка"));
        departments.add(new Department(9, "Клиентская поддержка"));
        departments.add(new Department(10, "Исследования и разработки"));
    }

    @Override
    public List<GetDepartmentDTO> get() {
        return DepartmentMapper.toGetDepartmentDTOList(departments);
    }

    @Override
    public List<GetEmployeeDTO> getEmployeesByDepartment(Integer departmentId) {
        String departmentName = departments.stream()
                .filter(department -> department.getId().equals(departmentId))
                .map(Department::getName)
                .findFirst()
                .orElse(null);

        if (departmentName == null) {
            return Collections.emptyList();
        }

        return employeeService.getEmployees().stream()
                .filter(employee -> employee.getDepartment().equals(departmentName))
                .map(EmployeeMapper::toGetEmployeeDTO)
                .collect(Collectors.toList());
    }
}

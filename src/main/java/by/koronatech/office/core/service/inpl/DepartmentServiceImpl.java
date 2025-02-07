package by.koronatech.office.core.service.inpl;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.core.entity.Department;
import by.koronatech.office.core.entity.Employee;
import by.koronatech.office.core.exception.DepartmentNotFoundException;
import by.koronatech.office.core.mapper.DepartmentMapper;
import by.koronatech.office.core.mapper.EmployeeMapper;
import by.koronatech.office.core.repository.DepartmentRepository;
import by.koronatech.office.core.repository.EmployeeRepository;
import by.koronatech.office.core.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<DepartmentDTO> get() {
        return DepartmentMapper.toGetDepartmentDTOList(departmentRepository.findAll());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDepartment(Integer departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        List<Employee> employeesInDepartment = employeeRepository.findByDepartment(department);

        return EmployeeMapper.toEmployeeDTOList(employeesInDepartment);
    }
}

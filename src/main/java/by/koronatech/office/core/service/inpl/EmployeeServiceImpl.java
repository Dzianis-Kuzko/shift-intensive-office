package by.koronatech.office.core.service.inpl;

import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.api.dto.SaveEmployeeDTO;
import by.koronatech.office.core.entity.Department;
import by.koronatech.office.core.entity.Employee;
import by.koronatech.office.core.exception.DepartmentNotFoundException;
import by.koronatech.office.core.exception.EmployeeNotFoundException;
import by.koronatech.office.core.mapper.EmployeeMapper;
import by.koronatech.office.core.repository.DepartmentRepository;
import by.koronatech.office.core.repository.EmployeeRepository;
import by.koronatech.office.core.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeDTO create(SaveEmployeeDTO saveEmployeeDTO) {
        Department department = departmentRepository.findByName(saveEmployeeDTO.getDepartment())
                .orElseThrow(() -> new DepartmentNotFoundException(saveEmployeeDTO.getDepartment()));

        Employee newEmployee = EmployeeMapper.fromSaveEmployeeDTO(saveEmployeeDTO, department);
        Employee savedEmployee = employeeRepository.save(newEmployee);
        return EmployeeMapper.toGetEmployeeDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO promoteToManager(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employee.setManager(true);
        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toGetEmployeeDTO(updatedEmployee);
    }

    @Override
    public EmployeeDTO update(Integer id, SaveEmployeeDTO saveEmployeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        Department department = departmentRepository.findByName(saveEmployeeDTO.getDepartment())
                .orElseThrow(() -> new DepartmentNotFoundException(saveEmployeeDTO.getDepartment()));

        existingEmployee.setName(saveEmployeeDTO.getName());
        existingEmployee.setSalary(saveEmployeeDTO.getSalary());
        existingEmployee.setDepartment(department);
        existingEmployee.setManager(saveEmployeeDTO.isManager());

        Employee savedEmployee = employeeRepository.save(existingEmployee);

        return EmployeeMapper.toGetEmployeeDTO(savedEmployee);
    }

    @Override
    public void delete(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }

}

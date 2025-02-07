package by.koronatech.office.core.service.inpl;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.core.entity.Department;
import by.koronatech.office.core.exception.DepartmentNotFoundException;
import by.koronatech.office.core.mapper.DepartmentMapper;
import by.koronatech.office.core.mapper.EmployeeMapper;
import by.koronatech.office.core.repository.DepartmentRepository;
import by.koronatech.office.core.repository.EmployeeRepository;
import by.koronatech.office.core.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Page<DepartmentDTO> getAll(Pageable pageable) {
        return departmentRepository.findAll(pageable)
                .map(DepartmentMapper::toGetDepartmentDTO);
    }

    @Override
    public Page<EmployeeDTO> getEmployeesByDepartment(Integer departmentId, Pageable pageable) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        return employeeRepository.findByDepartment(department, pageable).map(EmployeeMapper::toGetEmployeeDTO);
    }
}

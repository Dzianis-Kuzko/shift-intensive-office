package by.koronatech.office.core.service.inpl;

import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.api.dto.SaveEmployeeDTO;
import by.koronatech.office.core.entity.Department;
import by.koronatech.office.core.entity.Employee;
import by.koronatech.office.core.exception.DepartmentNotFoundException;
import by.koronatech.office.core.exception.EmployeeNotFoundException;
import by.koronatech.office.core.repository.DepartmentRepository;
import by.koronatech.office.core.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private SaveEmployeeDTO saveEmployeeDTO;
    private Employee employee;
    private EmployeeDTO expectedEmployeeDTO;
    private Department department;

    @BeforeEach
    public void setUp() {
        department = new Department(1, "Developer");

        saveEmployeeDTO = new SaveEmployeeDTO(
                "Den",
                new BigDecimal("50.00"),
                "Developer",
                false
        );

        employee = new Employee(1, "Den", new BigDecimal("50.00"), department, false);
        expectedEmployeeDTO = new EmployeeDTO(
                1,
                "Den",
                new BigDecimal("50.00"),
                "Developer",
                false);
    }

    @Test
    public void testCreateWithSuccess() {
        when(departmentRepository.findByName(saveEmployeeDTO.getDepartment()))
                .thenReturn(Optional.of(department));

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDTO result = employeeService.create(saveEmployeeDTO);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(expectedEmployeeDTO.getId());
        assertThat(result.getName()).isEqualTo(expectedEmployeeDTO.getName());
        assertThat(result.getSalary()).isEqualByComparingTo(expectedEmployeeDTO.getSalary());
        assertThat(result.getDepartment()).isEqualTo(expectedEmployeeDTO.getDepartment());
        assertThat(result.isManager()).isEqualTo(expectedEmployeeDTO.isManager());

        verify(departmentRepository).findByName(saveEmployeeDTO.getDepartment());
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    public void testCreateWithFault() {
        when(departmentRepository.findByName(saveEmployeeDTO.getDepartment()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.create(saveEmployeeDTO))
                .isInstanceOf(DepartmentNotFoundException.class)
                .hasMessage(new DepartmentNotFoundException(saveEmployeeDTO.getDepartment()).getMessage());

        verify(departmentRepository).findByName(saveEmployeeDTO.getDepartment());
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    public void testPromoteToManagerWithSuccess() {
        when(employeeRepository.findById(employee.getId()))
                .thenReturn(Optional.of(employee));

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        EmployeeDTO result = employeeService.promoteToManager(employee.getId());

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(expectedEmployeeDTO.getId());
        assertThat(result.getName()).isEqualTo(expectedEmployeeDTO.getName());
        assertThat(result.getSalary()).isEqualByComparingTo(expectedEmployeeDTO.getSalary());
        assertThat(result.getDepartment()).isEqualTo(expectedEmployeeDTO.getDepartment());
        assertThat(result.isManager()).isTrue();

        verify(employeeRepository).findById(employee.getId());
        verify(employeeRepository).save(employee);

    }

    @Test
    public void testPromoteToManagerWithFault() {
        int nonExistingId = 10;

        when(employeeRepository.findById(nonExistingId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.promoteToManager(nonExistingId))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessage(new EmployeeNotFoundException(nonExistingId).getMessage());

        verify(employeeRepository).findById(nonExistingId);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    public void testUpdateWithSuccess() {
        Department newDepartment = new Department(2, "HR");
        saveEmployeeDTO.setDepartment(newDepartment.getName());
        expectedEmployeeDTO.setDepartment(newDepartment.getName());

        when(employeeRepository.findById(employee.getId()))
                .thenReturn(Optional.of(employee));

        when(departmentRepository.findByName(saveEmployeeDTO.getDepartment()))
                .thenReturn(Optional.of(newDepartment));

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        EmployeeDTO result = employeeService.update(employee.getId(), saveEmployeeDTO);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(expectedEmployeeDTO.getId());
        assertThat(result.getName()).isEqualTo(expectedEmployeeDTO.getName());
        assertThat(result.getSalary()).isEqualByComparingTo(expectedEmployeeDTO.getSalary());
        assertThat(result.getDepartment()).isEqualTo(expectedEmployeeDTO.getDepartment());
        assertThat(result.isManager()).isEqualTo(expectedEmployeeDTO.isManager());

        verify(employeeRepository).findById(employee.getId());
        verify(departmentRepository).findByName(saveEmployeeDTO.getDepartment());
        verify(employeeRepository).save(employee);
    }

    @Test
    public void testUpdateWithFault() {
        when(employeeRepository.findById(employee.getId()))
                .thenReturn(Optional.of(employee));

        when(departmentRepository.findByName(saveEmployeeDTO.getDepartment()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.update(employee.getId(), saveEmployeeDTO))
                .isInstanceOf(DepartmentNotFoundException.class)
                .hasMessage(new DepartmentNotFoundException(saveEmployeeDTO.getDepartment()).getMessage());

        verify(employeeRepository).findById(employee.getId());
        verify(departmentRepository).findByName(saveEmployeeDTO.getDepartment());
        verify(employeeRepository, never()).save(any(Employee.class));
    }


    @Test
    void testDeleteWithSuccess() {

        when(employeeRepository.existsById(employee.getId())).thenReturn(true);

        employeeService.delete(employee.getId());

        verify(employeeRepository).existsById(employee.getId());
        verify(employeeRepository).deleteById(employee.getId());
    }


    @Test
    public void testDeleteWithFault() {
        when(employeeRepository.existsById(employee.getId())).thenReturn(false);

        assertThatThrownBy( () -> employeeService.delete(employee.getId()))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessage(new EmployeeNotFoundException(employee.getId()).getMessage());

        verify(employeeRepository).existsById(employee.getId());
        verify(employeeRepository, never()).deleteById(anyInt());
    }
}
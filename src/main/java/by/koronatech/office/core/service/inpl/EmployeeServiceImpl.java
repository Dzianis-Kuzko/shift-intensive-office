package by.koronatech.office.core.service.inpl;

import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.api.dto.SaveEmployeeDTO;
import by.koronatech.office.core.entity.Employee;
import by.koronatech.office.core.exception.EmployeeNotFoundException;
import by.koronatech.office.core.mapper.EmployeeMapper;
import by.koronatech.office.core.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeServiceImpl() {

        employees.add(new Employee(1, "Полякова Светлана", new BigDecimal(500.1), "Бухгалтерия", false));
        employees.add(new Employee(2, "Иванов Иван", new BigDecimal(650.5), "Бухгалтерия", false));
        employees.add(new Employee(3, "Смирнова Екатерина", new BigDecimal(720.8), "Маркетинг", false));
        employees.add(new Employee(4, "Кузнецов Артем", new BigDecimal(800.2), "Маркетинг", false));
        employees.add(new Employee(5, "Петров Александр", new BigDecimal(900.4), "Продажи", true));
        employees.add(new Employee(6, "Морозова Алина", new BigDecimal(480.3), "Продажи", false));
        employees.add(new Employee(7, "Соколова Наталья", new BigDecimal(600.9), "HR", false));
        employees.add(new Employee(8, "Григорьев Роман", new BigDecimal(850.0), "HR", true));
        employees.add(new Employee(9, "Дмитриев Андрей", new BigDecimal(1000.0), "IT", true));
        employees.add(new Employee(10, "Васильева Ирина", new BigDecimal(730.6), "IT", false));
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public EmployeeDTO create(SaveEmployeeDTO saveEmployeeDTO) {
        int maxId = employees.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0);

        Employee newEmployee = EmployeeMapper.fromSaveEmployeeDTO(saveEmployeeDTO);
        newEmployee.setId(maxId + 1);
        employees.add(newEmployee);

        return EmployeeMapper.toGetEmployeeDTO(newEmployee);
    }

    @Override
    public EmployeeDTO promoteToManager(Integer id) {
        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employee.setManager(true);
        return EmployeeMapper.toGetEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO update(Integer id, SaveEmployeeDTO saveEmployeeDTO) {
        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        Employee updatedEmployee = EmployeeMapper.fromSaveEmployeeDTO(saveEmployeeDTO);
        updatedEmployee.setId(id);
        employees.set(employees.indexOf(employee), updatedEmployee);
        return EmployeeMapper.toGetEmployeeDTO(updatedEmployee);
    }

    @Override
    public void delete(Integer id) {
        employees.removeIf(e -> e.getId().equals(id));
    }

}

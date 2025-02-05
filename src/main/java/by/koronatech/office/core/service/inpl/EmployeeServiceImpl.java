package by.koronatech.office.core.service.inpl;

import by.koronatech.office.api.dto.CreateEmployeeDTO;
import by.koronatech.office.api.dto.GetEmployeeDTO;
import by.koronatech.office.core.entity.Employee;
import by.koronatech.office.core.mapper.EmployeeMapper;
import by.koronatech.office.core.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeServiceImpl() {

        employees.add(new Employee(1, "Полякова Светлана", 500.1, "Бухгалтерия", false));
        employees.add(new Employee(2, "Иванов Иван", 650.5, "Бухгалтерия", false));
        employees.add(new Employee(3, "Смирнова Екатерина", 720.8, "Маркетинг", false));
        employees.add(new Employee(4, "Кузнецов Артем", 800.2, "Маркетинг", false));
        employees.add(new Employee(5, "Петров Александр", 900.4, "Продажи", true));
        employees.add(new Employee(6, "Морозова Алина", 480.3, "Продажи", false));
        employees.add(new Employee(7, "Соколова Наталья", 600.9, "HR", false));
        employees.add(new Employee(8, "Григорьев Роман", 850.0, "HR", true));
        employees.add(new Employee(9, "Дмитриев Андрей", 1000.0, "IT", true));
        employees.add(new Employee(10, "Васильева Ирина", 730.6, "IT", false));
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public GetEmployeeDTO create(CreateEmployeeDTO createEmployeeDTO) {
        int maxId = employees.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0);

        Employee newEmployee = EmployeeMapper.fromCreateEmployeeDTO(createEmployeeDTO);
        newEmployee.setId(maxId + 1);
        employees.add(newEmployee);
        return EmployeeMapper.toGetEmployeeDTO(newEmployee);
    }

    @Override
    public GetEmployeeDTO promoteToManager(Integer id) {
        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (employee != null) {
            employee.setManager(true);
            return EmployeeMapper.toGetEmployeeDTO(employee);
        }
        return null;
    }

    @Override
    public GetEmployeeDTO update(Integer id, CreateEmployeeDTO createEmployeeDTO) {
        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (employee != null) {
            Employee updatedEmployee = EmployeeMapper.fromCreateEmployeeDTO(createEmployeeDTO);
            updatedEmployee.setId(id);
            employees.set(employees.indexOf(employee), updatedEmployee);
            return EmployeeMapper.toGetEmployeeDTO(updatedEmployee);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        employees.removeIf(e -> e.getId().equals(id));
    }

}

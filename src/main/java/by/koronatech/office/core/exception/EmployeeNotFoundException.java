package by.koronatech.office.core.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Integer employeeId) {
        super("Employee with ID " + employeeId + " not found");
    }

}

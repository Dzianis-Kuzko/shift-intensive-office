package by.koronatech.office.core.exception;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(Integer departmentId) {
        super("Department with ID " + departmentId + " not found");
    }

    public DepartmentNotFoundException(String departmentName) {
        super("Department with name " + departmentName + " not found");
    }

}

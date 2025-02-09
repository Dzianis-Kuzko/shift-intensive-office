package by.koronatech.office.core.mapper;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.core.entity.Department;

public class DepartmentMapper {

    public static DepartmentDTO toGetDepartmentDTO(Department department) {
        if (department == null) {
            return null;
        }
        return new DepartmentDTO(department.getId(), department.getName());
    }

}

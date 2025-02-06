package by.koronatech.office.core.mapper;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.core.entity.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentMapper {

    public static DepartmentDTO toGetDepartmentDTO(Department department) {
        if (department == null) {
            return null;
        }
        return new DepartmentDTO(department.getId(), department.getName());
    }

    public static List<DepartmentDTO> toGetDepartmentDTOList(List<Department> departments) {
        if (departments == null) {
            return new ArrayList<>();
        }
        return departments.stream()
                .map(DepartmentMapper::toGetDepartmentDTO)
                .toList();
    }
}

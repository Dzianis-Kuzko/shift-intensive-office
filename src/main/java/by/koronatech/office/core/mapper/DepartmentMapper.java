package by.koronatech.office.core.mapper;

import by.koronatech.office.api.dto.GetDepartmentDTO;
import by.koronatech.office.core.entity.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {
    public static GetDepartmentDTO toGetDepartmentDTO(Department department) {
        if (department == null) {
            return null;
        }
        return new GetDepartmentDTO(department.getId(), department.getName());
    }

    public static List<GetDepartmentDTO> toGetDepartmentDTOList(List<Department> departments) {
        if (departments == null) {
            return new ArrayList<>();
        }
        return departments.stream()
                .map(DepartmentMapper::toGetDepartmentDTO)
                .collect(Collectors.toList());
    }
}

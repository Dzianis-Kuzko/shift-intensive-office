package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.core.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDTO> getAll() {
        return departmentService.get();
    }


    @GetMapping("/{id}/employees")
    public List<EmployeeDTO> getEmployeesByDepartment(
            @PathVariable Integer id) {
        return departmentService.getEmployeesByDepartment(id);
    }
}

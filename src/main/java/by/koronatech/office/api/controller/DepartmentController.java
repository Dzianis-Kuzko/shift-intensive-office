package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.DepartmentDTO;
import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.core.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public Page<DepartmentDTO> getAll(Pageable pageable) {
        return departmentService.getAll(pageable);
    }


    @GetMapping("/{id}/employees")
    public Page<EmployeeDTO> getEmployeesByDepartment(
            @PathVariable Integer id,
            Pageable pageable) {
        return departmentService.getEmployeesByDepartment(id, pageable);
    }
}

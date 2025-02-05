package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.GetDepartmentDTO;
import by.koronatech.office.api.dto.GetEmployeeDTO;
import by.koronatech.office.core.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<GetDepartmentDTO> get() {
        return departmentService.get();
    }


    @GetMapping("/{id}/employees")
    public List<GetEmployeeDTO> getEmployeesByDepartment(
            @PathVariable Integer id) {
        return departmentService.getEmployeesByDepartment(id);
    }
}

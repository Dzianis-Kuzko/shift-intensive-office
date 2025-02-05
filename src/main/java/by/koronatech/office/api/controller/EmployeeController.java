package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.CreateEmployeeDTO;
import by.koronatech.office.api.dto.GetEmployeeDTO;
import by.koronatech.office.core.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public GetEmployeeDTO create(@RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return employeeService.create(createEmployeeDTO);
    }

    @PatchMapping("/{id}/make-manager")
    public GetEmployeeDTO promoteToManager(@PathVariable Integer id) {
        return employeeService.promoteToManager(id);
    }


    @PutMapping("/{id}")
    public GetEmployeeDTO update(
            @PathVariable Integer id,
            @RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return employeeService.update(id, createEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        employeeService.delete(id);
    }

}

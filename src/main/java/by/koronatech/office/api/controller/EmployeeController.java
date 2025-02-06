package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.SaveEmployeeDTO;
import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.core.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public EmployeeDTO create(@RequestBody SaveEmployeeDTO saveEmployeeDTO) {
        return employeeService.create(saveEmployeeDTO);
    }

    @PatchMapping("/{id}/manager")
    public EmployeeDTO promoteToManager(@PathVariable Integer id) {
        return employeeService.promoteToManager(id);
    }


    @PutMapping("/{id}")
    public EmployeeDTO update(
            @PathVariable Integer id,
            @RequestBody SaveEmployeeDTO saveEmployeeDTO) {
        return employeeService.update(id, saveEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

package by.koronatech.office.api.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class GetEmployeeDTO {
    private Integer id;
    private String name;
    private Double salary;
    private String Department;
    private Boolean manager;
}

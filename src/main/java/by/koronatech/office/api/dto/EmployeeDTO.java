package by.koronatech.office.api.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class EmployeeDTO {

    private Integer id;

    private String name;

    private BigDecimal salary;

    private String department;

    private boolean manager;
}

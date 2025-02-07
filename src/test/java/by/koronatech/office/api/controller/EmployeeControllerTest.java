package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.EmployeeDTO;
import by.koronatech.office.api.dto.SaveEmployeeDTO;
import by.koronatech.office.core.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    private SaveEmployeeDTO saveEmployeeDTO;

    private EmployeeDTO employeeDTO;

    @BeforeEach
    public void setUp() {
        BigDecimal bigDecimal = new BigDecimal("50.00");
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);

        saveEmployeeDTO = new SaveEmployeeDTO(
                "Den",
                bigDecimal,
                "Developer",
                false);

        employeeDTO = new EmployeeDTO(
                1,
                "Den",
                bigDecimal,
                "Developer",
                false);
    }

    @Test
    public void testCreateWithSuccess() throws Exception {
        when(employeeService.create(saveEmployeeDTO)).thenReturn(employeeDTO);

        String jsonRequest = "{\"name\":\"Den\",\"salary\":50.00,\"manager\":false,\"department\":\"Developer\"}";
        String expectedJson = "{\"id\":1,\"name\":\"Den\",\"salary\":50.00,\"manager\":false,\"department\":\"Developer\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.POST, "/employees")
                .contentType("application/json")
                .content(jsonRequest);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expectedJson));
    }


    @Test
    public void testPromoteToManagerWithSuccess() throws Exception {
        int id = employeeDTO.getId();

        employeeDTO.setManager(true);

        when(employeeService.promoteToManager(eq(id))).thenReturn(employeeDTO);

        String expectedJson = "{\"id\":1,\"name\":\"Den\",\"salary\":50.00,\"manager\":true,\"department\":\"Developer\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.PATCH, "/employees/{id}/manager", id)
                .contentType("application/json");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testUpdateWithSuccess() throws Exception {
        int id = employeeDTO.getId();

        when(employeeService.update(eq(id), eq(saveEmployeeDTO))).thenReturn(employeeDTO);

        String jsonRequest = "{\"name\":\"Den\",\"salary\":50.00,\"manager\":false,\"department\":\"Developer\"}";
        String expectedJson = "{\"id\":1,\"name\":\"Den\",\"salary\":50.00,\"manager\":false,\"department\":\"Developer\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.PUT, "/employees/{id}", id)
                .contentType("application/json")
                .content(jsonRequest);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testDeleteWithSuccess() throws Exception {
        int id = employeeDTO.getId();

        doNothing().when(employeeService).delete(eq(id));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.DELETE, "/employees/{id}", id)
                .contentType("application/json");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }
}
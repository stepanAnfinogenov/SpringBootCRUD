package com.anf2.springbootcrud.rest;

import com.anf2.springbootcrud.rest.controller.ControllerRest;
import com.anf2.springbootcrud.rest.entity.Employee;
import com.anf2.springbootcrud.rest.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ControllerRestTest {
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    ControllerRest controllerRest;

    Employee RECORD_1 = new Employee(1L, "name1", "surname1", "department1", 1111);
    Employee RECORD_2 = new Employee(2L, "name2", "surname2", "department2", 2222);
    Employee RECORD_3 = new Employee(3L, "name3", "surname3", "department3", 3333);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerRest).build();
    }

    @Test
    public void shouldReturnAllEmployees() throws Exception {
        List<Employee> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        Mockito.when(employeeService.getAllEmployees()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("name1")))
                .andExpect(jsonPath("$[1].name", is("name2")))
                .andExpect(jsonPath("$[2].name", is("name3")));
    }

    @Test
    public void shouldReturnExistingEmployeeById() throws Exception {
        Mockito.when(employeeService.getEmployeeById(RECORD_1.getEmployeeId())).thenReturn(RECORD_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("name1")));
    }

    @Test
    public void shouldReturnExistingEmployeeByName() throws Exception {
        List<Employee> records = new ArrayList<>(Arrays.asList(RECORD_1));

        Mockito.when(employeeService.getEmployeeByName(RECORD_1.getName())).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/employees/name?name=" + RECORD_1.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0].name", is("name1")));
    }

    @Test
    public void shouldAddNewEmployee() throws Exception {
        Employee employee1 = Employee.builder()
                .employeeId(1L)
                .name("name1")
                .surname("surname1")
                .department("department1")
                .salary(1111)
                .build();

        Mockito.when(employeeService.saveEmployee(employee1)).thenReturn(employee1);

        String content = objectWriter.writeValueAsString(employee1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("name1")));
    }

    @Test
    public void shouldUpdateExistingEmployee() throws Exception {
        Employee updatedEmployee1 = Employee.builder()
                .employeeId(1L)
                .name("updatedName1")
                .surname("surname1")
                .department("department1")
                .salary(1111)
                .build();

        Mockito.when(employeeService.saveEmployee(updatedEmployee1)).thenReturn(updatedEmployee1);

        String updatedContent = objectWriter.writeValueAsString(updatedEmployee1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedContent);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("updatedName1")));
    }

    @Test
    public void shouldDeleteExistingEmployeeById() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.delete("/api/employees/1"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteExistingEmployeeByName() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.delete("/api/employees?name=name1"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

package com.anfinogenov.springbootcrud;

import com.anfinogenov.springbootcrud.Repository.EmployeeRepository;
import com.anfinogenov.springbootcrud.controller.MyRestController;
import com.anfinogenov.springbootcrud.entity.Employee;
import com.anfinogenov.springbootcrud.service.EmployeeService;
import com.anfinogenov.springbootcrud.service.EmployeeServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.servlet.MockMvcBuilder;
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
public class MyRestControllerTest {
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    MyRestController myRestController;

    Employee RECORD_1 = new Employee(1, "name1", "surname1", "department1", 1111);
    Employee RECORD_2 = new Employee(2, "name2", "surname2", "department2", 2222);
    Employee RECORD_3 = new Employee(3, "name3", "surname3", "department3", 3333);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(myRestController).build();
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
    public void shouldReturnEmployeeById() throws Exception {
        Mockito.when(employeeService.getEmployee(RECORD_1.getId())).thenReturn(RECORD_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("name1")));
    }

    @Test
    public void shouldReturnEmployeeByName() throws Exception {
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
                .id(1)
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
    public void shouldUpdateEmployee() throws Exception {
        Employee updatedEmployee1 = Employee.builder()
                .id(1)
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
}

package com.anf2.springbootcrud.soap.endpoint;

import com.anf2.springbootcrud.soap.entity.EmployeeInfo;
import com.anf2.springbootcrud.soap.service.impl.EmployeeServiceImpl;
import com.anf2.springbootcrud.soap.GetEmployeeByIdRequest;
import com.anf2.springbootcrud.soap.GetEmployeeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://soap.springbootcrud.anf2.com";

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeByIdRequest request) {
        GetEmployeeResponse response = new GetEmployeeResponse();
        EmployeeInfo employeeInfo = new EmployeeInfo();
        BeanUtils.copyProperties(employeeServiceImpl.getEmployeeById(request.getEmployeeId()), employeeInfo);
        response.setEmployeeInfo(employeeInfo);
        return response;
    }
}
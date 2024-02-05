package com.webapi.Service;

import com.webapi.Exception.EmployeeNotFoundException;
import com.webapi.Model.Employee;
import com.webapi.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        if (employee.getId() == null) {
            if (isEmailAlreadyExists(employee.getEmail()))
                throw new IllegalArgumentException("Ez az email cím már regisztrálva van!");
            this.employeeRepository.save(employee);
        } else {
            Employee existingEmployee = this.employeeRepository.findById(employee.getId()).orElseThrow(
                    () -> new EmployeeNotFoundException("Dolgozó nem található!")
            );

            if (!employee.getEmail().equals(existingEmployee.getEmail())) {
                if (isEmailAlreadyExists(employee.getEmail())) {
                    throw new IllegalArgumentException("Ez az email cím már regisztrálva van!");
                }
            }
            this.employeeRepository.save(employee);
        }

        return employee;
    }

    private boolean isEmailAlreadyExists(String email) {
        return employeeRepository.findByEmail(email).isPresent();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> {
            String errorMessage = "Az ID=" + id + " dolgozó nem található.";
            return new EmployeeNotFoundException(errorMessage);
        });
    }

    @Override
    public void deleteEmployeeById(Long id) {
        this.employeeRepository.deleteById(id);
    }
}
package demoCompany.controller;

import demoCompany.entity.Department;
import demoCompany.entity.Project;
import demoCompany.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company/department")
public class DepartmentController {

    @Autowired
    public DepartmentService departmentService;

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable("id") long id) {
        return departmentService.getById(id);
    }

    @GetMapping("/{id}/projects")
    public List<Project> getProjects(@PathVariable("id") long id) {
        return departmentService.getAllProjects(id);
    }

    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getAll();
    }

    @PostMapping
    public Department create(@Valid @RequestBody Department department) {
        return departmentService.create(department);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable("id") long id, @Valid @RequestBody Department department) {
        return departmentService.update(id, department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable("id") long id) {
        departmentService.delete(id);
    }
}

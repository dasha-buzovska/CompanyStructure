package demoCompany.service;

import demoCompany.ResourceNotFoundException;
import demoCompany.entity.Department;
import demoCompany.entity.Project;
import demoCompany.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepo;
    @Autowired
    private ProjectService projectService;

    public Department create(Department department) {
        return departmentRepo.save(department);
    }

    public Department getById(long id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
    }

    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

    public List<Project> getAllProjects(Long id) {
        Department department = getById(id);
        return projectService.getAllByDepartment(department);
    }

    public Department update(Long id, Department newDepartment) {
        Department oldDepartment = getById(id);
        oldDepartment.setName(newDepartment.getName());
        return departmentRepo.saveAndFlush(oldDepartment);
    }

    public void delete(Long id) {
        if (!getAllProjects(id).isEmpty()) {
            throw new RuntimeException("Can't delete department with projects. Please delete its projects first");
        }
        departmentRepo.deleteById(id);
    }

}

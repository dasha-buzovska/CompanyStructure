package demoCompany.service;

import demoCompany.ResourceNotFoundException;
import demoCompany.entity.Department;
import demoCompany.entity.Position;
import demoCompany.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepo;
    @Autowired
    private PositionService positionService;

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

    public List<Position> getAllPositions(Long id) {
        Department department = getById(id);
        return positionService.getAllByDepartment(department);
    }

    public Department update(Long id, Department newDepartment) {
        Department oldDepartment = getById(id);
        oldDepartment.setName(newDepartment.getName());
        return departmentRepo.saveAndFlush(oldDepartment);
    }

    public void delete(Long id) {
        if (!getAllPositions(id).isEmpty()) {
            throw new RuntimeException("Can't delete department with projects. Please delete its positions firstly!");
        }
        departmentRepo.deleteById(id);
    }

}

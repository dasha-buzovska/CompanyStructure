package demoCompany.service;

import demoCompany.ResourceNotFoundException;
import demoCompany.entity.Department;
import demoCompany.entity.Position;
import demoCompany.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private DepartmentService departmentService;

    public Position create(Position position) {
        Department department = departmentService.getById(position.getDepartment().getId());
        position.setDepartment(department);
        return positionRepository.save(position);
    }

    public Position getById(Long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position", "id", id));
    }

    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    public List<Position> getAllByDepartment(Department department) {
        return positionRepository.findAllByDepartment(department);
    }

    public Position update(Long id, Position position) {
        Position oldPosition = getById(id);
        oldPosition.setLevel(position.getLevel());
        oldPosition.setOrganizationalPosition(position.getOrganizationalPosition());
        oldPosition.setProgrammingLanguage(position.getProgrammingLanguage());
        Department department = departmentService.getById(position.getDepartment().getId());
        oldPosition.setDepartment(department);
        return positionRepository.saveAndFlush(oldPosition);
    }

    public void delete(Long id) {
        positionRepository.deleteById(id);
    }

}

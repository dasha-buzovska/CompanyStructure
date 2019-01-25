package demoCompany.service;

import demoCompany.ResourceNotFoundException;
import demoCompany.entity.Department;
import demoCompany.entity.Person;
import demoCompany.entity.Project;
import demoCompany.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PersonService personService;

    public Project getById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
        Department department = departmentService.getById(project.getDepartment().getId());
        project.setDepartment(department);
        return project;
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public List<Project> getAllByDepartment(Department department) {
        return projectRepository.findAllByDepartment(department);
    }

    public List<Person> getProjectEmployees(Long id) {
        Project project = getById(id);
        return personService.getAllByProject(project);
    }

    public Project create(Project project) {
        project = projectRepository.save(project);
        Department department = departmentService.getById(project.getDepartment().getId());
        project.setDepartment(department);
        return project;
    }

    public Project update(Long id, Project newProject) {
        Project oldProject = getById(id);
        oldProject.setName(newProject.getName());
        oldProject.setDepartment(newProject.getDepartment());
        oldProject.setStart(newProject.getStart());
        oldProject.setEnd(newProject.getEnd());
        return projectRepository.saveAndFlush(oldProject);
    }

    public void delete(Long id) {
        if (!getProjectEmployees(id).isEmpty()) {
            throw new RuntimeException("Can't delete project with employees. You need to fire them firstly!");
        }
        projectRepository.deleteById(id);
    }

}

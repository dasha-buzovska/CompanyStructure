package demoCompany.controller;

import demoCompany.entity.Person;
import demoCompany.entity.Project;
import demoCompany.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{id}")
    public Project getProject(@PathVariable(value = "id") Long id) {
        return projectService.getById(id);
    }

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getAll();
    }

    @GetMapping("/{id}/workers")
    public List<Person> getWorkers(@PathVariable(value = "id") Long id) {
        return projectService.getProjectEmployees(id);
    }

    @PostMapping
    public Project createProjects(@Valid @RequestBody Project project) {
        return projectService.create(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable(value = "id") Long id, @Valid @RequestBody Project project) {
        return projectService.update(id, project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable(value = "id") Long id) {
        projectService.delete(id);
    }
}

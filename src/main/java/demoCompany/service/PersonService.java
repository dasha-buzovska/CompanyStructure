package demoCompany.service;


import demoCompany.ResourceNotFoundException;
import demoCompany.entity.Person;
import demoCompany.entity.Position;
import demoCompany.entity.Project;
import demoCompany.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PositionService positionService;
    @Autowired
    private ProjectService projectService;

    public Person create(Person person) {
        Position position = positionService.getById(person.getPosition().getId());
        person.setPosition(position);
        Project project = projectService.getById(person.getProject().getId());
        person.setProject(project);
        return personRepository.save(person);
    }

    public Person getById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person", "id", id));
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public List<Person> getAllByProject(Project project) {
        return personRepository.findAllByProject(project);
    }

    public Person update(Long id, Person person) {
        Person oldPerson = getById(id);
        oldPerson.setFirstName(person.getFirstName());
        oldPerson.setLastName(person.getLastName());
        oldPerson.setAge(person.getAge());
        oldPerson.setPosition(person.getPosition());
        oldPerson.setProject(person.getProject());
        return personRepository.saveAndFlush(oldPerson);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

}

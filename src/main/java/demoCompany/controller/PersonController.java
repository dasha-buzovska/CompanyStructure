package demoCompany.controller;

import demoCompany.entity.Person;
import demoCompany.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable(value = "id") Long id) {
        return personService.getById(id);
    }

    @GetMapping
    public List<Person> getAllWorkers() {
        return personService.getAll();
    }

    @PostMapping
    public Person createPerson(@Valid @RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable(value = "id") Long id,
                                   @Valid @RequestBody Person person) {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable(value = "id") Long id) {
        personService.delete(id);
    }
}

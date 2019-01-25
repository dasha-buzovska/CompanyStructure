package demoCompany.repository;

import demoCompany.entity.Person;
import demoCompany.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByProject(Project project);

}

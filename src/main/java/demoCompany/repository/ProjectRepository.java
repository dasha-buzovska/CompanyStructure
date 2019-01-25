package demoCompany.repository;

import demoCompany.entity.Department;
import demoCompany.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByDepartment(Department department);

}

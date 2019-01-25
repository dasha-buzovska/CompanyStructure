package demoCompany.repository;

import demoCompany.entity.Department;
import demoCompany.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long>{

    List<Position> findAllByDepartment(Department department);
}

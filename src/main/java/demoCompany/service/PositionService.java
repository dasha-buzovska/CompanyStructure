package demoCompany.service;

import demoCompany.ResourceNotFoundException;
import demoCompany.entity.Position;
import demoCompany.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    PositionRepository positionRepository;

    public Position create(Position position) {
        return positionRepository.save(position);
    }

    public Position getById(Long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position", "id", id));
    }

    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    public Position update(Long id, Position position) {
        Position oldPosition = getById(id);
        oldPosition.setLevel(position.getLevel());
        oldPosition.setOrganizationalPosition(position.getOrganizationalPosition());
        oldPosition.setProgrammingLanguage(position.getProgrammingLanguage());
        return positionRepository.saveAndFlush(oldPosition);
    }

    public void delete(Long id) {
        positionRepository.deleteById(id);
    }

}

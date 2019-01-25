package demoCompany.controller;

import demoCompany.entity.Position;
import demoCompany.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/{id}")
    public Position getPosition(@PathVariable(value = "id") Long id) {
        return positionService.getById(id);
    }

    @GetMapping
    public List<Position> getAllPositions() {
        return positionService.getAll();
    }

    @PostMapping
    public Position createPosition(@Valid @RequestBody Position position) {
        return positionService.create(position);
    }

    @PutMapping("/{id}")
    public Position updatePosition(@PathVariable(value = "id") Long id,
                                   @Valid @RequestBody Position position) {
        return positionService.update(id, position);
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable(value = "id") Long id) {
        positionService.delete(id);
    }

}

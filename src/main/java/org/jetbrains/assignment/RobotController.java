package org.jetbrains.assignment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/robot")
public class RobotController {
    private final RobotService robotService;

    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }


    @PostMapping("/locations")
    public List<Location> moveRobot(@RequestBody List<MovementOperation> operations) {
        return robotService.moveRobot(operations);
    }

    @PostMapping("/moves")
    public List<MovementOperation> calculateMoves(@RequestBody List<Location> locations) {
        return robotService.calculateMoves(locations);
    }
}

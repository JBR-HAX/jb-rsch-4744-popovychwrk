package org.jetbrains.assignment;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RobotService {

    public List<Location> moveRobot(List<MovementOperation> operations) {
        List<Location> locations = new ArrayList<>();
        int x = 0;
        int y = 0;
        locations.add(new Location(x, y));

        for (MovementOperation operation : operations) {
            switch (operation.getDirection().toUpperCase()) {
                case "NORTH":
                    y += operation.getSteps();
                    break;
                case "SOUTH":
                    y -= operation.getSteps();
                    break;
                case "EAST":
                    x += operation.getSteps();
                    break;
                case "WEST":
                    x -= operation.getSteps();
                    break;
            }
            locations.add(new Location(x, y));
        }
        return locations;
    }

    public List<MovementOperation>calculateMoves(List<Location> locations) {
        List<MovementOperation> operations = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (int i = 1; i < locations.size(); i++) {
            int xDiff = locations.get(i).getX() - x;
            int yDiff = locations.get(i).getY() - y;
            if (xDiff > 0) {
                operations.add(new MovementOperation("EAST", xDiff));
            } else if (xDiff < 0) {
                operations.add(new MovementOperation("WEST", -xDiff));
            }
            if (yDiff > 0) {
                operations.add(new MovementOperation("NORTH", yDiff));
            } else if (yDiff < 0) {
                operations.add(new MovementOperation("SOUTH", -yDiff));
            }
            x = locations.get(i).getX();
            y = locations.get(i).getY();
        }
        return operations;
    }
}

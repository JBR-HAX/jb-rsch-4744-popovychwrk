package org.jetbrains.assignment;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class RobotControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RobotService robotService;

    @Test
    public void testMoveRobot() throws Exception {
        List<MovementOperation> operations = new ArrayList<>();
        operations.add(new MovementOperation("NORTH", 5));
        operations.add(new MovementOperation("EAST", 3));

        List<Location> locations = new ArrayList<>();
        locations.add(new Location(0, 0));
        locations.add(new Location(0, 5));
        locations.add(new Location(3, 5));

        when(robotService.moveRobot(operations)).thenReturn(locations);

        this.mockMvc.perform(post("/robot/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"direction\":\"NORTH\",\"steps\":5},{\"direction\":\"EAST\",\"steps\":3}]"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"x\":0,\"y\":0},{\"x\":0,\"y\":5},{\"x\":3,\"y\":5}]"));

        verify(robotService, times(1)).moveRobot(operations);
    }

    @Test
    public void testCalculateMoves() throws Exception {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(0, 0));
        locations.add(new Location(0, 5));
        locations.add(new Location(3, 5));

        List<MovementOperation> operations = new ArrayList<>();
        operations.add(new MovementOperation("NORTH", 5));
        operations.add(new MovementOperation("EAST", 3));

        when(robotService.calculateMoves(locations)).thenReturn(operations);

        this.mockMvc.perform(post("/robot/moves")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"x\":0,\"y\":0},{\"x\":0,\"y\":5},{\"x\":3,\"y\":5}]"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"direction\":\"NORTH\",\"steps\":5},{\"direction\":\"EAST\",\"steps\":3}]"));

        verify(robotService, times(1)).calculateMoves(locations);
    }

}
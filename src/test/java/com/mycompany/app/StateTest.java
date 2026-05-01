package com.mycompany.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StateTest {
    
    @Test
    void testStateValues() {
        assertEquals(State.PLAYING, State.valueOf("PLAYING"));
        assertEquals(State.OWIN, State.valueOf("OWIN"));
        assertEquals(State.XWIN, State.valueOf("XWIN"));
        assertEquals(State.DRAW, State.valueOf("DRAW"));
    }
}
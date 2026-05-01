package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeCellTest {
    private TicTacToeCell cell;
    
    @BeforeEach
    void setUp() {
        cell = new TicTacToeCell(4, 1, 1);
    }
    
    @Test
    void testSetMarker() {
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
    }
    
    @Test
    void testGetRow() {
        assertEquals(1, cell.getRow());
    }
    
    @Test
    void testGetCol() {
        assertEquals(1, cell.getCol());
    }
    
    @Test
    void testGetNum() {
        assertEquals(4, cell.getNum());
    }
    
    @Test
    void testInitialMarkerIsSpace() {
        assertEquals(' ', cell.getMarker());
    }
}
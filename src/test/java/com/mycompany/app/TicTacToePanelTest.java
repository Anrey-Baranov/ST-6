package com.mycompany.app;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

public class TicTacToePanelTest {
    
    @Test
    void testPanelCreation() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        assertNotNull(panel);
        assertEquals(9, panel.getComponentCount());
    }
    
    @Test
    void testPanelLayout() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        assertTrue(panel.getLayout() instanceof GridLayout);
        GridLayout layout = (GridLayout) panel.getLayout();
        assertEquals(3, layout.getRows());
        assertEquals(3, layout.getColumns());
    }
}
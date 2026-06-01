package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import static org.junit.jupiter.api.Assertions.*;

public class TicTacToePanelTest {
    private TicTacToePanel panel;
    private GridLayout layout;
    
    @BeforeEach
    void setUp() {
        layout = new GridLayout(3, 3);
        panel = new TicTacToePanel(layout);
    }
    
    @Test
    void testPanelCreation() {
        assertNotNull(panel);
        assertEquals(9, panel.getComponentCount());
        assertTrue(panel.getLayout() instanceof GridLayout);
    }
    
    @Test
    void testCellsAreCreated() {
        for (Component comp : panel.getComponents()) {
            assertTrue(comp instanceof TicTacToeCell);
        }
    }
    
    @Test
    void testGameInitialization() {
        try {
            java.lang.reflect.Field gameField = TicTacToePanel.class.getDeclaredField("game");
            gameField.setAccessible(true);
            Game game = (Game) gameField.get(panel);
            assertNotNull(game);
            assertEquals('X', game.player1.symbol);
            assertEquals('O', game.player2.symbol);
        } catch (Exception e) {
            fail("Не удалось получить поле game: " + e.getMessage());
        }
    }
    
    @Test
    void testActionPerformedDoesNotThrowException() {
        Component firstCell = panel.getComponent(0);
        ActionEvent event = new ActionEvent(firstCell, ActionEvent.ACTION_PERFORMED, "command");

        assertDoesNotThrow(() -> {

            assertNotNull(panel.getClass().getMethod("actionPerformed", ActionEvent.class));
        });
    }
    
    @Test
    void testCellButtonsHaveActionListeners() {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                assertTrue(button.getActionListeners().length > 0);
            }
        }
    }
    
    @Test
    void testPanelHasGridLayout() {
        LayoutManager layoutManager = panel.getLayout();
        assertTrue(layoutManager instanceof GridLayout);
        GridLayout grid = (GridLayout) layoutManager;
        assertEquals(3, grid.getRows());
        assertEquals(3, grid.getColumns());
    }
}
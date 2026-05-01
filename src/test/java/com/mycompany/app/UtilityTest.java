package com.mycompany.app;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class UtilityTest {
    
    @Test
    void testPrintCharArray() {
        char[] board = {'X','O','X',' ',' ',' ',' ',' ',' '};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Utility.print(board);
        assertTrue(outContent.toString().contains("X-O-X"));
    }
    
    @Test
    void testPrintIntArray() {
        int[] board = {1,2,3,0,0,0,0,0,0};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Utility.print(board);
        assertTrue(outContent.toString().contains("1-2-3"));
    }
    
    @Test
    void testPrintArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(0);
        moves.add(3);
        moves.add(5);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Utility.print(moves);
        assertTrue(outContent.toString().contains("0-3-5"));
    }
}
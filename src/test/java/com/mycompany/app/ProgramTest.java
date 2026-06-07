package com.mycompany.app;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProgramTest {
    private Game game;
    
    @Before
    public void setUp() {
        game = new Game();
    }
    
    // ========== Тесты для Game ==========
    @Test
    public void testGameInitialization() {
        assertNotNull(game.player1);
        assertNotNull(game.player2);
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
        assertEquals(State.PLAYING, game.state);
    }
    
    @Test
    public void testCheckStateXWin() {
        game.board = new char[]{'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    public void testCheckStateOWin() {
        game.board = new char[]{'O','O','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'O';
        State state = game.checkState(game.board);
        assertEquals(State.OWIN, state);
    }
    
    @Test
    public void testCheckStateDraw() {
        game.board = new char[]{'X','O','X','O','X','O','O','X','O'};
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.DRAW, state);
    }
    
    @Test
    public void testCheckStatePlaying() {
        game.board = new char[]{'X',' ',' ',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.PLAYING, state);
    }
    
    @Test
    public void testGenerateMoves() {
        game.board = new char[]{' ','X','O',' ',' ',' ',' ',' ',' '};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(7, moves.size());
        assertTrue(moves.contains(0));
        assertTrue(moves.contains(3));
    }
    
    @Test
    public void testGenerateMovesNoMovesLeft() {
        game.board = new char[]{'X','O','X','O','X','O','O','X','O'};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(0, moves.size());
    }
    
    @Test
    public void testGenerateMovesWithSomeEmptyCells() {
        game.board = new char[]{'X',' ','O',' ','X',' ','O',' ','X'};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(4, moves.size());
        assertTrue(moves.contains(1));
        assertTrue(moves.contains(3));
        assertTrue(moves.contains(5));
        assertTrue(moves.contains(7));
    }
    
    @Test
    public void testEvaluatePositionXWin() {
        game.board = new char[]{'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(Game.INF, value);
    }
    
    @Test
    public void testEvaluatePositionOWin() {
        game.board = new char[]{'O','O','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'O';
        int value = game.evaluatePosition(game.board, game.player2);
        assertEquals(Game.INF, value);
    }
    
    @Test
    public void testEvaluatePositionNotFinished() {
        game.board = new char[]{'X',' ',' ',' ',' ',' ',' ',' ',' '};
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(-1, value);
    }
    
    @Test
    public void testEvaluatePositionLoseForX() {
        game.board = new char[]{'O','O','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'O';
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(-Game.INF, value);
    }
    
    @Test
    public void testEvaluatePositionDrawForFullBoard() {
        game.board = new char[]{'X','O','X','O','X','O','O','X','O'};
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(0, value);
    }
    
    @Test
    public void testEvaluatePositionForLosingPlayer() {
        game.board = new char[]{'X','X','X','O','O',' ',' ',' ',' '};
        game.symbol = 'X';
        int value = game.evaluatePosition(game.board, game.player2);
        assertEquals(-Game.INF, value);
    }
    
    @Test
    public void testEvaluatePositionForUnfinishedGame() {
        game.board = new char[]{'X',' ',' ',' ',' ',' ',' ',' ',' '};
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(-1, value);
    }
    
    @Test
    public void testMiniMaxReturnsValidMoveForEmptyBoard() {
        game.board = new char[]{' ',' ',' ',' ',' ',' ',' ',' ',' '};
        game.cplayer = game.player1;
        int move = game.MiniMax(game.board, game.player1);
        assertTrue(move >= 1 && move <= 9);
    }
    
    @Test
    public void testMiniMaxWithAlmostFullBoard() {
        game.board = new char[]{'X','O','X','O','X','O','O','X',' '};
        game.cplayer = game.player2;
        int move = game.MiniMax(game.board, game.player2);
        assertEquals(9, move);
    }
    
    @Test
    public void testMiniMaxWithMultipleBestMoves() {
        game.board = new char[]{'X',' ',' ',' ','O',' ',' ',' ',' '};
        game.cplayer = game.player2;
        game.player2.symbol = 'O';
        game.player1.symbol = 'X';
        int move = game.MiniMax(game.board, game.player2);
        assertTrue(move >= 1 && move <= 9);
    }
    
    @Test
    public void testMinMove() {
        game.board = new char[]{'X',' ',' ',' ',' ',' ',' ',' ',' '};
        game.player1.symbol = 'X';
        int result = game.MinMove(game.board, game.player1);
        assertTrue(result >= -100 && result <= 100);
    }
    
    @Test
    public void testMaxMove() {
        game.board = new char[]{'X',' ',' ',' ',' ',' ',' ',' ',' '};
        game.player1.symbol = 'X';
        int result = game.MaxMove(game.board, game.player1);
        assertTrue(result >= -100 && result <= 100);
    }
    
    @Test
    public void testMaxMoveDirectCall() {
        game.board = new char[]{'X',' ',' ',' ',' ',' ',' ',' ',' '};
        game.player1.symbol = 'X';
        int result = game.MaxMove(game.board, game.player1);
        assertTrue(result >= -Game.INF && result <= Game.INF);
    }
    
    @Test
    public void testMinMoveDirectCall() {
        game.board = new char[]{'O',' ',' ',' ',' ',' ',' ',' ',' '};
        game.player1.symbol = 'X';
        int result = game.MinMove(game.board, game.player1);
        assertTrue(result >= -Game.INF && result <= Game.INF);
    }
    
    @Test
    public void testCheckStateDiagonalWinX() {
        game.board = new char[]{'X',' ',' ',' ','X',' ',' ',' ','X'};
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    public void testCheckStateDiagonalWinO() {
        game.board = new char[]{'O',' ',' ',' ','O',' ',' ',' ','O'};
        game.symbol = 'O';
        State state = game.checkState(game.board);
        assertEquals(State.OWIN, state);
    }
    
    @Test
    public void testCheckStateVerticalWinX() {
        game.board = new char[]{'X',' ',' ','X',' ',' ','X',' ',' '};
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    public void testCheckStateWithSymbolX() {
        game.board = new char[]{'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    public void testCheckStateWithSymbolO() {
        game.board = new char[]{'O','O','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'O';
        State state = game.checkState(game.board);
        assertEquals(State.OWIN, state);
    }
    
    // ========== Тесты для TicTacToeCell ==========
    @Test
    public void testTicTacToeCellCreation() {
        TicTacToeCell cell = new TicTacToeCell(4, 1, 1);
        assertEquals(4, cell.getNum());
        assertEquals(1, cell.getRow());
        assertEquals(1, cell.getCol());
        assertEquals(' ', cell.getMarker());
    }
    
    @Test
    public void testSetMarker() {
        TicTacToeCell cell = new TicTacToeCell(4, 1, 1);
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
    }
    
    @Test
    public void testGetRow() {
        TicTacToeCell cell = new TicTacToeCell(4, 1, 1);
        assertEquals(1, cell.getRow());
    }
    
    @Test
    public void testGetCol() {
        TicTacToeCell cell = new TicTacToeCell(4, 1, 1);
        assertEquals(1, cell.getCol());
    }
    
    @Test
    public void testGetNum() {
        TicTacToeCell cell = new TicTacToeCell(4, 1, 1);
        assertEquals(4, cell.getNum());
    }
    
    @Test
    public void testInitialMarkerIsSpace() {
        TicTacToeCell cell = new TicTacToeCell(4, 1, 1);
        assertEquals(' ', cell.getMarker());
    }
    
    // ========== Тесты для Utility ==========
    @Test
    public void testUtilityPrintCharArray() {
        char[] board = {'X','O','X',' ',' ',' ',' ',' ',' '};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Utility.print(board);
        assertTrue(outContent.toString().contains("X-O-X"));
    }
    
    @Test
    public void testUtilityPrintIntArray() {
        int[] board = {1,2,3,0,0,0,0,0,0};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Utility.print(board);
        assertTrue(outContent.toString().contains("1-2-3"));
    }
    
    @Test
    public void testUtilityPrintArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(0);
        moves.add(3);
        moves.add(5);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Utility.print(moves);
        assertTrue(outContent.toString().contains("0-3-5"));
    }
    
    @Test
    public void testPrintEmptyCharArray() {
        char[] emptyBoard = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Utility.print(emptyBoard);
        String output = outContent.toString();
        assertTrue(output.contains("-") || output.length() > 0);
    }
    
    @Test
    public void testPrintCharArrayWithValues() {
        char[] board = {'X','O','X','O','X','O','X','O','X'};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Utility.print(board);
        String output = outContent.toString();
        assertTrue(output.contains("X") && output.contains("O"));
    }
    
    @Test
    public void testPrintEmptyIntArray() {
        int[] emptyBoard = {0,0,0,0,0,0,0,0,0};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Utility.print(emptyBoard);
        assertTrue(outContent.toString().contains("0-0-0"));
    }
    
    @Test
    public void testPrintEmptyArrayList() {
        ArrayList<Integer> emptyMoves = new ArrayList<>();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Utility.print(emptyMoves);
        assertTrue(outContent.toString().contains("\n"));
    }
    
    // ========== Тесты для State ==========
    @Test
    public void testStateValues() {
        assertEquals(State.PLAYING, State.valueOf("PLAYING"));
        assertEquals(State.OWIN, State.valueOf("OWIN"));
        assertEquals(State.XWIN, State.valueOf("XWIN"));
        assertEquals(State.DRAW, State.valueOf("DRAW"));
    }
    
    // ========== Тесты для Program ==========
    @Test
    public void testProgramClassExists() {
        assertNotNull(Program.class);
    }
    
    @Test
    public void testMainMethodExists() {
        try {
            java.lang.reflect.Method mainMethod = Program.class.getMethod("main", String[].class);
            assertNotNull(mainMethod);
        } catch (NoSuchMethodException e) {
            fail("Метод main не найден");
        }
    }
}
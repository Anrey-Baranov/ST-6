package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game;
    
    @BeforeEach
    void setUp() {
        game = new Game();
    }
    
    @Test
    void testCheckStateXWin() {
        game.board = new char[]{'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    void testCheckStateOWin() {
        game.board = new char[]{'O','O','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'O';
        State state = game.checkState(game.board);
        assertEquals(State.OWIN, state);
    }
    
    @Test
    void testCheckStateDraw() {
        game.board = new char[]{'X','O','X','O','X','O','O','X','O'};
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.DRAW, state);
    }
    
    @Test
    void testCheckStatePlaying() {
        game.board = new char[]{'X',' ',' ',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.PLAYING, state);
    }
    
    @Test
    void testGenerateMoves() {
        game.board = new char[]{' ','X','O',' ',' ',' ',' ',' ',' '};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(7, moves.size());
        assertTrue(moves.contains(0));
        assertTrue(moves.contains(3));
    }
    
    @Test
    void testEvaluatePositionXWin() {
        game.board = new char[]{'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(Game.INF, value);
    }

    @Test
    void testEvaluatePositionOWin() {
        game.board = new char[]{'O','O','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'O';
        int value = game.evaluatePosition(game.board, game.player2);
        assertEquals(Game.INF, value);
    }
    
    @Test
    void testEvaluatePositionNotFinished() {
        game.board = new char[]{'X',' ',' ',' ',' ',' ',' ',' ',' '};
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(-1, value);
    }
    @Test
    void testMiniMaxReturnsValidMoveForEmptyBoard() {
        game.board = new char[]{' ',' ',' ',' ',' ',' ',' ',' ',' '};
        game.cplayer = game.player1;
        int move = game.MiniMax(game.board, game.player1);
        assertTrue(move >= 1 && move <= 9);
    }
    
    @Test
    void testMiniMaxWithAlmostFullBoard() {
        game.board = new char[]{'X','O','X','O','X','O','O','X',' '};
        game.cplayer = game.player2;
        int move = game.MiniMax(game.board, game.player2);
        assertEquals(9, move);
    }
    
    @Test
    void testMinMove() {
        game.board = new char[]{'X',' ',' ',' ',' ',' ',' ',' ',' '};
        game.player1.symbol = 'X';
        int result = game.MinMove(game.board, game.player1);
        assertTrue(result >= -100 && result <= 100);
    }
    
    @Test
    void testMaxMove() {
        game.board = new char[]{'X',' ',' ',' ',' ',' ',' ',' ',' '};
        game.player1.symbol = 'X';
        int result = game.MaxMove(game.board, game.player1);
        assertTrue(result >= -100 && result <= 100);
    }
    
    @Test
    void testCheckStateDiagonalWinX() {
        game.board = new char[]{'X',' ',' ',' ','X',' ',' ',' ','X'};
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    void testCheckStateDiagonalWinO() {
        game.board = new char[]{'O',' ',' ',' ','O',' ',' ',' ','O'};
        game.symbol = 'O';
        State state = game.checkState(game.board);
        assertEquals(State.OWIN, state);
    }
    
    @Test
    void testCheckStateVerticalWinX() {
        game.board = new char[]{'X',' ',' ','X',' ',' ','X',' ',' '};
        game.symbol = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }
    
    @Test
    void testEvaluatePositionXWinWithCorrectSymbol() {
        game.board = new char[]{'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(Game.INF, value);
    }
    
    @Test
    void testEvaluatePositionLoseForX() {
        game.board = new char[]{'O','O','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'O';
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(-Game.INF, value);
    }
    
    @Test
    void testEvaluatePositionDrawForFullBoard() {
        game.board = new char[]{'X','O','X','O','X','O','O','X','O'};
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(0, value);
    }
    
    @Test
    void testGenerateMovesNoMovesLeft() {
        game.board = new char[]{'X','O','X','O','X','O','O','X','O'};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(0, moves.size());
    }
    
    @Test
    void testGameInitialization() {
        Game newGame = new Game();
        assertNotNull(newGame.player1);
        assertNotNull(newGame.player2);
        assertEquals('X', newGame.player1.symbol);
        assertEquals('O', newGame.player2.symbol);
        assertEquals(State.PLAYING, newGame.state);
    }
}

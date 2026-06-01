package com.mycompany.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProgramTest {
    
    @Test
    void testProgramClassExists() {
        assertNotNull(Program.class);
    }
    
    @Test
    void testMainMethodExists() {
        try {
            java.lang.reflect.Method mainMethod = Program.class.getMethod("main", String[].class);
            assertNotNull(mainMethod);
        } catch (NoSuchMethodException e) {
            fail("Метод main не найден");
        }
    }
}
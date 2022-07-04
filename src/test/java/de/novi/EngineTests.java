package de.novi;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

public class EngineTests {



    @Test
    public void mainContextLoadsAndUnloads() {
        Main main = new Main();
        try {
            main.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void intentionallyFailingTest() {
        fail("TDD Dictates you must have a failing test before you write any new code");
    }
}
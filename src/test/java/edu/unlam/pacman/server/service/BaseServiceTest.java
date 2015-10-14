package edu.unlam.pacman.server.service;

import org.junit.Before;

import edu.unlam.pacman.Launcher;

/**
 * @author Cristian Miranda
 * @since 10/14/15 - 11:26
 */
public class BaseServiceTest {
    @Before
    public void setup() {
        Launcher.initProperties();
    }
}

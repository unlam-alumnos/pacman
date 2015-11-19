package edu.unlam.pacman.server.dao;

import org.junit.Before;

import edu.unlam.pacman.Launcher;

/**
 * @author Cristian Miranda
 * @since 10/14/15 - 11:26
 */
public class BaseDaoTest {
    @Before
    public void setup() {
        String[] args = {"client"};
        Launcher.initProperties(args);
    }
}

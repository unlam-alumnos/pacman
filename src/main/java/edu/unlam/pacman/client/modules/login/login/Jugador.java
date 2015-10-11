package edu.unlam.pacman.client.modules.login.login;

public class Jugador {
    private Long id;
    private String username;
    private String password;

    public Jugador() {
    }

    /**
     * Requerido por Dalesbred
     */
    public Jugador(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

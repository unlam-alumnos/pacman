package edu.unlam.pacman.client.modules.login.login;

public class Jugador {
    private Long id;
    private String username;
    private String password;

    public Jugador() {
    }

    public Jugador(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Jugador jugador = (Jugador) o;

        if (id != null ? !id.equals(jugador.id) : jugador.id != null) {
            return false;
        }
        if (username != null ? !username.equals(jugador.username) : jugador.username != null) {
            return false;
        }
        return !(password != null ? !password.equals(jugador.password) : jugador.password != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}

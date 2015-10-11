package edu.unlam.pacman.server.service;

import com.google.common.base.Preconditions;
import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.server.dao.RegistroDao;
import edu.unlam.pacman.shared.exception.ServiceException;

/**
 * @author Cristian Miranda
 * @since 10/11/15 - 17:47
 */
public class RegistroService {

    private static RegistroService instance;
    private RegistroDao dao;

    private RegistroService() {
        this.dao = RegistroDao.getInstance();
    }

    public static RegistroService getInstance() {
        if (instance == null) {
            instance = new RegistroService();
        }
        return instance;
    }

    public Long register(String username, String password, String passwordConfirmation) throws ServiceException {
        checkPreconditions(username, password, passwordConfirmation);
        if (dao.getByUsername(username.toLowerCase()) != null) {
            throw new ServiceException("Ya existe un jugador con ese nombre");
        }
        Jugador jugador = new Jugador(username, password);
        return dao.register(jugador);
    }

    private void checkPreconditions(String username, String password, String passwordConfirmation) throws ServiceException {
        try {
            Preconditions.checkNotNull("Debe ingresar un nombre de usuario", username);
            Preconditions.checkNotNull("Debe ingresar una contraseña", password);
            Preconditions.checkNotNull("Debe ingresar la confirmación de la contraseña", passwordConfirmation);
            Preconditions.checkArgument(password.equals(passwordConfirmation), "Las contraseñas deben ser identicas");
            Preconditions.checkArgument(!username.trim().isEmpty(), "El nombre de usuario no puede ser vacío");
            Preconditions.checkArgument(!password.trim().isEmpty(), "La contraseña no puede ser vacía");
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Do not use - Only for testing purposes
     * @param dao
     */
    public void setDao(RegistroDao dao) {
        this.dao = dao;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.uc.dei.aor.projecto8.whiteboard.beans;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.projecto8.whiteboard.entities.Users;
import pt.uc.dei.aor.projecto8.whiteboard.facades.UsersFacade;

/**
 *
 * @author Users
 */
@Named
@SessionScoped
@Stateful
public class LoggedUser {

    @Inject
    private UsersFacade userFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private Users loggedUser;

    public Users getLoggedUser() {
        if (loggedUser == null) {
            loggedUser = userFacade.findUserByUserName();
        }
        return loggedUser;
    }

    public void setLoggedUser(Users loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean confirmLoggedUser() {
        return loggedUser != null;
    }

}

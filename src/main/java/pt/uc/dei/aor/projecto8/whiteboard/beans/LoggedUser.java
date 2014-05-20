/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.uc.dei.aor.projecto8.whiteboard.beans;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.projecto8.whiteboard.entities.User;
import pt.uc.dei.aor.projecto8.whiteboard.facades.UsersFacade;

/**
 *
 * @author User
 */
@Named
@SessionScoped
@Stateful
public class LoggedUser {

    @Resource
    SessionContext ctx;
    @Inject
    private UsersFacade userFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private User loggedUser;

    public User getLoggedUser() {
//        if (loggedUser == null) {
//            String username = ctx.getCallerPrincipal().getName();
//            userFacade.findUserByUserName(ctx.getCallerPrincipal().getName());
//        }
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

}

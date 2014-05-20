/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.uc.dei.aor.projecto8.whiteboard.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.uc.dei.aor.projecto8.whiteboard.entities.User;

/**
 *
 * @author User
 */
@Stateless
public class UsersFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "WhiteboardPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(User.class);
    }

    public User findUserByUserName(String username) {
        return (User) em.createNamedQuery(User.findByUsername).setParameter("username",
                username).getSingleResult();
    }

}

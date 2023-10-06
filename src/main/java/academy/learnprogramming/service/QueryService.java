package academy.learnprogramming.service;

import academy.learnprogramming.entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class QueryService {

    @Inject
    EntityManager entityManager;

    public User findUserByEmail(String email){
        //TODO
        return entityManager.createNamedQuery(User.FIND_USER_BY_EMAIL, User.class).setParameter("email", email)
                .getResultList().get(0);
    }

    public boolean authenticateUser(String email, String password){
        //TODO
        return false;
    }

}

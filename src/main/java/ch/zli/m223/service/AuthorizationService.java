package ch.zli.m223.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ch.zli.m223.model.User;

@ApplicationScoped
public class AuthorizationService {
    @Inject
    private EntityManager entityManager;

    public User getUserByUsername(String username) {
        var query = entityManager.createQuery("FROM User WHERE username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }


    
}

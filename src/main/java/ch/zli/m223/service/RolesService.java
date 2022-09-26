package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import ch.zli.m223.model.Role;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class RolesService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Role createRole(Role role) {
        entityManager.persist(role);
        return role;
    }

    @Transactional
    public void deleteRole(Long id) {
        Role role = getRoleById(id);
        entityManager.remove(role);
    }

    @Transactional
    public void updateRole(Role role){
        entityManager.merge(role);
    }

    public List<Role> findAll(){
        var query = entityManager.createQuery("FROM Role", Role.class);
        return query.getResultList();
    }

    public Role getRoleById(Long id){
        return entityManager.find(Role.class, id);
    }

}

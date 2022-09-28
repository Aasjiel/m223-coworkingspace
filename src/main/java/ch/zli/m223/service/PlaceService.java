package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import ch.zli.m223.model.Place;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@ApplicationScoped
public class PlaceService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Place createPlace(@Valid Place place) {
        entityManager.persist(place);
        return place;
    }

    @Transactional
    public void deletePlace(Long id) {
        Place place = getPlaceById(id);
        entityManager.remove(place);
    }

    @Transactional
    public void updatePlace(Place place){
        entityManager.merge(place);
    }

    public List<Place> findAll(){
        var query = entityManager.createQuery("FROM Place", Place.class);
        return query.getResultList();
    }

    public Place getPlaceById(Long id){
        return entityManager.find(Place.class, id);
    }
}

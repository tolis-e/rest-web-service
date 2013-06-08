package org.example.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.example.model.Car;
import org.example.model.Car_;

@ApplicationScoped
public class CarRepository {

    @Inject
    private EntityManager em;

    public Car findById(Long id) {
        return em.find(Car.class, id);
    }

    public List<Car> findAllCars() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> car = cq.from(Car.class);
        cq.select(car);
        return em.createQuery(cq).getResultList();
    }

    public Car findByCarNumberFrame(String carNumberFrame) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Car> criteria = cb.createQuery(Car.class);
        Root<Car> car = criteria.from(Car.class);
        criteria.select(car).where(cb.equal(car.get(Car_.numberFrame), carNumberFrame));
        return em.createQuery(criteria).getSingleResult();
    }

}

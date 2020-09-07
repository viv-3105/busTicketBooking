package org.spring.BusTicketsBooking.respositories;

import org.spring.BusTicketsBooking.models.CityModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<CityModel, Integer> {
    List<CityModel> findAll();
}

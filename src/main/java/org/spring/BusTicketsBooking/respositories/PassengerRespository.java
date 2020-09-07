package org.spring.BusTicketsBooking.respositories;

import org.spring.BusTicketsBooking.models.PassengerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRespository extends CrudRepository<PassengerModel, Integer> {
    List<PassengerModel> findAll();
    void deleteAllByNumberPassenger(int number);

}

package org.spring.BusTicketsBooking.models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "passenger")
@Data
public class PassengerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "number_passenger")
    private int numberPassenger;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "form_city")
    private String fromCity;
    @Column(name = "to_city")
    private String toCity;
    @Column(name = "number_of_seat")
    private int numberOfSeat;

    public PassengerModel(String name, String lastName, String fromCity, String toCity) {
        this.name = name;
        this.lastName = lastName;
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    public PassengerModel(PassengerModel passengerModel) {
        this.id = passengerModel.getId();
        this.numberPassenger = passengerModel.numberPassenger;
        this.name = passengerModel.getName();
        this.lastName = passengerModel.getLastName();
        this.fromCity = passengerModel.getFromCity();
        this.toCity = passengerModel.getToCity();
        this.numberOfSeat = passengerModel.getNumberOfSeat();
    }

    public PassengerModel() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberPassenger() {
		return numberPassenger;
	}

	public void setNumberPassenger(int numberPassenger) {
		this.numberPassenger = numberPassenger;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public int getNumberOfSeat() {
		return numberOfSeat;
	}

	public void setNumberOfSeat(int numberOfSeat) {
		this.numberOfSeat = numberOfSeat;
	}
    
    

}

package org.spring.BusTicketsBooking.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.BusTicketsBooking.models.CityModel;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CityService {

	private List<CityModel> cities;
    private Map<String, Integer> grantedNumberForTheCity;
    
    public List<CityModel> getCities() {
		return cities;
	}

	public void setCities(List<CityModel> cities) {
		this.cities = cities;
	}

	public Map<String, Integer> getGrantedNumberForTheCity() {
		return grantedNumberForTheCity;
	}

	public void setGrantedNumberForTheCity(Map<String, Integer> grantedNumberForTheCity) {
		this.grantedNumberForTheCity = grantedNumberForTheCity;
	}

    public CityService() {
        grantedNumberForTheCity = new HashMap<>();;
    }

    public boolean isUserChoseCorrectly(String fromCity, String toCity){
        int a = grantedNumberForTheCity.get(fromCity);
        int b = grantedNumberForTheCity.get(toCity);
        return a <= b;
    }

    public Map<String, Integer> awardingNumberToTheCity(List<CityModel> cities ) {
        Map<String, Integer> numberOfCity= new HashMap<>();
        for (int i = 0; i < cities.size(); i++) {
            numberOfCity.put(cities.get(i).getName(), i);
        }
        return numberOfCity;
    }

}

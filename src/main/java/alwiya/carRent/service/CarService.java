package alwiya.carRent.service;

import alwiya.carRent.model.Car;
import alwiya.carRent.utils.SearchCarRequest;
import alwiya.carRent.utils.dto.CarBrandRequestDTO;

import java.util.List;

public interface CarService {

    List<Car> getAll(SearchCarRequest request);
    Car getOne(Integer id);
    Car create(CarBrandRequestDTO request);
    Car update(Integer id, Car request);
    void delete(Integer id);

}

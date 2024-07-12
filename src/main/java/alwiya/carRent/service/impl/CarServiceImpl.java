package alwiya.carRent.service.impl;

import alwiya.carRent.model.Brand;
import alwiya.carRent.model.Car;
import alwiya.carRent.repository.CarRepository;
import alwiya.carRent.service.BrandService;
import alwiya.carRent.service.CarService;
import alwiya.carRent.utils.SearchCarRequest;
import alwiya.carRent.utils.dto.CarBrandRequestDTO;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;
    private final BrandService brandService;

    @Override
    public List<Car> getAll(SearchCarRequest request) {
        Specification<Car> specification = getCarSpecification(request);
        return carRepository.findAll(specification);
    }

    @Override
    public Car getOne(Integer id) {
        try {
            return carRepository.findById(id).orElseThrow(Throwable::new);
        } catch (Throwable e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public Car create(CarBrandRequestDTO request) {
        Brand brand =brandService.getOne(request.getBrand_id());

        Car car = new Car();
        car.setName(request.getName());
        car.setBrand(brand);
        car.setAvailable(true);

        return carRepository.save(car);
    }

    @Override
    public Car update(Integer id, Car request) {
        Car updated = this.getOne(id);
        updated.setName(request.getName());
        return carRepository.save(updated);
    }

    @Override
    public void delete(Integer id) {
        carRepository.deleteById(id);
    }

    private Specification<Car> getCarSpecification(SearchCarRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null) {
                Predicate namePredicate = criteriaBuilder.like(
                        root.get("name"),
                        "%" + request.getName() + "%"
                );
                predicates.add(namePredicate);
            }
            return query.where(predicates.toArray(new Predicate[] {})).getRestriction();
        };
    }

}

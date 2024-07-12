package alwiya.carRent.service.impl;

import alwiya.carRent.model.Car;
import alwiya.carRent.model.Rent;
import alwiya.carRent.model.User;
import alwiya.carRent.repository.CarRepository;
import alwiya.carRent.repository.RentRepository;
import alwiya.carRent.service.CarService;
import alwiya.carRent.service.RentService;
import alwiya.carRent.service.UserService;
import alwiya.carRent.utils.dto.RentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final UserService userService;
    private final CarService carService;

    @Override
    public List<Rent> getAll() {
        return rentRepository.findAll();
    }

    @Override
    public Rent getOne(Integer id) {
        try {
            return rentRepository.findById(id).orElseThrow(Throwable::new);
        } catch (Throwable e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public Rent rent(RentRequestDTO request) {
        User user = userService.getOne(request.getUser_id());
        Car car = carService.getOne(request.getCar_id());

        if (car.getAvailable()) {
            Rent rent = new Rent();
            rent.setCompleted(false);
            rent.setCar(car);
            rent.setUser(user);
            Rent createRent= rentRepository.save(rent);

            car.setAvailable(false);
            carService.update(car.getId(), car);

            return createRent;
        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @Override
    @Transactional
    public Rent returns(Integer id, RentRequestDTO request) {
        Car car = carService.getOne(request.getCar_id());

        if (!car.getAvailable()) {
            Rent rent = this.getOne(id);
            rent.setCompleted(true);
            Rent updated = rentRepository.save(rent);

            car.setAvailable(true);
            carService.update(car.getId(), car);

            return updated;
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
}

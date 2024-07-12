package alwiya.carRent.controller;

import alwiya.carRent.model.Car;
import alwiya.carRent.service.CarService;
import alwiya.carRent.utils.SearchCarRequest;
import alwiya.carRent.utils.dto.CarBrandRequestDTO;
import alwiya.carRent.utils.dto.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @PostMapping
    public Car create(@RequestBody CarBrandRequestDTO request) {
        return carService.create(request);
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String name
    ) {
        SearchCarRequest request = new SearchCarRequest();
        request.setName(name);

        return Res.renderJson(
                carService.getAll(request),
                HttpStatus.OK,
                "Successfully get cars"
        );
    }

    @GetMapping("/{id}")
    public Car getOne(@PathVariable Integer id) {
        return carService.getOne(id);
    }

    @PutMapping("/{id}")
    public Car update(@PathVariable Integer id, @RequestBody Car request) {
        return carService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        carService.delete(id);
    }

}

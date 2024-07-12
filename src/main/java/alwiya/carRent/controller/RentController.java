package alwiya.carRent.controller;

import alwiya.carRent.model.Rent;
import alwiya.carRent.service.RentService;
import alwiya.carRent.utils.dto.RentRequestDTO;
import alwiya.carRent.utils.dto.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rents")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @PostMapping
    public ResponseEntity<?> rent(@RequestBody RentRequestDTO request) {
        return Res.renderJson(
                rentService.rent(request),
                HttpStatus.CREATED,
                "Successfully create a rent"
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return Res.renderJson(
                rentService.getAll(),
                HttpStatus.OK,
                "Successfully get rents"
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> returns(@PathVariable Integer id, @RequestBody RentRequestDTO request) {
        return Res.renderJson(
                rentService.returns(id, request),
                HttpStatus.OK,
                "Car with id "+ request.getCar_id() +" successfully returned"
        );
    }


}

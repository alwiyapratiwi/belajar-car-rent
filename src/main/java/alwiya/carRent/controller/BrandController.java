package alwiya.carRent.controller;

import alwiya.carRent.model.Brand;
import alwiya.carRent.service.BrandService;
import alwiya.carRent.utils.SearchBrandRequest;
import alwiya.carRent.utils.dto.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Brand request) {
        return Res.renderJson(
                brandService.create(request),
                HttpStatus.CREATED,
                "Successfully brand a user"
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String name
    ) {
        SearchBrandRequest request = new SearchBrandRequest();
        request.setName(name);

        return Res.renderJson(
                brandService.getAll(request),
                HttpStatus.OK,
                "Successfully get brands"
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return Res.renderJson(
                brandService.getOne(id),
                HttpStatus.OK,
                "Successfully get a brand with id " + id
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Brand request) {
        return Res.renderJson(
                brandService.update(id, request),
                HttpStatus.OK,
                "Successfully updated brand"
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        brandService.delete(id);
    }

}

package alwiya.carRent.service;

import alwiya.carRent.model.Brand;
import alwiya.carRent.utils.SearchBrandRequest;

import java.util.List;

public interface BrandService {

    List<Brand> getAll(SearchBrandRequest request);
    Brand getOne(Integer id);
    Brand create(Brand request);
    Brand update(Integer id, Brand request);
    void delete(Integer id);

}

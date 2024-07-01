package alwiya.carRent.service.impl;

import alwiya.carRent.model.Brand;
import alwiya.carRent.repository.BrandRepository;
import alwiya.carRent.service.BrandService;
import alwiya.carRent.utils.SearchBrandRequest;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public List<Brand> getAll(SearchBrandRequest request) {
        Specification<Brand> specification = getBrandSpecification(request);
        return brandRepository.findAll(specification);
    }

    @Override
    public Brand getOne(Integer id) {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public Brand create(Brand request) {
        return brandRepository.save(request);
    }

    @Override
    public Brand update(Integer id, Brand request) {
        Brand brand =this.getOne(id);
        brand.setName(request.getName());
        brandRepository.save(brand);
        return null;
    }

    @Override
    public void delete(Integer id) {
        brandRepository.deleteById(id);
    }

    private Specification<Brand> getBrandSpecification(SearchBrandRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null) {
                Predicate namePredicate =criteriaBuilder.like(
                        root.get("name"),
                        "%" + request.getName() + "%"
                );
                predicates.add(namePredicate);
            }
            return query.where(predicates.toArray(new Predicate[] {})).getRestriction();
        };
    }
}

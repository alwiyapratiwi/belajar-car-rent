package alwiya.carRent.service.impl;

import alwiya.carRent.model.User;
import alwiya.carRent.repository.UserRepository;
import alwiya.carRent.service.UserService;
import alwiya.carRent.utils.SearchUserRequest;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAll(SearchUserRequest request) {
        Specification<User> specification = getUserSpecification(request);
        return userRepository.findAll(specification);
    }

    @Override
    public User getOne(Integer id) {
        try {
            return userRepository.findById(id).orElseThrow(Throwable::new);
        } catch (Throwable e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public User create(User request) {
        return userRepository.save(request);
    }

    @Override
    public User update(Integer id, User request) {
        User user = this.getOne(id);
        user.setName(request.getName());
        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    private Specification<User> getUserSpecification(SearchUserRequest request) {
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

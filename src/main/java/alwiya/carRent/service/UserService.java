package alwiya.carRent.service;

import alwiya.carRent.model.User;
import alwiya.carRent.utils.SearchUserRequest;

import java.util.List;

public interface UserService {

    List<User> getAll(SearchUserRequest request);
    User getOne(Integer id);
    User create(User request);
    User update(Integer id, User request);
    void delete(Integer id);

}

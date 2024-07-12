package alwiya.carRent.service;

import alwiya.carRent.model.Rent;
import alwiya.carRent.utils.dto.RentRequestDTO;

import java.util.List;

public interface RentService {

    List<Rent> getAll();
    Rent getOne(Integer id);
    Rent rent(RentRequestDTO request);
    Rent returns(Integer id, RentRequestDTO request);
}

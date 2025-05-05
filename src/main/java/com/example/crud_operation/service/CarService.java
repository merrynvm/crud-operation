package com.example.crud_operation.service;

import com.example.crud_operation.entity.CarEntity;
import com.example.crud_operation.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<CarEntity> allCars(){
        return carRepository.findAll();
    }

    public Optional<CarEntity> carById(Long id){
        if(carRepository.existsById(id)){
            return carRepository.findById(id);
        }else {
            return Optional.empty();
        }
    }

    public CarEntity addCar(CarEntity newCar){
        return carRepository.save(newCar);
    }

    public Optional<CarEntity> updateCarById(Long id, CarEntity carToUpdate){
        if(!carRepository.existsById(id)){
            return Optional.empty();
        }

        carToUpdate.setId(id);
        return Optional.of(carRepository.save(carToUpdate));
    }


    public Optional<?> deleteCarById(Long id){
        if(!carRepository.existsById(id)){
           return Optional.empty();
        }

        carRepository.deleteById(id);

        return Optional.of(HttpStatus.NO_CONTENT);
    }

    public void deleteAllCar(){
        carRepository.deleteAll();
    }

}

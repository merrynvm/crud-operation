package com.example.crud_operation.controller;

import com.example.crud_operation.entity.CarEntity;
import com.example.crud_operation.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping
    public List<CarEntity> allCars(){
        return carService.allCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarEntity> carById(@PathVariable Long id){
        Optional<CarEntity> carFound = carService.carById(id);

        return carFound
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CarEntity> addCar(@RequestBody CarEntity newCar){
        return ResponseEntity.ok(carService.addCar(newCar));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarEntity> updateCarById(@PathVariable Long id, @RequestBody CarEntity carToUpdate){
        Optional<CarEntity> carFound = carService.updateCarById(id, carToUpdate);

        return carFound
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarEntity> deleteCarById(@PathVariable Long id){
        Optional<?> carFound = carService.deleteCarById(id);

        if(carFound.isPresent()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAllCars(){
        carService.deleteAllCar();
        return ResponseEntity.noContent().build();
    }

}

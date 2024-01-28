package kz.rakho.exercise2.controllers;


import kz.rakho.exercise2.models.TemperatureModel;
import kz.rakho.exercise2.services.TemperatureService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/temperature")
@AllArgsConstructor
public class TemperatureController {

    private final TemperatureService service;

    @GetMapping
    public List<TemperatureModel> findAllTemperature() {

            return service.findAllTemperature();
    }


    @PostMapping("save_temperature")
    public void saveTemperature(@Valid @RequestBody TemperatureModel temperature){
        service.saveTemperature(temperature);
    }

    @DeleteMapping("delete_temperature/{id}")
    public void Temperature(@PathVariable Long id) {
        service.deleteTemperature(id);
    }
}

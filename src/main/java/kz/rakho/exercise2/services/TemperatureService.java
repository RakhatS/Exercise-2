package kz.rakho.exercise2.services;

import kz.rakho.exercise2.models.TemperatureModel;

import java.util.List;

public interface TemperatureService {
    List<TemperatureModel> findAllTemperature();
    TemperatureModel saveTemperature(TemperatureModel temperature);
    TemperatureModel findById(Long id);
    void deleteTemperature(Long id);
}

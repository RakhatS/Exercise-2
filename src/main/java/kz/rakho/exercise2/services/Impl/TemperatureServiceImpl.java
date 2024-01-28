package kz.rakho.exercise2.services.Impl;

import kz.rakho.exercise2.models.TemperatureModel;
import kz.rakho.exercise2.repositories.TemperatureRepository;
import kz.rakho.exercise2.services.TemperatureService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class TemperatureServiceImpl implements TemperatureService {
    private final TemperatureRepository repository;

    @Override
    public List<TemperatureModel> findAllTemperature() {
        return repository.findAll();
    }

    @Override
    public TemperatureModel saveTemperature(TemperatureModel temperature) {
        return repository.save(temperature);
    }

    @Override
    public TemperatureModel findById(Long id) {
        return repository.findEmployeeById(id);
    }


    @Override
    public void deleteTemperature(Long id) {
        repository.deleteById(id);
    }
}

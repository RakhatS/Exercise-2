package kz.rakho.exercise2.repositories;

import kz.rakho.exercise2.models.TemperatureModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<TemperatureModel, Long> {
    void deleteById(Long id);
    TemperatureModel findEmployeeById(Long id);
}

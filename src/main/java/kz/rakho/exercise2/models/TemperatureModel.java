package kz.rakho.exercise2.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Entity
//@Getter
@Table(name = "Temperatures")
public class TemperatureModel {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private double value;
}

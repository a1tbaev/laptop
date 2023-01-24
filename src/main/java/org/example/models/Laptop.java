package org.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.enums.OperationSystem;

import java.time.LocalDate;

@Entity
@Table(name = "laptops")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String brand;
    @Enumerated(EnumType.STRING)
    private OperationSystem operationSystem;
    private int memory;
    private int price;
    private LocalDate dateOfIssue;

    public Laptop(String brand, OperationSystem operationSystem, int memory, int price, LocalDate dateOfIssue) {
        this.brand = brand;
        this.operationSystem = operationSystem;
        this.memory = memory;
        this.price = price;
        this.dateOfIssue = dateOfIssue;
    }
}

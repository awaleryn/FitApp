package com.example.fitapp.intake;

import com.example.fitapp.product.Product;
import com.example.fitapp.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "daily_intake")
public class DailyIntake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double totalCalories = 0;

    @Column(nullable = false)
    private double totalProtein = 0;

    @Column(nullable = false)
    private double totalFat = 0;

    @Column(nullable = false)
    private double totalCarbohydrates = 0;

    private String caloriesToday;

    private String proteinToday;

    private String fatToday;

    private String carbohydratesToday;

    public DailyIntake(User user, LocalDate date) {
        this.user = user;
        this.date = date;
    }

    public void addProduct(Product product) {
        this.totalCalories += product.getCalories();
        this.totalProtein += product.getProtein();
        this.totalFat += product.getFat();
        this.totalCarbohydrates += product.getCarbohydrates();
    }
}
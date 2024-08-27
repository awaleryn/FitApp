package com.example.fitapp.intake;

import com.example.fitapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyIntakeRepository extends JpaRepository<DailyIntake, Long> {

    Optional<DailyIntake> findByUserAndDate(User user, LocalDate date);

    List<DailyIntake> findByUserOrderByDateDesc(User user);

}

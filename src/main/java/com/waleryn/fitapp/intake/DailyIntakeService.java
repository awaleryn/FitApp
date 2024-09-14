package com.waleryn.fitapp.intake;

import com.waleryn.fitapp.exception.DailyNeedsNotAssignedException;
import com.waleryn.fitapp.product.Product;
import com.waleryn.fitapp.user.User;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DailyIntakeService {

    private static final Logger logger = LoggerFactory.getLogger(DailyIntakeService.class);
    private final DailyIntakeRepository dailyIntakeRepository;
    private final DailyIntakeMapper dailyIntakeMapper;

    public DailyIntakeService(
            DailyIntakeRepository dailyIntakeRepository,
            DailyIntakeMapper dailyIntakeMapper
    ) {
        this.dailyIntakeRepository = dailyIntakeRepository;
        this.dailyIntakeMapper = dailyIntakeMapper;
    }

    @Transactional
    public DailyIntakeDto addProductToDailyIntake(User user, Product product) {

        if (user.getDailyCaloricNeeds() == null
                || user.getProteinNeeds() == null
                || user.getFatNeeds() == null
                || user.getCarbNeeds() == null) {
            throw new DailyNeedsNotAssignedException("User doesn't have assigned daily needs!");
        }

        LocalDate today = LocalDate.now();
        DailyIntake dailyIntake = dailyIntakeRepository.findByUserAndDate(user, today)
                .orElse(new DailyIntake(user, today));

        logger.info("Daily Intake before adding product: " + dailyIntake);

        dailyIntake.addProduct(product);

        DailyIntake savedDailyIntake = dailyIntakeRepository.save(dailyIntake);


        logger.info("Daily Intake after saving: " + savedDailyIntake);
        return dailyIntakeMapper.toDto(dailyIntake);
    }

    public List<DailyIntake> getDailyIntakeHistory(User user) {
        return dailyIntakeRepository.findByUserOrderByDateDesc(user);
    }

    public DailyIntakeDto getDailyIntakeForDate(User user, LocalDate date) {
         Optional<DailyIntake> optionalDailyIntake = dailyIntakeRepository.findByUserAndDate(user, date);

         return optionalDailyIntake
                 .map(dailyIntakeMapper::toDto)
                 .orElseThrow(() -> new RuntimeException("No data found for the given date"));
    }
}
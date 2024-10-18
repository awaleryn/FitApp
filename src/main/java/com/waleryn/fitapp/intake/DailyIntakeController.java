package com.waleryn.fitapp.intake;

import com.waleryn.fitapp.product.Product;
import com.waleryn.fitapp.product.ProductRepository;
import com.waleryn.fitapp.user.User;
import com.waleryn.fitapp.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/intake")
public class DailyIntakeController {

    private final DailyIntakeService dailyIntakeService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public DailyIntakeController(
            DailyIntakeService dailyIntakeService,
            ProductRepository productRepository,
            UserRepository userRepository
    ) {
        this.dailyIntakeService = dailyIntakeService;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public DailyIntakeDto addProductToDailyIntake(
            @RequestParam String productName,
            Principal principal
    ) {
        Product product = productRepository.findByProductNameIgnoreCase(productName)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return dailyIntakeService.addProductToDailyIntake(user, product);
    }

    @GetMapping("/history")
    public List<DailyIntakeDto> getDailyIntakeHistory(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return dailyIntakeService.getDailyIntakeHistory(user);
    }

    @GetMapping("/history/{date}")
    public DailyIntakeDto getDailyIntakeForDate(@PathVariable String date, Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate localDate = LocalDate.parse(date);
        return dailyIntakeService.getDailyIntakeForDate(user, localDate);
    }
}
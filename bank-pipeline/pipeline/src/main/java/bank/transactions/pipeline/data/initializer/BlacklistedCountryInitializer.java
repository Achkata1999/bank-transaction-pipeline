package bank.transactions.pipeline.data.initializer;

import bank.transactions.pipeline.model.entity.BlackListedCountryEntity;
import bank.transactions.pipeline.repository.BlackListedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BlacklistedCountryInitializer implements CommandLineRunner {

    @Autowired
    private BlackListedRepository blackListedRepository;

    /**
     * Create Sample Data
     */
    @Override
    public void run(String... args) {
        List<BlackListedCountryEntity> blacklistedCountries = Arrays.asList(
                new BlackListedCountryEntity("RU", "Russia"),
                new BlackListedCountryEntity("UKR", "Ukraine"),
                new BlackListedCountryEntity("CAN", "Canada")
        );

        blacklistedCountries.forEach(country -> {
            if (!blackListedRepository.existsById(country.getCountryIsoCode())) {
                blackListedRepository.save(country);
            }
        });

        System.out.println("✅ Blacklisted countries initialized successfully.");
    }
}

package bank.transactions.pipeline.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BlackListedCountryEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String countryIsoCode;

    private String countryName;

    public BlackListedCountryEntity() {

    }

    public BlackListedCountryEntity(String countryIsoCode, String countryName) {
        this.countryIsoCode = countryIsoCode;
        this.countryName = countryName;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}

package bank.transactions.pipeline.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class TransactionEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String transactionId;

    private String userId;

    private BigDecimal amount;

    private Instant timestamp;
    private Instant createdAt;
    private String countryIsoCode;
    private Double latCoord;
    private Double longCoord;
    private boolean isFraud;


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public Double getLatCoord() {
        return latCoord;
    }

    public void setLatCoord(Double latCoord) {
        this.latCoord = latCoord;
    }

    public Double getLongCoord() {
        return longCoord;
    }

    public void setLongCoord(Double longCoord) {
        this.longCoord = longCoord;
    }

    public boolean getFraud() {
        return isFraud;
    }

    public void setFraud(boolean fraud) {
        isFraud = fraud;
    }
}

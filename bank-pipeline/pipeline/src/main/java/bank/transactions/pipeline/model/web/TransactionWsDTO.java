package bank.transactions.pipeline.model.web;

import jakarta.validation.constraints.*;

import java.time.Instant;

public class TransactionWsDTO {

    @NotBlank(message = "Transaction ID cannot be empty")
    private String tran_id;

    @NotBlank(message = "User ID cannot be empty")
    private String user_id;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotNull(message = "Timestamp cannot be null")
    private Instant timestamp;

    @NotBlank(message = "Country cannot be empty")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Invalid country code. Use ISO 3166-1 alpha-2 format")
    private String country;

    @NotNull(message = "Latitude cannot be null")
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private Double lat_coord;

    @NotNull(message = "Longitude cannot be null")
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    private Double long_coord;

    public String getTran_id() {
        return tran_id;
    }

    public void setTran_id(String tran_id) {
        this.tran_id = tran_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLat_coord() {
        return lat_coord;
    }

    public void setLat_coord(Double lat_coord) {
        this.lat_coord = lat_coord;
    }

    public Double getLong_coord() {
        return long_coord;
    }

    public void setLong_coord(Double long_coord) {
        this.long_coord = long_coord;
    }
}

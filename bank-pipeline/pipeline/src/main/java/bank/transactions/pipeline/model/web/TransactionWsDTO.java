package bank.transactions.pipeline.model.web;

import java.time.Instant;

public class TransactionWsDTO {

    private String tran_id;

    private String user_id;

    private Double amount;

    private Instant timestamp;

    private String country;

    private Double lat_coord;

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

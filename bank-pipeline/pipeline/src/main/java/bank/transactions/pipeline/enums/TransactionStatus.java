package bank.transactions.pipeline.enums;

public enum TransactionStatus {

    CREATED(0, "Created", "Transaction is successfully created"),
    EXCESSIVE_TRANSACTIONS(1, "Excessive Transactions", "User has created more than 10 transactions within the last 1 minute"),
    GEOGRAPHIC_ANOMALY(2, "Geographic Anomaly", "Two transactions created from different locations more than 300 kilometers apart within the last 30 minutes"),
    BLACKLISTED_COUNTRY(3, "Blacklisted Country", "Transaction created within a blacklisted country"),
    MULTI_COUNTRY_ACTIVITY(4, "Multi-Country Activity", "User has created transactions in 3 different countries within the last 10 minutes");

    private final int code;
    private final String displayName;
    private final String description;

    TransactionStatus(int code, String displayName, String description) {
        this.code = code;
        this.displayName = displayName;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}


package bank.transactions.pipeline.model.web;

public class TransactionResponseCodeWsDTO {

    private int code;
    private String name;
    private String message;

    public TransactionResponseCodeWsDTO(int code, String name, String message) {
        this.code = code;
        this.name = name;
        this.message = message;
    }

    public int getCode() {
        return code;
    }


    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }


}

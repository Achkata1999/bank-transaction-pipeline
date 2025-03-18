package bank.transactions.pipeline.controller;

import bank.transactions.pipeline.enums.TransactionStatus;
import bank.transactions.pipeline.model.web.TransactionResponseCodeWsDTO;
import bank.transactions.pipeline.model.web.TransactionWsDTO;
import bank.transactions.pipeline.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {


    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/new")
    public ResponseEntity<TransactionResponseCodeWsDTO> createTransaction(@RequestBody TransactionWsDTO transactionWsDTO) {
        TransactionStatus transactionStatus = transactionService.createTransaction(transactionWsDTO);

        return new ResponseEntity<>(new TransactionResponseCodeWsDTO(transactionStatus.getCode(), transactionStatus.getDisplayName(), transactionStatus.getDescription()), getHttpStatus(transactionStatus));
    }

    private HttpStatus getHttpStatus(TransactionStatus transactionStatus) {
        if (transactionStatus.equals(TransactionStatus.CREATED)) {
            return HttpStatus.CREATED;
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }
}

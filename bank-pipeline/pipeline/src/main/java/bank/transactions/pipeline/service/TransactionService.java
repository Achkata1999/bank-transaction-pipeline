package bank.transactions.pipeline.service;

import bank.transactions.pipeline.enums.TransactionStatus;
import bank.transactions.pipeline.model.web.TransactionWsDTO;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    TransactionStatus createTransaction(TransactionWsDTO transactionWsDTO);
}

package bank.transactions.pipeline.service.impl;

import bank.transactions.pipeline.enums.TransactionStatus;
import bank.transactions.pipeline.model.entity.TransactionEntity;
import bank.transactions.pipeline.model.web.TransactionWsDTO;
import bank.transactions.pipeline.repository.BlackListedRepository;
import bank.transactions.pipeline.repository.TransactionRepository;
import bank.transactions.pipeline.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BlackListedRepository blackListedRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, BlackListedRepository blackListedRepository) {
        this.transactionRepository = transactionRepository;
        this.blackListedRepository = blackListedRepository;
    }

    @Override
    public TransactionStatus createTransaction(TransactionWsDTO transactionWsDTO) {
        TransactionEntity transactionEntity = buildTransactionEntity(transactionWsDTO);

        TransactionStatus status = checkForFraud(transactionWsDTO, transactionEntity);

        transactionEntity.setFraud(isFraud(status));

        transactionRepository.save(transactionEntity);
        return status;
    }

    private boolean isFraud(TransactionStatus status) {
        return status != TransactionStatus.CREATED;
    }

    private TransactionEntity buildTransactionEntity(TransactionWsDTO transactionWsDTO) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(transactionWsDTO.getTran_id());
        transactionEntity.setUserId(transactionWsDTO.getUser_id());
        transactionEntity.setAmount(BigDecimal.valueOf(transactionWsDTO.getAmount()));
        transactionEntity.setCountryIsoCode(transactionWsDTO.getCountry());
        transactionEntity.setTimestamp(transactionWsDTO.getTimestamp());
        transactionEntity.setLatCoord(transactionWsDTO.getLat_coord());
        transactionEntity.setLongCoord(transactionWsDTO.getLong_coord());
        transactionEntity.setCreatedAt(Instant.now());
        return transactionEntity;
    }

    private TransactionStatus checkForFraud(TransactionWsDTO transactionWsDTO, TransactionEntity transactionEntity) {
        if (validateLastMinuteTransactions(transactionWsDTO.getUser_id(), transactionEntity.getCreatedAt())) {
            return TransactionStatus.EXCESSIVE_TRANSACTIONS;
        }

        if (validateDistance(transactionWsDTO, transactionEntity.getCreatedAt())) {
            return TransactionStatus.GEOGRAPHIC_ANOMALY;
        }

        if (blackListedRepository.findById(transactionWsDTO.getCountry()).isPresent()) {
            return TransactionStatus.BLACKLISTED_COUNTRY;
        }

        if (validateCounties(transactionWsDTO)) {
            return TransactionStatus.MULTI_COUNTRY_ACTIVITY;
        }

        return TransactionStatus.CREATED;
    }

    private boolean validateCounties(TransactionWsDTO transactionWsDTO) {
        Instant tenMinutesAgo = Instant.now().minusSeconds(10 * 60);

        List<String> countryCodes = transactionRepository.findDistinctCountriesByPeriod(
                transactionWsDTO.getUser_id(), tenMinutesAgo);

        boolean isNewCountry = !countryCodes.contains(transactionWsDTO.getCountry());

        return countryCodes.size() > 2 || (countryCodes.size() == 2 && isNewCountry);
    }


    private boolean validateLastMinuteTransactions(String userId, Instant createdAt) {
        return transactionRepository.countTransactionsByPeriod(
                userId, createdAt.minusSeconds(60)) >= 10;
    }


    private boolean validateDistance(TransactionWsDTO transactionWsDTO, Instant createdAt) {
        Instant thirtyMinutesAgo = createdAt.minusSeconds(30 * 60);
        List<TransactionEntity> recentTransactions = transactionRepository.findTransactionsLast30Minutes(transactionWsDTO.getUser_id(), thirtyMinutesAgo);

        for (TransactionEntity transaction : recentTransactions) {
            double distance = haversine(transaction.getLatCoord(), transaction.getLongCoord(), transactionWsDTO.getLat_coord(), transactionWsDTO.getLong_coord());
            if (distance > 300) {
                return true;
            }
        }
        return false;
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in km
    }
}

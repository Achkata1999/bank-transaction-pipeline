package bank.transactions.pipeline.repository;

import bank.transactions.pipeline.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

    @Query("SELECT COUNT(t) FROM TransactionEntity t WHERE t.userId = :userId AND t.createdAt >= :periodAgo")
    long countTransactionsByPeriod(@Param("userId") String userId, @Param("periodAgo") Instant periodAgo);

    @Query("SELECT t FROM TransactionEntity t WHERE t.userId = :userId AND t.createdAt >= :thirtyMinutesAgo")
    List<TransactionEntity> findTransactionsLast30Minutes(@Param("userId") String userId, @Param("thirtyMinutesAgo") Instant thirtyMinutesAgo);

    @Query("SELECT DISTINCT t.countryIsoCode FROM TransactionEntity t WHERE t.userId =:userId AND t.createdAt>=:periodAgo")
    List<String> findDistinctCountriesByPeriod(@Param("userId") String userId, @Param("periodAgo") Instant periodAgo);

}

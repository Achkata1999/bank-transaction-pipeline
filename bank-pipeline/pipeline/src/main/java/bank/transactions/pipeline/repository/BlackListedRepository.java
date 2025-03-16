package bank.transactions.pipeline.repository;

import bank.transactions.pipeline.model.entity.BlackListedCountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListedRepository extends JpaRepository<BlackListedCountryEntity, String> {
}

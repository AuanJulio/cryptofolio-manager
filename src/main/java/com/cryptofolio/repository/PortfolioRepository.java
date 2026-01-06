package com.cryptofolio.repository;

import com.cryptofolio.entity.Portfolio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PortfolioRepository extends MongoRepository<Portfolio, String> {
}

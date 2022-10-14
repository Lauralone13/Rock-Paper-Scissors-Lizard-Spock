package io.everyonecodes.mongo_rockscissorspaperlizardspock.gameresult.persistence.repository;

import io.everyonecodes.mongo_rockscissorspaperlizardspock.gameresult.persistence.domain.GameResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameResultRepository extends MongoRepository<GameResult, String> {
}

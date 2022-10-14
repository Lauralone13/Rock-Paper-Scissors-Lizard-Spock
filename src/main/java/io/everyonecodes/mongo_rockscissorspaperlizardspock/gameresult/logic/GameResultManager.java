package io.everyonecodes.mongo_rockscissorspaperlizardspock.gameresult.logic;

import io.everyonecodes.mongo_rockscissorspaperlizardspock.gameresult.persistence.domain.GameResult;
import io.everyonecodes.mongo_rockscissorspaperlizardspock.gameresult.persistence.repository.GameResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameResultManager {
	
	private final GameResultRepository repository;
	
	public GameResultManager(GameResultRepository repository) {
		this.repository = repository;
	}
	
	public void save(String result) {
		GameResult gameResult = new GameResult(result);
		repository.save(gameResult);
	}
	
	public String getStatistics() {
		List<GameResult> gameResults = repository.findAll();
		if(! (gameResults.size() == 0)) {
			GameResult lastEntry = gameResults.get(gameResults.size() - 1);
			return lastEntry.getResult();
		}
		return "";
	}
	
	public void clearScore() {
		repository.deleteAll();
	}
}

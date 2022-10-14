package io.everyonecodes.mongo_rockscissorspaperlizardspock.gameresult.logic;

import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.configuration.GameRunner;
import io.everyonecodes.mongo_rockscissorspaperlizardspock.gameresult.persistence.domain.GameResult;
import io.everyonecodes.mongo_rockscissorspaperlizardspock.gameresult.persistence.repository.GameResultRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameResultManagerTest {
	
	@Autowired
	GameResultManager gameResultManager;
	
	@MockBean
	GameResultRepository gameResultRepository;
	
	@MockBean
	GameRunner gameRunner;
	
	@Test
	void save() {
		
		gameResultManager.save("test");
		GameResult gameResult = new GameResult("test");
		Mockito.verify(gameResultRepository).save(gameResult);
	}
	
	private static Stream<Arguments> parameters() {
		return Stream.of(
				Arguments.of("", List.of()),
				Arguments.of(
						"Nobody wins: 1, Player 1 wins: 2",
						List.of(
								new GameResult("Nobody wins: 1, Player 1 wins: 1"),
								new GameResult("Nobody wins: 1, Player 1 wins: 2"))),
				Arguments.of(
						"Nobody wins: 1, Player 1 wins: 1, Player 2 wins: 1",
						List.of(
								new GameResult("Player 1 wins: 1"),
								new GameResult("Nobody wins: 1, Player 1 wins: 1"),
								new GameResult("Nobody wins: 1, Player 1 wins: 1, Player 2 wins: 1"))),
				Arguments.of(
						"Player 2 wins: 1",
						List.of(
								new GameResult("Player 2 wins: 1"))),
				Arguments.of(
						"Nobody wins: 2, Player 1 wins: 2, Player 2 wins: 1",
						List.of(
								new GameResult("Player 1 wins: 1"),
								new GameResult("Player 1 wins: 1, Player 2 wins: 1"),
								new GameResult("Nobody wins: 1, Player 1 wins: 1, Player 2 wins: 1"),
								new GameResult("Nobody wins: 2, Player 1 wins: 1, Player 2 wins: 1"),
								new GameResult("Nobody wins: 2, Player 1 wins: 2, Player 2 wins: 1")))
				);
	}
	@ParameterizedTest
	@MethodSource("parameters")
	void getStatistics(String expected, List<GameResult> entryList) {
		
		Mockito.when(gameResultRepository.findAll()).thenReturn(entryList);
	
		String result = gameResultManager.getStatistics();
		
		Assertions.assertEquals(expected, result);
		Mockito.verify(gameResultRepository).findAll();
	}
}
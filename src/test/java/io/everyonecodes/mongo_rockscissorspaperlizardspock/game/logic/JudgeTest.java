package io.everyonecodes.mongo_rockscissorspaperlizardspock.game.logic;

import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.domain.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class JudgeTest {
	
	Judge judge = new Judge();
	
	private static Stream<Arguments> parameters() {
		return Stream.of(
				Arguments.of(
						new Move("test1", "1", List.of("test1 test2", "test1 test4")),
						new Move("test2", "2", List.of("test2 test3", "test2 test5")), "Player 1 wins ~test1 test2~"),
				Arguments.of(
						new Move("test2", "2", List.of("test2 test3", "test2 test5")),
						new Move("test3", "3", List.of("test3 test4", "test3 test1")), "Player 1 wins ~test2 test3~"),
				Arguments.of(
						new Move("test3", "3", List.of("test3 test4", "test3 test1")),
						new Move("test4", "4", List.of("test4 test5", "test4 test2")), "Player 1 wins ~test3 test4~"),
				Arguments.of(
						new Move("test1", "1", List.of("test1 test2", "test1 test4")),
						new Move("test5", "5", List.of("test5 test1", "test5 test3")), "Player 2 wins ~test5 test1~"),
				Arguments.of(
						new Move("test1", "1", List.of("test1 test2", "test1 test4")),
						new Move("test1", "1", List.of("test1 test2", "test1 test4")), "It's a tie! Nobody won! xD")
		                );
	}
	@ParameterizedTest
	@MethodSource("parameters")
	void judge(Move move1, Move move2, String expected) {
		String result = judge.judge(move1, move2);
		Assertions.assertEquals(expected, result);
	}
}
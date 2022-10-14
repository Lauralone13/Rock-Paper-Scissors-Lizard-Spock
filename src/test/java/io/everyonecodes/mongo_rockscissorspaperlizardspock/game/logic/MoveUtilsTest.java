package io.everyonecodes.mongo_rockscissorspaperlizardspock.game.logic;

import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.configuration.GameRunner;
import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.domain.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
class MoveUtilsTest {

	@Autowired
	MoveUtils moveUtils;

	@MockBean
	GameRunner gameRunner;

	@Test
	void getNames() {
		String result = moveUtils.getNames();
		String expected = "(test1(1), test2(2), test3(3), test4(4), test5(5))";
		Assertions.assertEquals(expected, result);
	}
	
	private static Stream<Arguments> parameters() {
		return Stream.of(
				Arguments.of("test1", new Move("test1", "1", List.of("test1 test2", "test1 test4"))),
				Arguments.of("test3", new Move("test3", "3", List.of("test3 test4", "test3 test1"))),
				Arguments.of("test5", new Move("test5", "5", List.of("test5 test1", "test5 test3")))
		                );
	}
	@ParameterizedTest
	@MethodSource("parameters")
	void getOneByName(String moveName, Move expected) {
		Optional<Move> oResult = moveUtils.getOne(moveName);
		Assertions.assertTrue(oResult.isPresent());
		Move result = oResult.get();
		Assertions.assertEquals(expected, result);
	}
	
	private static Stream<Arguments> parametersByNumbers() {
		return Stream.of(
				Arguments.of("1", new Move("test1", "1", List.of("test1 test2", "test1 test4"))),
				Arguments.of("3", new Move("test3", "3", List.of("test3 test4", "test3 test1"))),
				Arguments.of("5", new Move("test5", "5", List.of("test5 test1", "test5 test3")))
		                );
	}
	@ParameterizedTest
	@MethodSource("parametersByNumbers")
	void getOneByNumber(String moveNumber, Move expected) {
		Optional<Move> oResult = moveUtils.getOne(moveNumber);
		Assertions.assertTrue(oResult.isPresent());
		Move result = oResult.get();
		Assertions.assertEquals(expected, result);
		
	}
}
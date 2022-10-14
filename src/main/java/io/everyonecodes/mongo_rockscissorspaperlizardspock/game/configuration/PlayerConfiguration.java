package io.everyonecodes.mongo_rockscissorspaperlizardspock.game.configuration;

import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.domain.Move;
import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.logic.MoveUtils;
import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.player.Computer;
import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.player.Human;
import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.player.Player;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PlayerConfiguration {

	@Bean
	Player player1(MoveUtils moveUtils) {
		return new Human(moveUtils);
	}
	
	@Bean
	Player player2(List<Move> moves) {
		return new Computer(moves);
	}
}

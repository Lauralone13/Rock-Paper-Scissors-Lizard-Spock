package io.everyonecodes.mongo_rockscissorspaperlizardspock.game.configuration;

import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.domain.Move;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("game")
public class MoveConfiguration {
	
	private List<Move> moves;
	
	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}
	
	@Bean
	List<Move> moves() {
		return moves;
	}
}

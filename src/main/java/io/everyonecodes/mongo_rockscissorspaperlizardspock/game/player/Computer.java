package io.everyonecodes.mongo_rockscissorspaperlizardspock.game.player;

import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.domain.Move;

import java.util.List;
import java.util.Random;

public class Computer implements Player {
	
	private final List<Move> moves;
	
	public Computer(List<Move> moves) {
		this.moves = moves;
	}
	
	@Override
	public Move play() {
		Random random = new Random();
		int numberOfMoves = moves.size();
		int choice = random.nextInt(numberOfMoves);
		return moves.get(choice);
	}
	
	@Override
	public boolean wantsToPlayAgain() {
		return true;
	}
}

package io.everyonecodes.mongo_rockscissorspaperlizardspock.game.player;

import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.domain.Move;

public interface Player {
	
	Move play();
	boolean wantsToPlayAgain();
}

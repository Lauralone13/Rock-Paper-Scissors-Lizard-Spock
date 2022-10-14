package io.everyonecodes.mongo_rockscissorspaperlizardspock.game.player;

import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.domain.Move;
import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.logic.MoveUtils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Human implements Player {
	
	private final MoveUtils moveUtils;
	private final Scanner scanner = new Scanner(System.in);
	
	public Human(MoveUtils moveUtils) {
		this.moveUtils = moveUtils;
	}
	
	@Override
	public Move play() {
		Optional<Move> oMove = Optional.empty();
		
		while(oMove.isEmpty()) {
			System.out.println("Choose a move " + moveUtils.getNames() + ":");
			String chosenMove = scanner.nextLine();
			oMove = moveUtils.getOne(chosenMove);
		}
		
		return oMove.get();
	}
	
	@Override
	public boolean wantsToPlayAgain() {

		boolean playAgain = false;

		System.out.println("Do you want to play again? (y, n):");
		String choice = scanner.nextLine();

		while(true) {
			if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")) {
				if (choice.equalsIgnoreCase("y")) {
					playAgain = true;
				}

				break;
			}

			System.out.println("Please enter 'y' for continuing or 'n' to stop.");
			choice = scanner.nextLine();
		}

		return playAgain;
	}
}

package io.everyonecodes.mongo_rockscissorspaperlizardspock.game.logic;

import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.domain.Move;
import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.player.Player;
import io.everyonecodes.mongo_rockscissorspaperlizardspock.gameresult.logic.GameResultManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Game {
	
	private final Player player1;
	private final Player player2;
	private final Judge judge;
	private final GameResultManager gameResultManager;
	
	private int player1Points;
	private int player2Points;
	private int itsATiePoints;
	
	
	public Game(Player player1, Player player2, Judge judge, GameResultManager gameResultManager) {
		this.player1 = player1;
		this.player2 = player2;
		this.judge = judge;
		this.gameResultManager = gameResultManager;
	}
	
	public void play() {
		gameResultManager.clearScore();
		
		String thanks = "Thanks for playing! =)";
		boolean playingGame = true;
		
		while(playingGame) {
			
			battleGround();
			
			playingGame = askToPlayAgain();
		}
		
		System.out.println("Final scores: " + gameResultManager.getStatistics() + "\n" + thanks);
		
	}
	
	private void battleGround() {
		System.out.println("Game statistics: " + gameResultManager.getStatistics());
		Move move1 = player1.play();
		Move move2 = player2.play();
		System.out.println("Player 1 chose: " + move1.getName());
		System.out.println("Player 2 chose: " + move2.getName());
		String resultMessage = judge.judge(move1, move2);
		System.out.println(resultMessage);
		saveResultInDatabase(resultMessage);
	}
	
	private void saveResultInDatabase(String gameResultMessage) {
		
		if(gameResultMessage.contains("1")) {
			player1Points = player1Points + 1;
		}
		
		if(gameResultMessage.contains("2")) {
			player2Points = player2Points + 1;
		}
		
		if(gameResultMessage.contains("tie")) {
			itsATiePoints = itsATiePoints + 1;
		}
		
		List<String> pointsList = new ArrayList<>(List.of(
				"Nobody wins: " + itsATiePoints,
				"\nPlayer 1 wins: " + player1Points,
				"\nPlayer 2 wins: " + player2Points));
		
		var resultsTillNow = pointsList.stream()
				.filter(s -> !s.contains("0"))
				.collect(Collectors.joining(", "));
		
		gameResultManager.save(resultsTillNow);
	}
	
	private boolean askToPlayAgain() {
		return player1.wantsToPlayAgain() && player2.wantsToPlayAgain();
	}
	
}

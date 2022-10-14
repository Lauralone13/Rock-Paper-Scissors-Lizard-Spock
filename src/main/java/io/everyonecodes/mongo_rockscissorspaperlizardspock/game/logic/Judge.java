package io.everyonecodes.mongo_rockscissorspaperlizardspock.game.logic;

import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.domain.Move;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Judge {
	
	public String judge(Move movePlayer1, Move movePlayer2) {
		List<String> defeatsOfMovePlayer1 = movePlayer1.getDefeats();
		List<String> defeatsOfMovePlayer2 = movePlayer2.getDefeats();
		
		int numberOfDefeats = defeatsOfMovePlayer1.size();
		
		String result = "";
		
		for(int i = 0; i < numberOfDefeats; i++) {
			
			if(movePlayer1.equals(movePlayer2)) {
				result = "It's a tie! Nobody won! xD";
				break;
			}
			
			if(defeatsOfMovePlayer1.get(i).contains(movePlayer1.getName()) && defeatsOfMovePlayer1.get(i).contains(movePlayer2.getName())) {
				result = "Player 1 wins " + "~" + defeatsOfMovePlayer1.get(i) + "~";
			}
			
			if(defeatsOfMovePlayer2.get(i).contains(movePlayer2.getName()) && defeatsOfMovePlayer2.get(i).contains(movePlayer1.getName())) {
				result = "Player 2 wins " + "~" + defeatsOfMovePlayer2.get(i) + "~";
			}
		}
		
		return result;
	}
}
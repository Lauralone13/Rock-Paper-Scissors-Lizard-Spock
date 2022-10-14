package io.everyonecodes.mongo_rockscissorspaperlizardspock.game.logic;

import io.everyonecodes.mongo_rockscissorspaperlizardspock.game.domain.Move;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoveUtils {

	private final List<Move> moves;
	
	public MoveUtils(List<Move> moves) {
		this.moves = moves;
	}
	
	public String getNames() {
		String moveNames = "(";
		
		for(Move move : moves) {
			String moveName = move.getName();
			String moveNumber = move.getNumber();
			moveNames = moveNames + moveName +  "(" + moveNumber + ")";
			
			if(!move.equals(moves.get(moves.size() - 1))) {
				moveNames = moveNames + ", ";
			}
		}
		
		return moveNames + ")";
	}
	
	public Optional<Move> getOne(String moveName) {
		return moves.stream().filter(move -> move.getName().equalsIgnoreCase(moveName) || move.getNumber().equals(moveName)).findFirst();
	}
	
	/*
	public static void main(String[] args) {

		MoveUtils moveUtils = new MoveUtils(List.of(new Move("test1", "1", List.of("test1, test1")), new Move("test2", "2", List.of("test2, test3")), new Move("test3", "3", List.of("test3, test1"))));
		System.out.println(moveUtils.getNames());

		System.out.println(moveUtils.getOne("test2"));
		System.out.println(moveUtils.getOne("3"));
	}

	 */
}

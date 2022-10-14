package io.everyonecodes.mongo_rockscissorspaperlizardspock.game.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Move {
	
	private String name;
	private String number;
	private List<String> defeats = new ArrayList<>();
	
	public Move() {
	}
	
	public Move(String name, String number, List<String> defeats) {
		this.name = name;
		this.number = number;
		this.defeats = defeats;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public List<String> getDefeats() {
		return defeats;
	}
	
	public void setDefeats(List<String>  defeats) {
		this.defeats = defeats;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		Move move = (Move) o;
		return Objects.equals(name, move.name) && Objects.equals(number, move.number) && Objects.equals(defeats, move.defeats);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, number, defeats);
	}
	
	@Override
	public String toString() {
		return "Move{" + "name='" + name + '\'' + ", moveNumber='" + number + '\'' + ", defeats=" + defeats + '}';
	}
}

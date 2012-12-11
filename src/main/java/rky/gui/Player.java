package rky.gui;

import java.util.ArrayList;
import java.util.List;

public class Player 
{
	private List<Double> score = new ArrayList<Double>();
	private List<Candidate> guesses = new ArrayList<Candidate>();
	private String playerName;
	private int turns_left;
	private double max_score;
	public int scrolling_index;
	public Attribute selectedAttribute;
	
	public Player(String playerName){
		this.playerName = playerName;
	}
	
	public Attribute getSelectedAttribute() {
		return selectedAttribute;
	}
	public void setSelectedAttribute(Attribute selectedAttribute) {
		this.selectedAttribute = selectedAttribute;
	}
	public List<Double> getScore() {
		return score;
	}
	public void setScore(List<Double> score) {
		this.score = score;
	}
	public List<Candidate> getGuesses() {
		return guesses;
	}
	public void setGuesses(List<Candidate> guesses) {
		this.guesses = guesses;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getTurns_left() {
		return turns_left;
	}
	public void setTurns_left(int turns_left) {
		this.turns_left = turns_left;
	}
	public double getMax_score() {
		return max_score;
	}
	public void setMax_score(double max_score) {
		this.max_score = max_score;
	}
	
	@Override
	public String toString() {
		return "Player [score=" + score + ", guesses=" + guesses
				+ ", playerName=" + playerName + ", turns_left=" + turns_left
				+ ", max_score=" + max_score + "]";
	}
}

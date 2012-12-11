package rky.gui.board;

import rky.gui.Attribute;
import rky.gui.Candidate;
import rky.gui.Controller;
import rky.gui.Player;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import rky.simpleGamePlatform.Piece;
import rky.simpleGamePlatform.RigidRectPiece;

public class PlayerBoard extends Board {

	// start co-odinates of PlayerBoard
	private int offset_x, offset_y;

	private final int width = 640, height = 480;
	private final int show_max_guess = 5;

	private Player currentPlayer;
	
	private int noOfAttributes;
	private int maxNoOfCandidates;
	
	//private List<Candidate> player_guesses = new ArrayList<Candidate>();
	//private Attribute selectedAttribute;

	private Candidate ideal = new Candidate();
	private int border = 10;
	private int padding = 10;
	private RigidRectPiece submitButton = new RigidRectPiece();
	private RigidRectPiece next;
	private RigidRectPiece prev;
	private int offset_for_strings = 0;

	//public int scrolling_index = 0;

	boolean isGameRunning = false;
	boolean isGameOver = false;

	//List<Double> score = new ArrayList<Double>();

	Color colr = new Color(0, 255, 255);

	Font font = new Font("Helvetica", Font.BOLD, 16);
	Font headerFont = new Font("Verdana", Font.BOLD, 20);

	int attribute_display_width = 25;
	int attribute_display_height = 25;

	Controller applet;
	boolean isScrollingEnabled = false;

	
	public Player getPlayerName() {
		return currentPlayer;
	}

	public void setPlayerName(Player player) {
		this.currentPlayer = player;
	}

	public int getNoOfAttributes() {
		return noOfAttributes;
	}

	public void setNoOfAttributes(int noOfAttributes) {
		this.noOfAttributes = noOfAttributes;
	}

	public int getMaxNoOfCandidates() {
		return maxNoOfCandidates;
	}

	public void setMaxNoOfCandidates(int maxNoOfCandidates) {
		this.maxNoOfCandidates = maxNoOfCandidates;
	}

	public List<Candidate> getPlayer_guesses() {
		return currentPlayer.getGuesses();
	}

	public void setPlayer_guesses(List<Candidate> player_guesses) {
		this.currentPlayer.setGuesses(player_guesses);
	}

	public Attribute getSelectedAttribute() {
		return currentPlayer.getSelectedAttribute();
	}

	public void setSelectedAttribute(Attribute selectedAttribute) {
		this.currentPlayer.setSelectedAttribute(selectedAttribute);
	}
	
	public int getOffset_x() {
		return offset_x;
	}

	public void setOffset_x(int offset_x) {
		this.offset_x = offset_x;
	}

	public int getOffset_y() {
		return offset_y;
	}

	public void setOffset_y(int offset_y) {
		this.offset_y = offset_y;
	}
	
	public int getOffset_for_strings() {
		return offset_for_strings;
	}

	public void setOffset_for_strings(int offset_for_strings) {
		this.offset_for_strings = offset_for_strings;
	}

	public List<Double> getScore() {
		return currentPlayer.getScore();
	}

	public void setScore(List<Double> score) {
		this.currentPlayer.setScore(score);
	}

	public PlayerBoard(int x, int y, Controller applet,Player player) {
		this.offset_x = x;
		this.offset_y = y;
		this.applet = applet;
		this.currentPlayer = player;
	}

	@Override
	public void init() {
		attribute_display_width = (int) (10 * 25 / noOfAttributes);
		attribute_display_height = (int) (10 * 25 / noOfAttributes);

	}

	private void setIdealCandidate() {
		ideal = createNewCandidate(noOfAttributes);
		showCandidate(ideal, offset_x + width - border - padding * 15
				- attribute_display_width, false);
		currentPlayer.selectedAttribute = ideal.getAttributes().get(0);
	}

	@Override
	public void pieceClicked(Piece p) {
		if (p == submitButton){
			
			pushToHistory(ideal);
			applet.updatePlayerMove();
		}
		if (p == next) {
			nextButtonClicked();
		}
		if (p == prev) {
			previousButtoClicker();
		}
	}

	private void previousButtoClicker() {
		if (currentPlayer.scrolling_index+show_max_guess < currentPlayer.getGuesses().size()) {
			currentPlayer.scrolling_index++;
			refreshAllGusses(false);
		}
	}

	private void nextButtonClicked() {

		if (currentPlayer.scrolling_index > 0) {
			currentPlayer.scrolling_index--;
			refreshAllGusses(false);
		}

	}

	private void refreshAllGusses(boolean isAutoScroll) {

		int candidate_id = 0;
		int column = 0;
		int start_candidate = currentPlayer.getGuesses().size() - 1;

		if (currentPlayer.getGuesses().size() > show_max_guess) {

			if (isAutoScroll)
				currentPlayer.scrolling_index++;
			candidate_id = currentPlayer.scrolling_index;
			start_candidate = show_max_guess - 1;
		}

		for (int i = start_candidate; i >= 0; i--) {
			Candidate c1 = currentPlayer.getGuesses().get(candidate_id++);
			column = offset_x + width - border - padding * 25 - i
			* (attribute_display_width + 40) - attribute_display_width;

			if (i == currentPlayer.getGuesses().size() - 1) {
				offset_for_strings = column - 70;
			}
			showCandidate(c1, column, true);
		}
	}

	public boolean keyDown(Event e, int key) {
		if (key == 1004) {
			selectPrevAttribute();
			return true;
		} else if (key == 1005) {
			selectNextAttribute();
			return true;
		} else if (key == 1006) {
			reduceGradient();
			return true;
		} else if (key == 1007) {
			increaseGraditent();
			return true;
		}

		return false;
	}

	private void increaseGraditent() {
		currentPlayer.selectedAttribute.getMaterial().increaseGradient();
	}

	private void reduceGradient() {
		currentPlayer.selectedAttribute.getMaterial().reduceGradient();
	}

	private void selectNextAttribute() {
		int selectedIndex = ideal.getAttributes().indexOf(currentPlayer.selectedAttribute);
		selectedIndex = (selectedIndex + 1) % ideal.getAttributes().size();
		currentPlayer.selectedAttribute = ideal.getAttributes().get(selectedIndex);
	}

	private void selectPrevAttribute() {
		int selectedIndex = ideal.getAttributes().indexOf(currentPlayer.selectedAttribute);
		if (selectedIndex == 0) {
			selectedIndex = ideal.getAttributes().size() - 1;
		} else {
			selectedIndex = (selectedIndex - 1);
		}
		currentPlayer.selectedAttribute = ideal.getAttributes().get(selectedIndex);
	}

	@Override
	public void update() 
	{
		for (int i = 0; i < ideal.getAttributes().size(); i++) {
			if (ideal.getAttributes().get(i) == currentPlayer.selectedAttribute) {
				currentPlayer.selectedAttribute.setBorderColor(Color.red);
			} else {
				ideal.getAttributes().get(i).setBorderColor(Color.black);
			}
		}

		if (isScrollingEnabled) {
			if (next == null) {
				next = new RigidRectPiece();
				next.setBounds(offset_x + 45, offset_y + 450, 60, 20);
				next.setColor(Color.gray);
				next.delegate = applet;
				applet.addPiece(next);
			}

			if (prev == null) {
				prev = new RigidRectPiece();
				prev.setBounds(offset_x + 310, offset_y + 450, 60, 20);
				prev.setColor(Color.gray);
				prev.delegate = applet;
				applet.addPiece(prev);
			}
		}

	}

	@Override
	public void drawOverlay(Graphics g) {
		g.setFont(headerFont);
		g.setColor(Color.BLUE);
		g.drawString(currentPlayer.getPlayerName(), offset_x + width / 2 - 90, offset_y + 20);

		if(isGameRunning){
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("Submit", offset_x + 510 + 20, offset_y + 80 + 20);

			if (isScrollingEnabled) {
				g.setFont(font);
				g.setColor(Color.black);
				g.drawString("Prev", offset_x + 45 + 10, offset_y + 450 + 15);

				g.setFont(font);
				g.setColor(Color.black);
				g.drawString("Next", offset_x + 310 + 10, offset_y + 450 + 15);
			}

			if (offset_for_strings != 0) {

				for (int i = 0; i < noOfAttributes; i++) {
					g.setFont(font);
					g.setColor(Color.black);
					g.drawString("A" + (i + 1), offset_for_strings + 15, offset_y
							+ i * (attribute_display_height + padding) + border
							+ padding * 7);
				}

				int startIndex = 0;
				int endIndex = currentPlayer.getGuesses().size();
				if(currentPlayer.getGuesses().size()>5){
					startIndex = currentPlayer.scrolling_index;
					endIndex = currentPlayer.scrolling_index + 5;
				}

				for (int i = startIndex; i < endIndex; i++) {
					g.setFont(font);
					g.setColor(Color.black);
					g.drawString("C" + (i + 1), offset_for_strings + (i-startIndex)
							* (attribute_display_width + padding * 3 + border)
							+ border + padding * 5, offset_y + 45);

				}

				for (int i = startIndex; i < endIndex; i++) {
					g.setFont(font);
					g.setColor(Color.black);
					if(i<currentPlayer.getScore().size()){
						g.drawString(currentPlayer.getScore().get(i) + "", offset_for_strings + (i-startIndex)
								* (attribute_display_width + padding * 3 + border)
								+ border + padding * 5, offset_y + 420 + 15);
					}

				}

				g.setFont(font);
				g.setColor(Color.red);
				g.drawString("Score:", offset_for_strings + 5, offset_y + 420 + 15);
				g.setColor(Color.black);
			}
		}

	}

	public void pushToHistory(Candidate c) {

		currentPlayer.getGuesses().add(c);
		// clear borders
		for (int i = 0; i < c.getAttributes().size(); i++) {
			c.getAttributes().get(i).setBorderColor(Color.black);
		}

		refreshAllGusses(true);

		if (currentPlayer.getGuesses().size() > 5) {
			isScrollingEnabled = true;
		}
		setIdealCandidate();
	}

	private Candidate createNewCandidate(int size_attributes) {

		Candidate newCandidate = new Candidate();

		for (int i = 0; i < size_attributes; i++) {
			Attribute p = new Attribute();
			adjustGradient(p,applet.getGameLevel());
			newCandidate.getAttributes().add(p);
		}
		return newCandidate;
	}

	private void adjustGradient(Attribute p, Level gameLevel) 
	{
		if(gameLevel == Level.Easy){
			p.getMaterial().setStep(255);
		}else if(gameLevel == Level.Medium){
			p.getMaterial().setStep(127);
		}else if(gameLevel == Level.Hard){
			p.getMaterial().setStep(7);
		}
	}

	private void showCandidate(Candidate candidate, int startPosition,boolean showGrid)
	{
		if (!showGrid) 
		{
			for (int i = 0; i < candidate.getAttributes().size(); i++) {
				Attribute p = candidate.getAttributes().get(i);
				p.setBounds(startPosition, offset_y + i
						* (attribute_display_height + padding) + border
						+ padding * 5, attribute_display_height,
						attribute_display_height);
				p.setColor(p.getColor());
				applet.addPiece(p);
			}
		} else {

			for (int i = 0; i < candidate.getAttributes().size(); i++) {
				Attribute p = candidate.getAttributes().get(i);

				int x = startPosition;
				int y = offset_y + i * (attribute_display_width + padding)
							+ border + padding * 5;
				
				int width = attribute_display_width;
				int height = attribute_display_height;

				RigidRectPiece grid = new RigidRectPiece();
				grid.setBounds(x - 20, y - 5, width + 40, height + 10);
				grid.setColor(Color.white);
				applet.addPiece(grid);

				p.setBounds(x, y, width, height);
				p.setColor(p.getColor());
				applet.addPiece(p);
			}
		}
	}

	@Override
	public void stop() {

	}

	@Override
	public void start() {
		setIdealCandidate();

		// submit button
		submitButton.setBounds(offset_x + 510, offset_y + 80, 100, 30);
		submitButton.setColor(Color.red);
		submitButton.delegate = applet;
		applet.addPiece(submitButton);	
		
		isGameRunning = true;
	}

	public void updateScore(double score) {
		this.currentPlayer.getScore().add(score);
	}

	public void setPlayer(Player player) 
	{
		//removeCurrentPlayer();
		currentPlayer = player;
	}

	private void removeCurrentPlayer() {
		
		for(int i=0; i<currentPlayer.getGuesses().size(); i++){
			Candidate c = currentPlayer.getGuesses().get(i);
			for(int j = 0 ; j< c.getAttributes().size();j++)
			applet.removePiece(c.getAttributes().get(j));
		}
	}
}

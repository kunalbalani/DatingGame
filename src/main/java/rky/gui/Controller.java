package rky.gui;

import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.URL;

import rky.dating.Dating;
import rky.gui.board.Level;
import rky.gui.board.Mode;
import rky.gui.board.NavigationBoard;
import rky.gui.board.PlayerBoard;
import rky.gui.board.ScoreBoard;
import rky.primitives.Candidate;
import rky.simpleGamePlatform.GamePlatform;
import rky.simpleGamePlatform.Piece;
import rky.simpleGamePlatform.RigidRectPiece;

//start applet with size 940 X 720
@SuppressWarnings("serial")
public class Controller extends GamePlatform {
	int width, height;

	ScoreBoard score_board;
	public NavigationBoard nav_board;
	public PlayerBoard player_board;

	public int max_no_candidates = 20;
	int max_no_attributes = 10;
	Mode mode;
	Level gameLevel;

	Player currentTurn;
	public Player player1, player2;

	rky.gui.Candidate lastCandidate;

	MediaTracker mt;
	URL base;

	public boolean isGameRunning = false;

	int candidate_counter = 2;

	public void setup() {
		width = getWidth();
		height = getHeight();

		// add borders
		RigidRectPiece v_border = new RigidRectPiece();
		v_border.setBounds(300, 0, 3, 780);
		addPiece(v_border);

		RigidRectPiece h_border = new RigidRectPiece();
		h_border.setBounds(300, 200, 640, 3);
		addPiece(h_border);

		score_board = new ScoreBoard(300, -50, this);
		score_board.init();

		nav_board = new NavigationBoard(this);
		nav_board.init();

		player1 = new Player("Player 1");
		player_board = new PlayerBoard(300, 210, this, player1);
		player_board.setNoOfAttributes(max_no_attributes);
		player_board.init();

	}

	public Image intializeImage(String imgName) {
		// initialize the MediaTracker
		mt = new MediaTracker(this);

		try {
			// getDocumentbase gets the applet path.
			base = getCodeBase();
			String path = base.toString();
			// System.out.println(path);

			// String folders[] = path.split("/");
			// path = "";
			// for(int i = 0 ; i<folders.length-1; i++){
			// path +=(folders[i]+"/");
			// }
			path += "rky/resources/Images/";
			// System.out.println(path);
			base = new URL(path);

		} catch (Exception e) {
		}

		// Here we load the image.
		Image img = getImage(base, imgName);
		mt.addImage(img, 0);

		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
		}

		return img;
	}

	public void pieceClicked(Piece p) {
		player_board.pieceClicked(p);
		nav_board.pieceClicked(p);
		score_board.pieceClicked(p);
	}

	public boolean keyDown(Event e, int key) {
		return player_board.keyDown(e, key);
	}

	public void update() {
		player_board.update();
		score_board.update();
		nav_board.update();
	}

	public void overlay(Graphics g) {
		player_board.drawOverlay(g);
		score_board.drawOverlay(g);
		nav_board.drawOverlay(g);
	}

	public int getMax_no_candidates() {
		return max_no_candidates;
	}

	public void setMax_no_candidates(int max_no_candidates) {
		this.max_no_candidates = max_no_candidates;
	}

	public int getMax_no_attributes() {
		return max_no_attributes;
	}

	public void setMax_no_attributes(int max_no_attributes) {
		this.max_no_attributes = max_no_attributes;
	}

	// callbacks from Navigation Bar
	public void startGame() {
		isGameRunning = true;

		Dating.applet = this;

		if (mode == Mode.SinglePlayer) {
			Dating.setNo_of_candidates(max_no_candidates);
		} else {
			player2 = new Player("Player 2");
			Dating.setNo_of_candidates(2 * max_no_candidates);
		}
		player_board.setNoOfAttributes(max_no_attributes);

		Thread thread = new Thread() {
			public void run() {
				Dating.runGame(max_no_attributes);
			}
		};

		thread.start();

		currentTurn = player1;
		player_board.start();
		score_board.start();
		nav_board.start();
	}

	public void displayGameOver() {
		if(player2 != null)
			new ShowDialogBox(getMaxScore(player1), getMaxScore(player2));
		else{
			new ShowDialogBox(getMaxScore(player1), -1);
		}
		stopGame();
	}

	public void stopGame() {
		isGameRunning = false;
		player_board.stop();
		score_board.stop();
		nav_board.stop();
		this.setup();
	}

	public void setScoreForSelectedAttrbiute(int score){
		if(player_board.currentPlayer.selectedAttribute != null){
			player_board.currentPlayer.selectedAttribute.getMaterial().setValue(score*2.5);
		}
	}

	public double getMaxScore(Player player) {

		double retVal = -1;
		for (int i = 0; i < player.getScore().size(); i++) {
			if (player.getScore().get(i) > retVal)
				retVal = player.getScore().get(i);
		}

		return retVal;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	// public void restartGame(){
	// stopGame();
	// startGame();
	// }

	// calbacks from Player Board
	public void updatePlayerMove() {
		if (player_board.getPlayer_guesses().size() > 0) {
			lastCandidate = player_board.getPlayer_guesses().get(
					player_board.getPlayer_guesses().size() - 1);
		}
		if (mode == Mode.Mutiplayer)
			switchTurn();

	}

	public rky.primitives.Candidate getCandidateFromM() {

		while (lastCandidate == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		rky.primitives.Candidate returnValue = getCandidate(lastCandidate);
		lastCandidate = null;

		return returnValue;
	}

	private rky.primitives.Candidate getCandidate(
			rky.gui.Candidate lastCandidate2) {

		rky.primitives.Candidate candidate = new Candidate(
				player_board.getNoOfAttributes());

		for (int i = 0; i < player_board.getNoOfAttributes(); i++)
			candidate.set(i, lastCandidate2.getAttributes().get(i).getValue());

		return candidate;
	}

	public void updateScore(double score) {

		if (currentTurn == player1) {

			if (mode == Mode.Mutiplayer)
				player_board.updateScore(score, player2);
			else
				player_board.updateScore(score, player1);

			score_board.updateScore(score, Color.blue, candidate_counter / 2);

		} else {
			player_board.updateScore(score, player1);
			score_board.updateScore(score, Color.red, candidate_counter / 2);
		}
		if (mode == Mode.Mutiplayer) {
			candidate_counter++;
		} else {
			candidate_counter = candidate_counter + 2;
		}
	}

	public void switchTurn() {
		if (currentTurn == player1) {
			currentTurn = player2;
		} else {
			currentTurn = player1;
		}
		player_board.setPlayer(currentTurn);
	}

	public Level getGameLevel() {
		return gameLevel;
	}

	public void setGameLevel(Level gameLevel) {
		this.gameLevel = gameLevel;
	}

	public Mode getMode() {
		return mode;
	}
}

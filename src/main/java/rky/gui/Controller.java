
package rky.gui;


import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.URL;

import rky.gui.board.Level;
import rky.gui.board.Mode;
import rky.gui.board.NavigationBoard;
import rky.gui.board.PlayerBoard;
import rky.gui.board.ScoreBoard;
import rky.player.Player;
import rky.primitives.Candidate;
import rky.simpleGamePlatform.GamePlatform;
import rky.simpleGamePlatform.Piece;
import rky.simpleGamePlatform.RigidRectPiece;

//start applet with size 940 X 780
public class Controller extends GamePlatform
{		
	int width,height;

	ScoreBoard score_board;
	NavigationBoard nav_board;
	PlayerBoard player_board;

	int max_no_candidates = 20;
	int max_no_attributes = 10;
	Mode mode;
	Level gameLevel;
	
	

	Player player1,player2;

	rky.gui.Candidate lastCandidate;

	MediaTracker mt;
	URL base;

	boolean isGameRunning = false;
	boolean isGameOver = false;

	public void setup() 
	{
		width = getWidth();
		height = getHeight();

		//add borders
		RigidRectPiece v_border = new RigidRectPiece();
		v_border.setBounds(300, 0,3,780);
		addPiece(v_border);

		RigidRectPiece h_border = new RigidRectPiece();
		h_border.setBounds(300,260,640,3);
		addPiece(h_border);

		score_board = new ScoreBoard(300,0,this);
		score_board.init();

		nav_board = new NavigationBoard(this);
		nav_board.init();

		player_board = new PlayerBoard(300,270,this);
		player_board.setMaxNoOfCandidates(max_no_candidates);
		player_board.setNoOfAttributes(max_no_attributes);
		player_board.init();

		//		Dating.applet = this;
		//		
		//		Thread thread = new Thread(){
		//		    public void run(){
		//				Dating.runGame(max_no_attributes);
		//		    }
		//		  };
		//		 
		//		  thread.start();

	}

	public Image intializeImage(String imgName)
	{
		// initialize the MediaTracker 
		mt = new MediaTracker(this);


		try { 
			// getDocumentbase gets the applet path. 
			base = getCodeBase();
			String path = base.toString();
			System.out.println(path);

//			String folders[] = path.split("/");
//			path = "";
//			for(int i = 0 ; i<folders.length-1; i++){
//				path +=(folders[i]+"/");
//			}
			path += "rky/resources/Images/";
			System.out.println(path);
			base = new URL(path);

		} 
		catch (Exception e) {}

		// Here we load the image. 
		Image img = getImage(base,imgName);
		mt.addImage(img,0);

		try 
		{ 
			mt.waitForAll(); 
		} 
		catch (InterruptedException  e) {}

		return img;
	}

	public void pieceClicked(Piece p)
	{
		player_board.pieceClicked(p);
		nav_board.pieceClicked(p);
		score_board.pieceClicked(p);
	}

	public boolean keyDown(Event e, int key) 
	{
		return player_board.keyDown(e, key);	
	}

	public void update()
	{
		player_board.update();
		score_board.update();
		nav_board.update();
	}

	public void overlay(Graphics g)
	{
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

	//callbacks from Navigation Bar
	public void startGame()
	{
		isGameRunning = true;
		player_board.start();
		score_board.start();
		nav_board.start();
	}

	public void stopGame()
	{

	}

	public void setPlayers(Player one, Player two)
	{

	}

	public void setMode(Mode mode){

	}

	public void restartGame(){
		stopGame();
		startGame();
	}

	//calbacks from Player Board
	public void updatePlayerMove(){
		if(player_board.getPlayer_guesses().size() > 0){
			lastCandidate = player_board.getPlayer_guesses().
			get(player_board.getPlayer_guesses().size()-1);
		}
	}

	public rky.primitives.Candidate getCandidateFromM(){

		while(lastCandidate == null){
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

	private rky.primitives.Candidate getCandidate(rky.gui.Candidate lastCandidate2) {

		rky.primitives.Candidate candidate = new Candidate( player_board.getNoOfAttributes() );

		for( int i = 0; i < player_board.getNoOfAttributes(); i++ )
			candidate.set(i, lastCandidate2.getAttributes().get(i).getValue());

		return candidate;
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



package rky.gui;


import rky.gui.board.PlayerBoard;
import rky.gui.board.ScoreBoard;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.URL;

import rky.dating.Dating;

import rky.primitives.Candidate;

import rky.simpleGamePlatform.GamePlatform;
import rky.simpleGamePlatform.Piece;
import rky.simpleGamePlatform.RigidRectPiece;

//start applet with size 940 X 780
@SuppressWarnings("serial")
public class Controller extends GamePlatform
{		
	int width,height;

	ScoreBoard score_board;
	PlayerBoard player_board;
	
	int max_no_candidates = 20;
	int max_no_attributes = 10;
	int p1_turns_left = 20;
	int p2_turns_left = 20;
	
	rky.gui.Candidate lastCandidate;

	MediaTracker mt;
	URL base;

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

		player_board = new PlayerBoard(300,270,this);
		player_board.setMaxNoOfCandidates(max_no_candidates);
		player_board.setNoOfAttributes(max_no_attributes);
		player_board.init();
		
		Dating.applet = this;
		
		Thread thread = new Thread(){
		    public void run(){
				Dating.runGame(max_no_attributes);
		    }
		  };
		 
		  thread.start();
		
	}

	Image intializeImage(String imgName)
	{
		// initialize the MediaTracker 
		mt = new MediaTracker(this);


		try { 
			// getDocumentbase gets the applet path. 
			base = getDocumentBase();
			//	String path = base.toString();
			//	System.out.println(path);
			//
			//	String folders[] = path.split("/");
			//	path = "";
			//	for(int i = 0 ; i<folders.length-1; i++){
			//		path +=(folders[i]+"/");
			//	}
			//	path += "Resources/";
			//	//System.out.println(path);
			//	base = new URL(path);

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
	}

	public boolean keyDown(Event e, int key) 
	{
		return player_board.keyDown(e, key);	
	}

	public void update()
	{
		player_board.update();
		score_board.update();
	}

	public void overlay(Graphics g)
	{
		player_board.drawOverlay(g);
		score_board.drawOverlay(g);
	}

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
}


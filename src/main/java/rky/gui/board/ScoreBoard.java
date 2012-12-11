package rky.gui.board;

import rky.gui.Controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import rky.simpleGamePlatform.Piece;
import rky.simpleGamePlatform.RigidRectPiece;


public class ScoreBoard extends Board {

	private Font bigFont = new Font("Helvetica", Font.ITALIC, 18);


	private List<RigidRectPiece>  bars = new ArrayList<RigidRectPiece>();
	private Set<RigidRectPiece> setOfBars = new TreeSet<RigidRectPiece>();
	private Line topScale;

	Controller applet;
	int xOffset, yOffset;
	
	public ScoreBoard(int xOffset,int yOffset,Controller applet)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.applet = applet;
	}

	@Override
	public void update() 
	{
		for(RigidRectPiece p :bars){			
			if(!setOfBars.contains(p)){
				applet.addPiece(p);
				setOfBars.add(p);
			}
		}
	}

	private void createGraph(Graphics2D g2)
	{
		g2.setFont(bigFont);
		g2.setColor(Color.RED);

		// Render a string using the derived font
		g2.drawString("Bad Match",xOffset+ 20, 70+yOffset);
		g2.setColor(Color.BLACK);
		g2.drawString("-1",xOffset+20, 230+yOffset);

		g2.setColor(Color.BLUE);
		g2.drawString("Ideal Match",xOffset+ 500, 70+yOffset);
		g2.setColor(Color.BLACK);
		g2.drawString("+1",xOffset+570, 230+yOffset);

		g2.drawLine(xOffset+300,210+yOffset,xOffset+300,230+yOffset);
		g2.drawString("0",xOffset+280, 245+yOffset);

		g2.setStroke(new BasicStroke(3));

		topScale = new Line(xOffset+50,220+yOffset,xOffset+550,220+yOffset);
		g2.drawLine(xOffset+50,220+yOffset,xOffset+550,220+yOffset);

	}

	private void addNewBar(Line l,double score,Color color){

		int barX = 500;
		int barY = l.start.y - 60;

		score += 1; //convert score from 0-2

		double place = score *l.getLength()/2;

		barX =(int)( place + 50 - 5);

		//System.out.println("x="+barX+"y="+barY);
		RigidRectPiece bar = new RigidRectPiece();
		bar.setBounds(xOffset+ barX,yOffset+barY, 10, 60);
		bar.setColor(color);
		applet.addPiece(bar);

		bars.add(bar);
	}

	public void updateScore(double score,Color color){

		double value = score;
		score = Math.round( value * 100.0 ) / 100.0;

		if(topScale != null){
			addNewBar(topScale, score,color);
		}
	}

	@Override
	public void stop() 
	{
		
	}

	@Override
	public void drawOverlay(Graphics g) {

		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D) g;
		createGraph(g2);		
	}

	@Override
	public void init() {
		
	}

	@Override
	public void pieceClicked(Piece p) {
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

}

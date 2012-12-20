package rky.gui.board;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rky.gui.Controller;
import rky.simpleGamePlatform.Piece;
import rky.simpleGamePlatform.RectPiece;
import rky.simpleGamePlatform.RigidRectPiece;

public class NavigationBoard extends Board {

	StopWatch sw = new StopWatch();
	Controller applet;

	RigidRectPiece startButton,stopButton;
	RigidRectPiece hand;
	RectPiece  pointer;
	int time=0;

	CheckboxGroup mode_radio_button;
	CheckboxGroup level_radio_button;
	List<Checkbox> allBoxes = new ArrayList<Checkbox>();

	TextField attributes;
	TextField candidates;
	Font font = new Font("Helvetica", Font.BOLD, 16);

	Label error = new Label();

	public NavigationBoard(Controller applet)
	{
		this.applet = applet;
	}

	@Override
	public void init()
	{
		//add a start button
		startButton = new RigidRectPiece();
		startButton.setBounds(150,260, 130, 45);
		Image startImage = applet.intializeImage("startButton.png");
		startButton.setImage(startImage);
		startButton.delegate = applet;
		applet.addPiece(startButton);

		hand = new RigidRectPiece();
		hand.setBounds(10,250, 90, 30);
		hand.setImage(applet.intializeImage("pointer-finger-white.png"));
		hand.setBorderColor(Color.white);
		applet.addPiece(hand);

		addModeCheckBox();
		addLevelOptions();
		addAttributesTF();
		addGuage();
	}

	private void addGuage() {
		int startOffsetx = 20;
		int startOffsety =	620;
		int allbarsheight = 30;
		
		for(int i =0 ; i<255;i++){
			
			RigidRectPiece p = new RigidRectPiece();
			p.setBorderColor(Color.white);
			p.setBounds(startOffsetx+i,startOffsety, 1, allbarsheight);
			p.setBorderColor(new Color(i,255-i,255-i));
			applet.addPiece(p);
			
		}
		setGuage(0);
	}
	
	public void setGuage(double score){
		
		if(pointer == null){
			pointer = new RectPiece();
			pointer.setBorderColor(Color.white);
			pointer.delegate = applet;
			pointer.setImage(applet.intializeImage("pointer-finger-Up.png"));
			applet.addPiece(pointer);
		}
		double pointAt = score*2.5;
		pointer.setBounds(pointAt+10,665,40,40);
	}

	private void addAttributesTF() 
	{
		Label attributes_label = new Label("#Attributes:");
		attributes_label.setLocation(10,180);
		attributes_label.setSize(100, 20);
		attributes_label.setBackground(Color.white);
		applet.add(attributes_label);

		attributes = new TextField(2);
		attributes.setLocation(110,180);
		attributes.setSize(60, 20);
		attributes.setText("15");
		attributes.setBackground(Color.white);
		attributes.setEnabled(false);
		applet.setLayout(null);
		applet.add(attributes);

		Label can_label = new Label("#Candidates:");
		can_label.setLocation(10,210);
		can_label.setSize(100, 20);
		can_label.setBackground(Color.white);
		applet.add(can_label);


		error = new Label("*Must be > 0");
		error.setLocation(170,210);
		error.setSize(100, 20);
		error.setBackground(Color.white);
		error.setForeground(Color.red);
		error.setVisible(false);
		applet.add(error);

		candidates = new TextField(2);
		candidates.setLocation(110,210);
		candidates.setSize(60, 20);
		candidates.setText("20");
		candidates.setBackground(Color.white);
		applet.add(candidates);
	}

	private void addLevelOptions() 
	{
		Label level_label = new Label("Select Level:");
		level_label.setLocation(10,100);
		level_label.setSize(80, 20);
		level_label.setBackground(Color.white);
		applet.add(level_label);

		level_radio_button = new CheckboxGroup();
		int startPosition = 80;

		FocusListener changeListener = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				Checkbox selectedBox = (Checkbox)e.getSource();
				if(selectedBox.getLabel().contains("Hard")){
					attributes.setText("15");
					candidates.setText("20");
				}else if(selectedBox.getLabel().contains("Medium")){
					attributes.setText("10");
					candidates.setText("15");
				}else if(selectedBox.getLabel().contains("Easy")){
					attributes.setText("5");
					candidates.setText("10");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		};

		for(int i=0;i<3;i++){

			String level = "";
			if(i ==0){
				level = "Hard";
			}else if(i ==1){
				level = "Medium";
			}else if(i ==2){
				level = "Easy";
			}

			Checkbox leve = new Checkbox(level,level_radio_button,false);
			leve.setLocation(100, startPosition);
			leve.setSize(120, 20);
			if(i ==0){
				leve.setState(true);
			}
			leve.addFocusListener(changeListener);
			leve.setBackground(Color.white);
			allBoxes.add(leve);
			applet.add(leve);

			startPosition+=20;
		}
	}



	private void addModeCheckBox() 
	{
		Label mode_label = new Label("Select Mode:");
		mode_label.setLocation(10,20);
		mode_label.setSize(80, 20);
		mode_label.setBackground(Color.white);
		applet.add(mode_label);

		mode_radio_button = new CheckboxGroup();
		Checkbox mode1 = new Checkbox("Single Player",mode_radio_button,false);
		mode1.setLocation(100, 20);
		mode1.setSize(120, 20);
		mode1.setState(true);
		mode1.setBackground(Color.white);
		allBoxes.add(mode1);
		applet.add(mode1);

		Checkbox mode2 = new Checkbox("Two Players",mode_radio_button,false);
		mode2.setLocation(100, 40);
		mode2.setSize(120, 20);
		mode2.setBackground(Color.white);
		allBoxes.add(mode2);
		applet.add(mode2);		

	}

	public void setAppletState()
	{

		String s = attributes.getText();
		try{
			int no_of_attributes = Integer.parseInt(s);
			applet.setMax_no_attributes(no_of_attributes);

		}catch (Exception e) {}

		try{
			s = candidates.getText();
			int no_of_cand = Integer.parseInt(s);
			applet.max_no_candidates = no_of_cand;

		}catch (Exception e) {}

		setValue(mode_radio_button.getSelectedCheckbox());
		setValue(level_radio_button.getSelectedCheckbox());
	}


	private void setValue(Checkbox currentCheckbox) {
		boolean checkboxState = currentCheckbox.getState();
		if (currentCheckbox.getLabel().contains("Single"))
			if (checkboxState)
			{
				applet.setMode(Mode.SinglePlayer);
			}
		if (currentCheckbox.getLabel().contains("Two"))
			if (checkboxState)
			{
				applet.setMode(Mode.Mutiplayer);
			}
		if (currentCheckbox.getLabel() == "Hard")
			if (checkboxState)
			{
				applet.setGameLevel(Level.Hard);
			}
		if (currentCheckbox.getLabel() == "Medium")
			if (checkboxState)
			{
				applet.setGameLevel(Level.Medium);
			}
		if (currentCheckbox.getLabel() == "Easy")
			if (checkboxState)
			{
				applet.setGameLevel(Level.Easy);
			}			
	}

	@Override
	public void stop() {

		sw = new StopWatch();
		
		startButton.setBounds(0,0, 0, 0);
		applet.removePiece(startButton);
		applet.removePiece(pointer);
		

		candidates.setEnabled(true);

		//diable all checkboxes
		for(int i =0 ;i <allBoxes.size();i++){
			allBoxes.get(i).setEnabled(true);
			applet.remove(allBoxes.get(i));
		}
		
		applet.remove(candidates);
		applet.remove(attributes);
	}

	@Override
	public void drawOverlay(Graphics g) {
		
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString("Player 1: "+applet.getMaxScore(applet.player1),20,350);
		if(applet.getMode() == Mode.Mutiplayer){
			g.setColor(Color.blue);
			g.drawString("Player 2: "+applet.getMaxScore(applet.player2),20,370);
		}

		g.setFont(new Font("Helvetica", Font.BOLD, 20));
		g.setColor(Color.black);
		g.drawString("Elapsed time:", 30, 525);
		g.drawString(getTime(sw.getElapsedTime()), 30, 550);
		
		//score board
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString("Gradient Meter ", 30, 610);
		g.drawString("0", 20, 670);
		g.drawString("0.5", (145), 670);
		g.drawString("1", 270, 670);

	}

	@Override
	public void update() 
	{
		hand.setBounds(20+Math.sin(time++)*5,270, 100, 45);
	}	

	@Override
	public void pieceClicked(Piece p) {
		if(p == startButton){

			if(!applet.isGameRunning){

				setAppletState();
				if(isValid()){
					applet.startGame();
					error.setVisible(false);
				}
				else{
					showError();
				}

			}else{
				applet.stopGame();
			}
		}

	}

	private void showError() {
		error.setVisible(true);
	}

	private boolean isValid() {
		try{
			if(Integer.parseInt(candidates.getText()) > 0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void start() {
		
		sw.start();

		applet.removePiece(hand);
		startButton.setBounds(180,260, 80, 80);
		startButton.setBorderColor(Color.white);
		startButton.setImage(applet.intializeImage("restart_button.png"));

		candidates.setEnabled(false);

		//diable all checkboxes
		for(int i =0 ;i <allBoxes.size();i++){
			allBoxes.get(i).setEnabled(false);
		}
		applet.isGameRunning = true;
	}

	private String getTime(long millis){

		return String.format("%d min, %d sec", 
				TimeUnit.MILLISECONDS.toMinutes(millis),
				TimeUnit.MILLISECONDS.toSeconds(millis) - 
				TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
		);

	}
}

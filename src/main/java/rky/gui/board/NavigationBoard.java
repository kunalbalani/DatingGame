package rky.gui.board;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rky.gui.Controller;
import rky.simpleGamePlatform.Piece;
import rky.simpleGamePlatform.RigidRectPiece;

public class NavigationBoard extends Board {

	StopWatch sw = new StopWatch();
	Controller applet;

	RigidRectPiece startButton,stopButton;
	RigidRectPiece hand;
	int time=0;

	CheckboxGroup mode_radio_button;
	CheckboxGroup level_radio_button;

	TextField attributes;
	TextField candidates;


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
		hand.setBounds(10,250, 130, 40);
		hand.setImage(applet.intializeImage("pointer-finger-white.png"));
		hand.setBorderColor(Color.white);
		applet.addPiece(hand);

		addModeCheckBox();
		addLevelOptions();
		addAttributesTF();
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
		applet.add(mode1);

		Checkbox mode2 = new Checkbox("Two Players",mode_radio_button,false);
		mode2.setLocation(100, 40);
		mode2.setSize(120, 20);
		mode2.setBackground(Color.white);
		applet.add(mode2);		

	}

	public void setAppletState()
	{

		String s = attributes.getText();
		try{
			int no_of_attributes = Integer.parseInt(s);
			applet.setMax_no_attributes(no_of_attributes);

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

	}

	@Override
	public void drawOverlay(Graphics g) {

	}

	@Override
	public void update() {
		hand.setBounds(10+Math.sin(time++)*5,250, 130, 60);
	}	

	@Override
	public void pieceClicked(Piece p) {
		if(p == startButton){
			//TODO disable start button
			setAppletState();
			if(isValid())
				applet.startGame();
			else{
				showError();
			}
		}
	}

	private void showError() {
		// TODO Auto-generated method stub

	}

	private boolean isValid() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void start() {
		//TODO disable all radio button
		sw.start();
	}
}

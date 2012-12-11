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

	public NavigationBoard(Controller applet)
	{
		this.applet = applet;
	}

	@Override
	public void init()
	{
		//add a start button
		startButton = new RigidRectPiece();
		startButton.setBounds(150,250, 130, 40);
		Image startImage = applet.intializeImage("startButton.png");
		startButton.setImage(startImage);
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
		attributes.setBackground(Color.white);
		applet.setLayout(null);
		applet.add(attributes);
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
		mode1.setBackground(Color.white);
		applet.add(mode1);

		Checkbox mode2 = new Checkbox("Two Players",mode_radio_button,false);
		mode2.setLocation(100, 40);
		mode2.setSize(120, 20);
		mode2.setBackground(Color.white);
		applet.add(mode2);		

	}

	public boolean action(Event evt, Object
			whichAction)
	{
		Checkbox currentCheckbox=(Checkbox)evt.target;
		boolean checkboxState = currentCheckbox.getState();
		if (currentCheckbox.getLabel() == "Single Player")
			if (checkboxState)
			{
				applet.setMode(Mode.SinglePlayer);
			}
		if (currentCheckbox.getLabel() == "Two Players")
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
		return true;
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

	}

	@Override
	public void start() {
		sw.start();
	}

	class MyActionListener  implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String s = attributes.getText();
            try{
            	int no_of_attributes = Integer.parseInt(s);
            	
            	if(no_of_attributes < 15 && no_of_attributes >5){
            		applet.setMax_no_attributes(no_of_attributes);
            	}
            }catch (Exception e) {}
        } 
    }
}

package rky.gui;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.event.*;

public class ShowDialogBox{
	JFrame frame;
	double player1 = -1;
	public double player2 = -1;

	public ShowDialogBox(double score1,double score2){
		
		player1 = score1;
		player2 = score2;
		frame = new JFrame("Show Message Dialog");
		JLabel label = new JLabel("Game Over !");
		JButton button = new JButton("View Score");
		button.addActionListener(new MyAction());
		frame.setLayout(new BorderLayout());
		frame.add(label,BorderLayout.CENTER);
		frame.add(button,BorderLayout.SOUTH);
		frame.setSize(170,100);
		frame.setLocation(400, 400);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public class MyAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(player2 == -1)
				JOptionPane.showMessageDialog(frame,"Player1 Scored: "+player1);
			else{
				JOptionPane.showMessageDialog(frame,"Player1 Scored: "+player1+" Player2 Score: "+player2);
			}
		}
	}
}
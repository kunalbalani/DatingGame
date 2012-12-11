package rky.gui.board;

import java.awt.Graphics;

import rky.simpleGamePlatform.Piece;

public abstract class Board {

	public abstract void init();
	public abstract void start();
	public abstract void stop();
	public abstract void update();
	public abstract void pieceClicked(Piece p);
	public abstract void drawOverlay(Graphics g);
}

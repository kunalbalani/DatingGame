package rky.gui.board;

import java.awt.Graphics;

public abstract class Board {

	public abstract void init();
	public abstract void stop();
	public abstract void drawOverlay(Graphics g);
}

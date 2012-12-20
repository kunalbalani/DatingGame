package rky.simpleGamePlatform;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;


// A RECTANGLE SHAPED THING

public class RectPiece extends Piece 
{

	int xImage; 
	int yImage;
	int widthImage;
	int heightImage;

	public enum DIRECTION {
		TOP,LEFT,RIGHT,BOTTOM
	}

	public DIRECTION direction = DIRECTION.TOP;


	public Piece setBounds(double x, double y, double width, double height) {
		this.x = x + width / 2;
		this.y = y + height / 2;
		X[0] = x;
		Y[0] = y;
		X[1] = x + width;
		Y[1] = y;
		X[2] = x + width;
		Y[2] = y + height;
		X[3] = x;
		Y[3] = y + height;
		setShape(X, Y, 4);


		//save co-ordinates for image
		xImage = (int)x;
		yImage = (int)y;
		widthImage = (int)width;
		heightImage = (int)height;

		return this;

	}

	public void setImage(Image image){
		this.image = image;
	}

	void update(Graphics g) {
		super.update(g);

		if(image != null){
			g.drawImage(image,xImage,yImage,widthImage,heightImage, null);
		}
	}


	boolean mouseDrag(int x, int y) {
		if(x> 30 && x <280){
			delegate.setScoreForSelectedAttrbiute((x*100)/250 -10);
			return super.mouseDrag(x,685);
		}
		return false;

	}

	public Piece setX(double x) {
		xImage = (int)x - this.widthImage/2;
		return super.setX(x);
	}

	/**
      Set the y position of this piece.
	 */

	public Piece setY(double y) {
		yImage = (int)y - this.heightImage/2;
		return super.setY(y);
	}

	public DIRECTION getDirection(){
		return this.direction;
	}

	/**
	 * Rotates image in clockwise/anticloackwise directions
	 * @param image,boolean -> if true image is rotated clockwise direction else anticloackwise
	 * @return rotated image
	 */
	public BufferedImage rotateImage(BufferedImage image,boolean isClockWise){

		BufferedImage retVal ;

		AffineTransform tx = new AffineTransform();
		if(isClockWise){
			tx.rotate(Math.toRadians(90), image.getWidth()/2, image.getHeight()/2);
		}else{
			tx.rotate(Math.toRadians(270), image.getWidth()/2, image.getHeight()/2);
		}

		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		retVal = op.filter(image, null);

		return retVal;
	}

}



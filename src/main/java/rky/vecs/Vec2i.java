package rky.vecs;

import rky.vecs.Operators.vOpV;
import rky.vecs.Operators.viOpV;
import rky.vecs.Operators.vvOpF;
import rky.vecs.Operators.vvOpI;
import rky.vecs.Operators.vvOpV;



public class Vec2i extends Veci
{
	public Vec2i() {
		super(2);
	}
	
	public Vec2i(Vec2i vec) {
		super(vec);
	}
	
	public Vec2i( int x, int y ) {
		super(2);
		_vals[0] = x;
		_vals[1] = y;
	}
	
	// Copies the first two components of vec into the new Vec3i.
	// If vec's degree is smaller than 2, the corresponding components are left as 0.
	public Vec2i( Veci vec ) {
		super(2);
		if( vec._vals.length > 0 ) {
			_vals[0] = vec._vals[0];
			if( vec._vals.length > 1 )
				_vals[1] = vec._vals[1];
		}
	}
	
	public Vec2i clone() {
		return new Vec2i( this );
	}
	
	public Vec2f toF() {
		return new Vec2f( this );
	}
	
	//-------------------------------------------------------------
	
	public int getX()           { return _vals[0]; }
	public int getY()           { return _vals[1]; }
	
	public void   setX(int x)   { _vals[0] = x; }
	public void   setY(int y)   { _vals[1] = y; }
	
	//-------------------------------------------------------------
	
	public  Vec2i op( vOpV<Vec2i> operator )                    { return operator.performOp(this);           }
	public  Vec2i op( vvOpV<Vec2i> operator, Vec2i operand2 )   { return operator.performOp(this, operand2); }
	public  Vec2i op( viOpV<Vec2i> operator, int operand2 )     { return operator.performOp(this, operand2); }
	public double op( vvOpF<Vec2i> operator, Vec2i operand2 )   { return operator.performOp(this, operand2); }
	public    int op( vvOpI<Vec2i> operator, Vec2i operand2 )   { return operator.performOp(this, operand2); }
	
}
package rky.vecs;

import rky.vecs.Operators.vOpV;
import rky.vecs.Operators.vfOpV;
import rky.vecs.Operators.viOpV;
import rky.vecs.Operators.vvOpF;
import rky.vecs.Operators.vvOpI;
import rky.vecs.Operators.vvOpV;

public class Vec2f extends Vecf
{
	
	public Vec2f() {
		super(2);
	}

	public Vec2f( Vec2f vec ) {
		super( vec );
	}
	
	public Vec2f( double x, double y ) {
		super(2);
		_vals[0] = x;
		_vals[1] = y;
	}
	
	public Vec2f( int x, int y ) {
		super(2);
		_vals[0] = x;
		_vals[1] = y;
	}
	
	// Copies the first two components of vec into the new Vec3i.
	// If vec's degree is smaller than 2, the corresponding components are left as 0.
	public Vec2f( Vecf vec ) {
		super(2);
		if( vec._vals.length > 0 ) {
			_vals[0] = vec._vals[0];
			if( vec._vals.length > 1 )
				_vals[1] = vec._vals[1];
		}
	}

	// Copies the first two components of vec into the new Vec3i.
	// If vec's degree is smaller than 2, the corresponding components are left as 0.
	public Vec2f( Veci vec ) {
		super(2);
		if( vec._vals.length > 0 ) {
			_vals[0] = vec._vals[0];
			if( vec._vals.length > 1 )
				_vals[1] = vec._vals[1];
		}
	}
	
	public Vec2f clone() {
		return new Vec2f( this );
	}
	
	//-------------------------------------------------------------
	
	public double getX()           { return _vals[0]; }
	public double getY()           { return _vals[1]; }
	
	public void   setX(double x)   { _vals[0] = x; }
	public void   setY(double y)   { _vals[1] = y; }
	
	//-------------------------------------------------------------
	
	public  Vec2f op( vOpV<Vec2f> operator )                     { return operator.performOp(this);           }
	public  Vec2f op( vvOpV<Vec2f> operator, Vec2f operand2 )    { return operator.performOp(this, operand2); }
	public  Vec2f op( viOpV<Vec2f> operator, int operand2 )      { return operator.performOp(this, operand2); }
	public  Vec2f op( vfOpV<Vec2f> operator, double operand2 )   { return operator.performOp(this, operand2); }
	public double op( vvOpF<Vec2f> operator, Vec2f operand2 )    { return operator.performOp(this, operand2); }
	public    int op( vvOpI<Vec2f> operator, Vec2f operand2 )    { return operator.performOp(this, operand2); }
	
}

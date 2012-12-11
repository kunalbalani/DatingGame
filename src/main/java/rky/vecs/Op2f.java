package rky.vecs;

import rky.vecs.Operators.vOpF;
import rky.vecs.Operators.vfOpV;
import rky.vecs.Operators.vvOpF;
import rky.vecs.Operators.vvOpV;

public class Op2f
{
	
	public static final vvOpV<Vec2f> PLUS;
	public static final vvOpV<Vec2f> MINUS;
	public static final vfOpV<Vec2f> TIMES;
	public static final vfOpV<Vec2f> DIVIDE;
	public static final vvOpF<Vec2f> DOT;
	public static final vvOpV<Vec2f> COMP_WISE_MULT;
	public static final vvOpV<Vec2f> COMP_WISE_MAX;
	public static final vvOpV<Vec2f> COMP_WISE_MIN;
	public static final vOpF<Vec2f>  MAX;
	public static final vOpF<Vec2f>  MIN;
	
	
	
	static {
		PLUS = new vvOpV<Vec2f>() {
			public Vec2f performOp(Vec2f v1, Vec2f operand2) {
				v1._vals[0] += operand2._vals[0];
				v1._vals[1] += operand2._vals[1];
				return v1;
			}
		};
		
		MINUS = new vvOpV<Vec2f>() {
			public Vec2f performOp(Vec2f v1, Vec2f v2) {
				v1._vals[0] -= v2._vals[0];
				v1._vals[1] -= v2._vals[1];
				return v1;
			}
		};
		
		TIMES = new vfOpV<Vec2f>() {
			public Vec2f performOp(Vec2f v, double n) {
				v._vals[0] *= n;
				v._vals[1] *= n;
				return v;
			}
		};

		DIVIDE = new vfOpV<Vec2f>() {
			public Vec2f performOp(Vec2f v, double n) {
				v._vals[0] /= n;
				v._vals[1] /= n;
				return v;
			}
		};
		
		DOT = new vvOpF<Vec2f>() {
			public double performOp(Vec2f v1, Vec2f v2) {
				return v1._vals[0] * v2._vals[0] + v1._vals[1] * v2._vals[1];
			}
		};
		
		COMP_WISE_MULT = new vvOpV<Vec2f>() {
			public Vec2f performOp(Vec2f v1, Vec2f v2) {
				v1._vals[0] *= v2._vals[0];
				v1._vals[1] *= v2._vals[1];
				return v1;
			}
		};

		COMP_WISE_MAX = new vvOpV<Vec2f>() {
			public Vec2f performOp(Vec2f v1, Vec2f v2) {
				v1._vals[0] = Math.max( v1._vals[0], v2._vals[0] );
				v1._vals[1] = Math.max( v1._vals[1], v2._vals[1] );
				return v1;
			}
		};

		COMP_WISE_MIN = new vvOpV<Vec2f>() {
			public Vec2f performOp(Vec2f v1, Vec2f v2) {
				v1._vals[0] = Math.min( v1._vals[0], v2._vals[0] );
				v1._vals[1] = Math.min( v1._vals[1], v2._vals[1] );
				return v1;
			}
		};
		
		MAX = new vOpF<Vec2f>() {
			public double performOp(Vec2f v) {
				return Math.max( v._vals[0], v._vals[1] );
			}
		};
		
		MIN = new vOpF<Vec2f>() {
			public double performOp(Vec2f v) {
				return Math.min( v._vals[0], v._vals[1] );
			}
		};
		
	}	
}

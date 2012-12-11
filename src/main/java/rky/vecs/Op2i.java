package rky.vecs;

import rky.vecs.Operators.vOpI;
import rky.vecs.Operators.viOpV;
import rky.vecs.Operators.vvOpI;
import rky.vecs.Operators.vvOpV;



public class Op2i {
	
	public static final vvOpV<Vec2i> PLUS;
	public static final vvOpV<Vec2i> MINUS;
	public static final viOpV<Vec2i> TIMES;
	public static final viOpV<Vec2i> DIVIDE;
	public static final vvOpI<Vec2i> DOT;
	public static final vvOpV<Vec2i> COMP_WISE_MULT;
	public static final vvOpV<Vec2i> COMP_WISE_MAX;
	public static final vvOpV<Vec2i> COMP_WISE_MIN;
	public static final vOpI<Vec2i>  MAX;
	public static final vOpI<Vec2i>  MIN;
	
	
	
	static {
		PLUS = new vvOpV<Vec2i>() {
			public Vec2i performOp(Vec2i v1, Vec2i operand2) {
				v1._vals[0] += operand2._vals[0];
				v1._vals[1] += operand2._vals[1];
				return v1;
			}
		};
		
		MINUS = new vvOpV<Vec2i>() {
			public Vec2i performOp(Vec2i v1, Vec2i v2) {
				v1._vals[0] -= v2._vals[0];
				v1._vals[1] -= v2._vals[1];
				return v1;
			}
		};
		
		TIMES = new viOpV<Vec2i>() {
			public Vec2i performOp(Vec2i v, int n) {
				v._vals[0] *= n;
				v._vals[1] *= n;
				return v;
			}
		};

		DIVIDE = new viOpV<Vec2i>() {
			public Vec2i performOp(Vec2i v, int n) {
				v._vals[0] /= n;
				v._vals[1] /= n;
				return v;
			}
		};
		
		DOT = new vvOpI<Vec2i>() {
			public int performOp(Vec2i v1, Vec2i v2) {
				return v1._vals[0] * v2._vals[0] + v1._vals[1] * v2._vals[1]; 
			}
		};
		
		COMP_WISE_MULT = new vvOpV<Vec2i>() {
			public Vec2i performOp(Vec2i v1, Vec2i v2) {
				v1._vals[0] *= v2._vals[0];
				v1._vals[1] *= v2._vals[1];
				return v1;
			}
		};

		COMP_WISE_MAX = new vvOpV<Vec2i>() {
			public Vec2i performOp(Vec2i v1, Vec2i v2) {
				v1._vals[0] = Math.max( v1._vals[0], v2._vals[0] );
				v1._vals[1] = Math.max( v1._vals[1], v2._vals[1] );
				return v1;
			}
		};

		COMP_WISE_MIN = new vvOpV<Vec2i>() {
			public Vec2i performOp(Vec2i v1, Vec2i v2) {
				v1._vals[0] = Math.min( v1._vals[0], v2._vals[0] );
				v1._vals[1] = Math.min( v1._vals[1], v2._vals[1] );
				return v1;
			}
		};
		
		MAX = new vOpI<Vec2i>() {
			public int performOp(Vec2i v) {
				return Math.max( v._vals[0], v._vals[1] );
			}
		};
		
		MIN = new vOpI<Vec2i>() {
			public int performOp(Vec2i v) {
				return Math.min( v._vals[0], v._vals[1] );
			}
		};
		
	}	

}

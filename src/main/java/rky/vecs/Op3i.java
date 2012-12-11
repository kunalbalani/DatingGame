package rky.vecs;

import rky.vecs.Operators.vOpI;
import rky.vecs.Operators.viOpV;
import rky.vecs.Operators.vvOpI;
import rky.vecs.Operators.vvOpV;



public class Op3i {
	
	public static final vvOpV<Vec3i> PLUS;
	public static final vvOpV<Vec3i> MINUS;
	public static final viOpV<Vec3i> TIMES;
	public static final viOpV<Vec3i> DIVIDE;
	public static final vvOpI<Vec3i> DOT;
	public static final vvOpV<Vec3i> COMP_WISE_MULT;
	public static final vvOpV<Vec3i> COMP_WISE_MAX;
	public static final vvOpV<Vec3i> COMP_WISE_MIN;
	public static final vOpI<Vec3i>  MAX;
	public static final vOpI<Vec3i>  MIN;
	
	
	
	static {
		PLUS = new vvOpV<Vec3i>() {
			public Vec3i performOp(Vec3i v1, Vec3i operand2) {
				v1._vals[0] += operand2._vals[0];
				v1._vals[1] += operand2._vals[1];
				v1._vals[2] += operand2._vals[2];
				return v1;
			}
		};
		
		MINUS = new vvOpV<Vec3i>() {
			public Vec3i performOp(Vec3i v1, Vec3i v2) {
				v1._vals[0] -= v2._vals[0];
				v1._vals[1] -= v2._vals[1];
				v1._vals[2] -= v2._vals[2];
				return v1;
			}
		};
		
		TIMES = new viOpV<Vec3i>() {
			public Vec3i performOp(Vec3i v, int n) {
				v._vals[0] *= n;
				v._vals[1] *= n;
				v._vals[2] *= n;
				return v;
			}
		};

		DIVIDE = new viOpV<Vec3i>() {
			public Vec3i performOp(Vec3i v, int n) {
				v._vals[0] /= n;
				v._vals[1] /= n;
				v._vals[2] /= n;
				return v;
			}
		};
		
		DOT = new vvOpI<Vec3i>() {
			public int performOp(Vec3i v1, Vec3i v2) {
				return v1._vals[0] * v2._vals[0] + 
						v1._vals[1] * v2._vals[1] +
						v1._vals[2] * v2._vals[2]; 
			}
		};
		
		COMP_WISE_MULT = new vvOpV<Vec3i>() {
			public Vec3i performOp(Vec3i v1, Vec3i v2) {
				v1._vals[0] *= v2._vals[0];
				v1._vals[1] *= v2._vals[1];
				v1._vals[2] *= v2._vals[2];
				return v1;
			}
		};

		COMP_WISE_MAX = new vvOpV<Vec3i>() {
			public Vec3i performOp(Vec3i v1, Vec3i v2) {
				v1._vals[0] = Math.max( v1._vals[0], v2._vals[0] );
				v1._vals[1] = Math.max( v1._vals[1], v2._vals[1] );
				v1._vals[2] = Math.max( v1._vals[2], v2._vals[2] );
				return v1;
			}
		};

		COMP_WISE_MIN = new vvOpV<Vec3i>() {
			public Vec3i performOp(Vec3i v1, Vec3i v2) {
				v1._vals[0] = Math.min( v1._vals[0], v2._vals[0] );
				v1._vals[1] = Math.min( v1._vals[1], v2._vals[1] );
				v1._vals[2] = Math.min( v1._vals[2], v2._vals[2] );
				return v1;
			}
		};
		
		MAX = new vOpI<Vec3i>() {
			public int performOp(Vec3i v) {
				return Math.max( Math.max( v._vals[0], v._vals[1] ), v._vals[2] );
			}
		};
		
		MIN = new vOpI<Vec3i>() {
			public int performOp(Vec3i v) {
				return Math.max( Math.min( v._vals[0], v._vals[1] ), v._vals[2] );
			}
		};
		
		
	}	


}

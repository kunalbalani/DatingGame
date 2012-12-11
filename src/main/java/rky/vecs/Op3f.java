package rky.vecs;

import rky.vecs.Operators.vOpF;
import rky.vecs.Operators.vfOpV;
import rky.vecs.Operators.vvOpF;
import rky.vecs.Operators.vvOpV;

public class Op3f
{
	public static final vvOpV<Vec3f> PLUS;
	public static final vvOpV<Vec3f> MINUS;
	public static final vfOpV<Vec3f> TIMES;
	public static final vfOpV<Vec3f> DIVIDE;
	public static final vvOpF<Vec3f> DOT;
	public static final vvOpV<Vec3f> COMP_WISE_MULT;
	public static final vvOpV<Vec3f> COMP_WISE_MAX;
	public static final vvOpV<Vec3f> COMP_WISE_MIN;
	public static final vOpF<Vec3f>  MAX;
	public static final vOpF<Vec3f>  MIN;
	
	
	
	static {
		PLUS = new vvOpV<Vec3f>() {
			public Vec3f performOp(Vec3f v1, Vec3f operand2) {
				v1._vals[0] += operand2._vals[0];
				v1._vals[1] += operand2._vals[1];
				v1._vals[2] += operand2._vals[2];
				return v1;
			}
		};
		
		MINUS = new vvOpV<Vec3f>() {
			public Vec3f performOp(Vec3f v1, Vec3f v2) {
				v1._vals[0] -= v2._vals[0];
				v1._vals[1] -= v2._vals[1];
				v1._vals[2] -= v2._vals[2];
				return v1;
			}
		};
		
		TIMES = new vfOpV<Vec3f>() {
			public Vec3f performOp(Vec3f v, double n) {
				v._vals[0] *= n;
				v._vals[1] *= n;
				v._vals[2] *= n;
				return v;
			}
		};

		DIVIDE = new vfOpV<Vec3f>() {
			public Vec3f performOp(Vec3f v, double n) {
				v._vals[0] /= n;
				v._vals[1] /= n;
				v._vals[2] /= n;
				return v;
			}
		};
		
		DOT = new vvOpF<Vec3f>() {
			public double performOp(Vec3f v1, Vec3f v2) {
				return v1._vals[0] * v2._vals[0] + 
						v1._vals[1] * v2._vals[1] +
						v1._vals[2] * v2._vals[2]; 
			}
		};
		
		COMP_WISE_MULT = new vvOpV<Vec3f>() {
			public Vec3f performOp(Vec3f v1, Vec3f v2) {
				v1._vals[0] *= v2._vals[0];
				v1._vals[1] *= v2._vals[1];
				v1._vals[2] *= v2._vals[2];
				return v1;
			}
		};

		COMP_WISE_MAX = new vvOpV<Vec3f>() {
			public Vec3f performOp(Vec3f v1, Vec3f v2) {
				v1._vals[0] = Math.max( v1._vals[0], v2._vals[0] );
				v1._vals[1] = Math.max( v1._vals[1], v2._vals[1] );
				v1._vals[2] = Math.max( v1._vals[2], v2._vals[2] );
				return v1;
			}
		};

		COMP_WISE_MIN = new vvOpV<Vec3f>() {
			public Vec3f performOp(Vec3f v1, Vec3f v2) {
				v1._vals[0] = Math.min( v1._vals[0], v2._vals[0] );
				v1._vals[1] = Math.min( v1._vals[1], v2._vals[1] );
				v1._vals[2] = Math.min( v1._vals[2], v2._vals[2] );
				return v1;
			}
		};
		
		MAX = new vOpF<Vec3f>() {
			public double performOp(Vec3f v) {
				return Math.max( Math.max( v._vals[0], v._vals[1] ), v._vals[2] );
			}
		};
		
		MIN = new vOpF<Vec3f>() {
			public double performOp(Vec3f v) {
				return Math.max( Math.min( v._vals[0], v._vals[1] ), v._vals[2] );
			}
		};
		
		
	}
}

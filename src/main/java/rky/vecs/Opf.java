package rky.vecs;

import rky.vecs.Operators.vOpF;
import rky.vecs.Operators.vfOpV;
import rky.vecs.Operators.vvOpF;
import rky.vecs.Operators.vvOpV;

public class Opf
{
	public static final vvOpV<Vecf> PLUS;
	public static final vvOpV<Vecf> MINUS;
	public static final vfOpV<Vecf> TIMES;
	public static final vfOpV<Vecf> DIVIDE;
	public static final vvOpF<Vecf> DOT;
	public static final vvOpV<Vecf> COMP_WISE_MULT;
	public static final vvOpV<Vecf> COMP_WISE_MAX;    // NOT IN-PLACE
	public static final vvOpV<Vecf> COMP_WISE_MIN;    // NOT IN-PLACE
	public static final vOpF<Vecf>  MAX;
	public static final vOpF<Vecf>  MIN;
	
	static
	{
		PLUS = new vvOpV<Vecf>() {
			public Vecf performOp(Vecf v1, Vecf v2) {
				int len = v1._vals.length < v2._vals.length ? v1._vals.length : v2._vals.length;
				for( int i = 0; i < len; i++ ) 
					v1._vals[i] += v2._vals[i];
				return v1;
			}
		};
		
		MINUS = new vvOpV<Vecf>() {
			public Vecf performOp(Vecf v1, Vecf v2) {
				final int len = v1._vals.length < v2._vals.length ? v1._vals.length : v2._vals.length;
				for( int i = 0; i < len; i++ ) 
					v1._vals[i] -= v2._vals[i];
				return v1;
			}
		};
		
		TIMES = new vfOpV<Vecf>() {
			public Vecf performOp(Vecf v, double n) {
				for( int i = 0; i < v._vals.length; i++ ) 
					v._vals[i] *= n;
				return v;
			}
		};

		DIVIDE = new vfOpV<Vecf>() {
			public Vecf performOp(Vecf v, double n) {
				for( int i = 0; i < v._vals.length; i++ ) 
					v._vals[i] /= n;
				return v;
			}
		};
		
		DOT = new vvOpF<Vecf>() {
			public double performOp(Vecf v1, Vecf v2) {
				final int len = v1._vals.length < v2._vals.length ? v1._vals.length : v2._vals.length;
				double sum = 0;
				for( int i = 0; i < len; i++ )
					sum += v1._vals[i] * v2._vals[i];
				return sum;
			}
		};
		
		COMP_WISE_MULT = new vvOpV<Vecf>() {
			public Vecf performOp(Vecf v1, Vecf v2) {
				int len = v1._vals.length < v2._vals.length ? v1._vals.length : v2._vals.length;
				for( int i = 0; i < len; i++ )
					v1._vals[i] *= v2._vals[i];
				for( int i = len; i < v1._vals.length; i++ )
					v1._vals[i] = 0;
				return v1;
			}
		};

		COMP_WISE_MAX = new vvOpV<Vecf>() {
			public Vecf performOp(Vecf v1, Vecf v2) {
				Vecf v = new Vecf( Math.max(v1._vals.length, v2._vals.length) );
				int len = v1._vals.length < v2._vals.length ? v1._vals.length : v2._vals.length;
				for( int i = 0; i < len; i++ )
					v._vals[i] = Math.max( v1._vals[i], v2._vals[i] );
				return v;
			}
		};

		COMP_WISE_MIN = new vvOpV<Vecf>() {
			public Vecf performOp(Vecf v1, Vecf v2) {
				Vecf v = new Vecf( v1._vals.length < v2._vals.length ? v1 : v2 );
				int len = v1._vals.length < v2._vals.length ? v1._vals.length : v2._vals.length;
				for( int i = 0; i < len; i++ )
					v._vals[i] = Math.min( v1._vals[i], v2._vals[i] );
				return v;
			}
		};

		MAX = new vOpF<Vecf>() {
			public double performOp(Vecf v) {
				double max = Double.NEGATIVE_INFINITY;
				for( int i = 0; i < v._vals.length; i++ )
					if( v._vals[i] > max )
						max = v._vals[i];
				return max;
			}
		};
		
		MIN = new vOpF<Vecf>() {
			public double performOp(Vecf v) {
				double min = Double.POSITIVE_INFINITY;
				for( int i = 0; i < v._vals.length; i++ )
					if( v._vals[i] < min )
						min = v._vals[i];
				return min;
			}
		};
	}
}

package rky.vecs;

import rky.vecs.Operators.vOpI;
import rky.vecs.Operators.viOpV;
import rky.vecs.Operators.vvOpI;
import rky.vecs.Operators.vvOpV;

public class Opi
{
	public static final vvOpV<Veci> PLUS;
	public static final vvOpV<Veci> MINUS;
	public static final viOpV<Veci> TIMES;
	public static final viOpV<Veci> DIVIDE;
	public static final vvOpI<Veci> DOT;
	public static final vvOpV<Veci> COMP_WISE_MULT;
	public static final vvOpV<Veci> COMP_WISE_MAX;    // NOT IN-PLACE
	public static final vvOpV<Veci> COMP_WISE_MIN;    // NOT IN-PLACE
	public static final vOpI<Veci>  MAX;
	public static final vOpI<Veci>  MIN;
	
	static
	{
		PLUS = new vvOpV<Veci>() {
			public Veci performOp(Veci v1, Veci v2) {
				int len = v1._vals.length < v2._vals.length ? v1._vals.length : v2._vals.length;
				for( int i = 0; i < len; i++ ) 
					v1._vals[i] += v2._vals[i];
				return v1;
			}
		};
		
		MINUS = new vvOpV<Veci>() {
			public Veci performOp(Veci v1, Veci v2) {
				final int len = v1._vals.length < v2._vals.length ? v1._vals.length : v2._vals.length;
				for( int i = 0; i < len; i++ ) 
					v1._vals[i] -= v2._vals[i];
				return v1;
			}
		};
		
		TIMES = new viOpV<Veci>() {
			public Veci performOp(Veci v, int n) {
				for( int i = 0; i < v._vals.length; i++ ) 
					v._vals[i] *= n;
				return v;
			}
		};

		DIVIDE = new viOpV<Veci>() {
			public Veci performOp(Veci v, int n) {
				for( int i = 0; i < v._vals.length; i++ ) 
					v._vals[i] /= n;
				return v;
			}
		};
		
		DOT = new vvOpI<Veci>() {
			public int performOp(Veci v1, Veci v2) {
				final int len = v1._vals.length < v2._vals.length ? v1._vals.length : v2._vals.length;
				int sum = 0;
				for( int i = 0; i < len; i++ )
					sum += v1._vals[i] * v2._vals[i];
				return sum;
			}
		};
		
		COMP_WISE_MULT = new vvOpV<Veci>() {
			public Veci performOp(Veci v1, Veci v2) {
				int len = v1._vals.length < v2._vals.length ? v1._vals.length : v2._vals.length;
				for( int i = 0; i < len; i ++ )
					v1._vals[i] *= v2._vals[i];
				for( int i = len; i < v1._vals.length; i ++)
					v1._vals[i] = 0;
				return v1;
			}
		};

		COMP_WISE_MAX = new vvOpV<Veci>() {
			public Veci performOp(Veci v1, Veci v2) {
				Veci v = new Veci( Math.max(v1._vals.length, v2._vals.length) );
				int len = v1._vals.length < v2._vals.length ? v1._vals.length : v2._vals.length;
				for( int i = 0; i < len; i++ )
					v._vals[i] = Math.max( v1._vals[i], v2._vals[i] );
				return v;
			}
		};

		COMP_WISE_MIN = new vvOpV<Veci>() {
			public Veci performOp(Veci v1, Veci v2) {
				Veci v = new Veci( v1._vals.length < v2._vals.length ? v1 : v2 );
				int len = v1._vals.length < v2._vals.length ? v1._vals.length : v2._vals.length;
				for( int i = 0; i < len; i++ )
					v._vals[i] = Math.min( v1._vals[i], v2._vals[i] );
				return v;
			}
		};

		MAX = new vOpI<Veci>() {
			public int performOp(Veci v) {
				int max = Integer.MIN_VALUE;
				for( int i = 0; i < v._vals.length; i++ )
					if( v._vals[i] > max )
						max = v._vals[i];
				return max;
			}
		};
		
		MIN = new vOpI<Veci>() {
			public int performOp(Veci v) {
				int min = Integer.MAX_VALUE;
				for( int i = 0; i < v._vals.length; i++ )
					if( v._vals[i] < min )
						min = v._vals[i];
				return min;
			}
		};
	}
}

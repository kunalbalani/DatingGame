package rky.vecs;

import java.util.Arrays;
import java.util.List;

public class Vecf implements Comparable<Vecf>
{
	double[] _vals;
	
	public Vecf( Vecf vec ) {
		_vals = vec._vals.clone();
	}
	
	public Vecf( int degree ) {
		_vals = new double[degree];
	}
	
	public Vecf( double[] vec ) {
		_vals = vec.clone();
	}
	
	public Vecf( List<Double> vec ) {
		this( vec.size() );
		int i = 0;
		for( Double v : vec )
			_vals[i++] = v;
	}
	
	public double get( int index ) {
		return _vals[index];
	}
	
	public void set( int index, double val ) {
		_vals[index] = val;
	}
	
	public int getDegree() {
		return _vals.length;
	}
	
	public String toString() {
		return Arrays.toString(_vals);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(_vals);
	}
	
	@Override
	public boolean equals( Object o ) {
		if( o == null ) return false;
		if( o == this ) return true;
		return compareTo((Vecf)o) == 0;
	}

	/* This method does component-wise comparisons, starting at component 0 (X).
	 * If vectors are not of the same degree, the results are the same as if the
	 * smaller degree vector was completed with 0's to match the degree of the
	 * higher degree vector.
	 * 
	 *  a = [1, 2, 3]        ---\  [1, 2, 3, 0, 0]  ---\   a < b
	 *  b = [1, 2, 3, 4, 5]  ---/  [1, 2, 3, 4, 5]  ---/
	 *  
	 *  a = [1, 2, 3]        ---\  [1, 2, 3,  0]    ---\   a > b
	 *  b = [1, 2, 3, -1]    ---/  [1, 2, 3, -1]    ---/
	 */
	@Override
	public int compareTo(Vecf v) {
		double[] a = _vals, b = v._vals;
		int athenb = -1;  // if a < b, -1 is returned
		if( a.length > b.length ) 
		{
			double[] tmp = a;
			a = b;
			b = tmp;
			athenb = 1;
		}   // a now holds the shorter array
		// for components in both vectors, compare corresponding components
		for( int i = 0; i < a.length; i++ )
			if( a[i] < b[i] )        return  athenb;
			else if( a[i] > b[i] )   return -athenb;
		// for components in only the higher degree vector, compare against 0 values
		for( int i = a.length; i < b.length; i++ )
			if( b[i] < 0 )       return -athenb;
			else if( b[i] > 0 )  return  athenb;
		return 0;
	}

}

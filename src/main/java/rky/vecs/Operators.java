package rky.vecs;

/*
 * Interface conventions:
 * - Naming: [x...]Op[y]
 *   - x: input type (for example vf means that the operator takes a vector and a floating point number)
 *   - y: output type
 * - Types:
 *   - v: integer vector (extends Veci)
 *   - d: decimal number (integer)
 *   - f: floating point number (double)
 *   
 * - Return:
 *   - if an operation returns a vector, the operation is usually done in place on
 *     the first vector in the inputs
 */

public class Operators {
	
	public static interface vOpV<V> {
		public V performOp( V operand );
	}
	
	public static interface vOpI<V> {
		public int performOp( V operand );
	}
	
	public static interface vOpF<V> {
		public double performOp( V operand );
	}
	
	public static interface vvOpV<V> {
		public V performOp( V operand1, V operand2 );
	}
	
	public static interface viOpV<V> {
		public V performOp( V operand1, int operand2 );
	}
	
	public static interface vfOpV<V> {
		public V performOp( V operand1, double operand2 );
	}
	
	public static interface vvOpF<V> {
		public double performOp( V operand1, V operand2 );
	}
	
	public static interface vvOpI<V> {
		public int performOp( V operand1, V operand2 );
	}
	
}

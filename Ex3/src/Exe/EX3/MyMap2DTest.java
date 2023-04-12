//by:Matan ziv


package Exe.EX3;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import org.junit.jupiter.api.Test;

class MyMap2DTest {
	static  Map2D _map = null;
	public static Point2D p1, p2, p3, p4;
	public static final int BLACK = Color.BLACK.getRGB();

	//Compare the matrix to the map
	boolean compar(int[][] m){
		for(int x = 0;x<_map.getWidth();x++) {
			for(int y = 0;y<_map.getHeight();y++) {
				if (_map.getPixel(x,y)!=m[x][y]) {return false;}
			}
		}
		return true;
	}

	void printer() {
		for (int x = 0; x <=9; x++) {
			System.out.println("");
			for (int y = 0; y <=9; y++) {
				System.out.print(_map.getPixel(y, x) + ", ");
			}
		}
	}




	/*optional inputs
	 * normal segment 2 points
	 * p1 and p2 are the same
	 * p1 or p2 is out of the matrix
	 */
		@Test
	void drawSegment_test() {
		//this matrix is printed upside down
		int[][] Matrix = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, -1, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, -1, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, -1, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, -1, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, -1, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, -1, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, -1, 0, 0}, 
				{0, 0, 0, 0, 0, 0, -1, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		};
		p1 = new Point2D(1,1);
		p2 = new Point2D(7,7);
		p3 = new Point2D(8,6);
		p4 = new Point2D(15,15);
		_map = new MyMap2D(10);
		
		_map.drawSegment(p1, p2, -1);
		_map.drawSegment(p3, p3, -1);
		_map.drawSegment(p4, p4, -1);
		assertTrue(this.compar(Matrix));
	}

		
		
		/*optional inputs
		 * normal rect 2 points
		 * p1 and p2 are the same
		 * p1 or p2 is out of the matrix
		 */
		
		
	@Test
	void drawRect_test() {
		//this matrix is printed upside down
		int[][] Matrix = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, -1, -1, -1, -1, -1, -1, -1, 0, 0}, 
				{0, -1, -1, -1, -1, -1, -1, -1, 0, 0}, 
				{0, -1, -1, -1, -1, -1, -1, -1, 0, 0}, 
				{0, -1, -1, -1, -1, -1, -1, -1, 0, 0}, 
				{0, -1, -1, -1, -1, -1, -1, -1, 0, 0}, 
				{0, -1, -1, -1, -1, -1, -1, -1, 0, 0}, 
				{0, -1, -1, -1, -1, -1, -1, -1, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, -1},  
		};
		p1 = new Point2D(1,1);
		p2 = new Point2D(7,7);
		p3 = new Point2D(14,71);
		p4 = new Point2D(9,9);
		_map = new MyMap2D(10);
		_map.drawRect(p1, p2, -1);
		_map.drawRect(p4, p4, -1);
		_map.drawRect(p1, p3, -1);
		assertTrue(this.compar(Matrix));
	}

	/*optional inputs
	 * normal circle in bounds
	 * half a circle is out of bounds
	 * all the circle is out of bounds
	 */

	@Test
	void drawCircle_test() {
		//this matrix is printed upside down
		int[][] Matrix = {
				{-1, -1, -1, -1, -1, -1, 0, 0, 0, 0}, 
				{-1, -1, -1, -1, -1, -1, -1, 0, 0, 0}, 
				{-1, -1, -1, -1, -1, -1, 0, 0, 0, 0}, 
				{-1, -1, -1, -1, -1, -1, 0, 0, 0, 0}, 
				{-1, -1, -1, -1, -1, -1, 0, 0, 0, 0}, 
				{-1, -1, -1, -1, -1, 0, 0, 0, 0, 0}, 
				{0, -1, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   
		};	
		p1 = new Point2D(1,1);
		p2 = new Point2D(17,17);
		_map = new MyMap2D(10);
		_map.drawCircle(p1, 5, -1);
		_map.drawCircle(p2, 5, -1);
		assertTrue(this.compar(Matrix));
	}

	/*optional inputs
	 * fill touching bounds
	 * do not fill everything
	 * invalid input
	 */
	@Test
	void fill_test() {
		//this matrix is printed upside down
		int[][] Matrix = {
				{-1, -1, -1, -1, -1, -1, 7, 7, 7, 7}, 
				{-1, -1, -1, -1, -1, -1, -1, 7, 7, 7},
				{-1, -1, -1, -1, -1, -1, 7, 7, 7, 7}, 
				{-1, -1, -1, -1, -1, -1, 7, 7, 7, 7}, 
				{-1, -1, -1, -1, -1, -1, 7, 7, 7, 7}, 
				{-1, -1, -1, -1, -1, 7, 7, 7, 7, 7}, 
				{7, -1, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}   
		};	
		p1 = new Point2D(1,1);
		p2 = new Point2D(7,7);
		p3 = null;
		_map = new MyMap2D(10);
		_map.drawCircle(p1, 5, -1);
		_map.fill(p2, 7);
		assertTrue(this.compar(Matrix));
	}
//	fill(int x, int y, int new_v)
	@Test
	void fill2_test() {
		//this matrix is printed upside down
		int[][] Matrix = {
				{-1, -1, -1, -1, -1, -1, 7, 7, 7, 7}, 
				{-1, -1, -1, -1, -1, -1, -1, 7, 7, 7},
				{-1, -1, -1, -1, -1, -1, 7, 7, 7, 7}, 
				{-1, -1, -1, -1, -1, -1, 7, 7, 7, 7}, 
				{-1, -1, -1, -1, -1, -1, 7, 7, 7, 7}, 
				{-1, -1, -1, -1, -1, 7, 7, 7, 7, 7}, 
				{7, -1, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}   
		};	
		p1 = new Point2D(1,1);
		p2 = new Point2D(7,7);
		p3 = null;
		_map = new MyMap2D(10);
		_map.drawCircle(p1, 5, -1);
		_map.fill(p2.ix(),  p2.iy(), 7);
		assertTrue(this.compar(Matrix));
	}
	

	@Test
	void fill3_test() {
		//this matrix is printed upside down
		int[][] Matrix = {
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, 
				{7, 7, 7, 7, 7, 7, 7, 7, 7, 7}   
		};	
		p1 = new Point2D(1,1);
		p2 = new Point2D(7,7);
		p3 = null;
		_map = new MyMap2D(10);
		_map.drawCircle(p1, 5, -1);
		_map.fill( 7);
		printer();
		assertTrue(this.compar(Matrix));
	}
		

	/*optional inputs
	 * same point
	 * no path
	 * valid input
	 */
	@Test
	void shortestPath_test() {// 
		//this matrix is printed upside down
		int[][] Matrix = {
				{8, 0, 0, 0, 0, 0, 0, 0, 0,0, 0}, 
				{8,  -1, -1, -1, -1, -1, -1, -1,0, 0}, 
				{8,  -1, -1, -1, -1, -1, -1, -1,0, 0}, 
				{8,  -1, -1, -1, -1, -1, -1, -1,0, 0}, 
				{8,  -1, -1, -1, -1, -1, -1, -1,0, 0}, 
				{8,  -1, -1, -1, -1, -1, -1, -1,0, 0}, 
				{8,  -1, -1, -1, -1, -1, -1, -1,0, 0}, 
				{8,  -1, -1, -1, -1, -1, -1, -1,0, 0}, 
				{8,  0, 0, 0, 0, 0, 0, 0,0, 0}, 
				{8, 8, 8, 8, 8, 8, 8, 8, 8, 8} 
		};
		p1 = new Point2D(1,1);
		p2 = new Point2D(7,7);
		p3 = new Point2D(9,9);
		p4 = new Point2D(0,0);
		_map = new MyMap2D(10);
		_map.drawRect(p1, p2, -1);
		Point2D[] ans =_map.shortestPath(p3,p4);
		for(int i=0; i<ans.length; i++) {
			_map.setPixel(ans[i], 8);}
		assertTrue(this.compar(Matrix));

	}
	
	
	@Test
	void shortestPathlngth_test() {// 
		//this matrix is printed upside down
		p1 = new Point2D(1,1);
		p2 = new Point2D(7,7);
		_map = new MyMap2D(10);
		_map.drawRect(p1, p2, -1);
		int ans =_map.shortestPathDist(p1,p2);
		assertEquals(ans,12);

	}

	/*optional inputs
	 * alive and one Neighbour
	 * alive and two or  Neighbours
	 * alive and three Neighbours
	 * alive and four or above Neighbours
	 */

		@Test
	void nextGenGol_test() {
		//this matrix is printed in 90 degrees tilt
		int[][] Matrix = {
				{-1, -1, -1, -1, -1, -1, -1, -1, -16777216, -16777216}, 
				{-1, -1, -1, -1, -1, -1, -1, -1, -16777216,-16777216}, 
				{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 
				{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 
				{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 
				{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 
				{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 
				{-1, -1, -1, -1, -1, -1, -1, -1, -16777216, -16777216}, 
				{-1, -1, -1, -1, -1, -1, -1, -16777216, -1, -1}, 
				{-1, -1, -1, -1, -1, -1, -1, -1, -16777216, -16777216}  
		};	
		_map = new MyMap2D(10);
		
		for (int x = 0; x <=9; x++) {
			for (int y = 0; y <=9; y++) {
				_map.setPixel(x, y, -1);
			}
		}
		
		_map.setPixel(9, 0, BLACK);
		
		_map.setPixel(0, 0, BLACK);
		_map.setPixel(1, 0, BLACK);	
		
		_map.setPixel(1, 9, BLACK);		
		_map.setPixel(0, 8, BLACK);		
		_map.setPixel(1, 8, BLACK);
		
		_map.setPixel(9, 9, BLACK);		
		_map.setPixel(9, 8, BLACK);		
		_map.setPixel(8, 9, BLACK);		
		_map.setPixel(8, 8, BLACK);		
		_map.setPixel(7, 8, BLACK);	
		
		_map.nextGenGol();;
		assertTrue(this.compar(Matrix));
	}
}


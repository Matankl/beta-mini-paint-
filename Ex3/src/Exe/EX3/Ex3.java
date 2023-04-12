//by:Matan ziv
//id:208235796

package Exe.EX3;

import java.awt.Color;

/**
 * This class is a simple "inter-layer" connecting (aka simplifing) the
 * StdDraw_Ex3 with the Map2D interface.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * You should change this class!
 *
 */
public class Ex3 {
	private static  Map2D _map = null;
	private static Color _color = Color.black;
	private static String _mode = "";
	public static final int WHITE = Color.WHITE.getRGB();
	public static final int BLACK = Color.BLACK.getRGB();
	public static Point2D p1, p2;
	public static int countPress = 0;

	public static void main(String[] args) {
		int dim = 10;  // init matrix (map) 10*10
		init(dim);
	}
	private static void init(int x) {
		StdDraw_Ex3.clear();
		_map = new MyMap2D(x);
		StdDraw_Ex3.setScale(-0.5, _map.getHeight()-0.5);
		StdDraw_Ex3.enableDoubleBuffering();
		_map.fill(WHITE);
		drawArray(_map);		
	}

	public static void drawGrid(Map2D map) {
		int w = map.getWidth();
		int h = map.getHeight();
		for(int i=0;i<w;i++) {
			StdDraw_Ex3.line(i, 0, i, h);
		}
		for(int i=0;i<h;i++) {
			StdDraw_Ex3.line(0, i, w, i);
		}
	}
	static public void drawArray(Map2D a) {
		StdDraw_Ex3.clear();
		StdDraw_Ex3.setPenColor(Color.gray);
		drawGrid(_map);
		for(int y=0;y<a.getWidth();y++) {
			for(int x=0;x<a.getHeight();x++) {
				int c = a.getPixel(x, y);
				StdDraw_Ex3.setPenColor(new Color(c));
				drawPixel(x,y);
			}
		}		
		StdDraw_Ex3.show();
	}
	
	//this method difine the color wanted or the size of the map
	public static void actionPerformed(String p) {
		_mode = p;
		if(p.equals("White")) {_color = Color.WHITE; }
		if(p.equals("Black")) {_color = Color.BLACK; }
		if(p.equals("Blue"))  {_color = Color.BLUE; }
		if(p.equals("Red"))   {_color = Color.RED; }
		if(p.equals("Yellow")){_color = Color.YELLOW; }
		if(p.equals("Green")) {_color = Color.GREEN; }

		if(p.equals("20x20")) {init(20);}
		if(p.equals("40x40")) {init(40);}
		if(p.equals("80x80")) {init(80);}
		if(p.equals("160x160")) {init(160);}
		if(p.equals("Clear")) {init(_map.getWidth());}

		drawArray(_map);

	}
	// this method applay the drawings usd in the gui 
	public static void mouseClicked(Point2D p) {
		System.out.println(p);
		int col = _color.getRGB();

		//one click methods

		if(_mode.equals("Point")) {_map.setPixel(p,col );}//sets single dot		

		if(_mode.equals("Fill")) {//fills area
			_map.fill(p, col);
			_mode = "none";
		}	

		if(_mode.equals("Gol")) //one generation of game of life
		{_map.nextGenGol();	}



		//two click methods

		if(_mode.equals("Segment")) { //draw segment
			countPress++;
			if(countPress%2 == 1) {p1 = p;}
			else {p2 = p;
			_map.drawSegment(p1, p2,col);	
			//				_mode = "none";
			countPress = 0;
			}
		}


		if(_mode.equals("Rect")) {// draw rectangle
			countPress++;
			if(countPress%2 == 1) {p1 = p;}
			else {p2 = p;
			_map.drawRect(p1, p2, col);
						_mode = "none";
			countPress = 0;
			}
		} 
		if(_mode.equals("Circle")) { //draw circle
			countPress++;
			if(countPress%2 == 1) {p1 = p;}
			else {p2 = p;
			double rad = MyMap2D.distance(p1, p2); //compute the distance 
			_map.drawCircle(p1, rad, col);
						_mode = "none";
			countPress = 0;
			}
		}
		if(_mode.equals("ShortestPath")) {// apply shortest path and draws it
			countPress++;
			if(countPress%2 == 1) {p1 = p;}
			else {p2 = p;
			Point2D[] ans = _map.shortestPath(p1, p2);
			if(ans == null) {System.out.println("not thesame color or no valid ans");}
			else {
				for(int i=0; i<ans.length; i++) {
					_map.setPixel(ans[i], col);
					System.out.println(ans.length-1);
				}
			}
							_mode = "none";
			countPress = 0;
			}
		}				
		drawArray(_map);
	}


	static private void drawPixel(int x, int y) {
		StdDraw_Ex3.filledCircle(x, y, 0.3);
	}
}

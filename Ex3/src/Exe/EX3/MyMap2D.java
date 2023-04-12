//by:Matan ziv
//id:208235796

package Exe.EX3;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * This class implements the Map2D interface.
 * You should change (implement) this class as part of Ex3. */
public class MyMap2D implements Map2D{
	private int[][] _map;

	public MyMap2D(int w, int h) {init(w,h);}
	public MyMap2D(int size) {this(size,size);}
	public MyMap2D(int[][] data) { 
		this(data.length, data[0].length);
		init(data);
	}

	@Override
	public void init(int w, int h) {
		_map = new int[w][h];

	}
	@Override
	public void init(int[][] arr) {
		init(arr.length, arr[0].length);
		for(int x = 0; x<this.getWidth()&& x<arr.length ;x++) {
			for(int y=0;y<this.getHeight()&& y<arr[0].length;y++) {
				this.setPixel(x, y, arr[x][y]);
			}
		}
	}

	@Override
	public int getWidth() {return _map.length;}
	@Override
	public int getHeight() {return _map[0].length;}
	@Override
	public int getPixel(int x, int y) { return _map[x][y];}
	@Override
	public int getPixel(Point2D p) { 
		return this.getPixel(p.ix(),p.iy());
	}

	public void setPixel(int x, int y, int v) {_map[x][y] = v;}
	public void setPixel(Point2D p, int v) { 
		setPixel(p.ix(), p.iy(), v);
	}


	//the method works like a printer, it "prints" the segment leyer by leyer
	@Override
	public void drawSegment(Point2D p1, Point2D p2, int v) {
		if(is_valid(p1, p2)!= true) {System.err.println("invalid input"); return;}
		double slope =0;
		if((p2.ix()-p1.ix())!= 0) {slope =(p2.y()-p1.y())/(p2.x()-p1.x());} //using the formula

		double y_cros = p1.y()-(slope*p1.x());//using the formula

		for (int y = Math.min(p1.iy(), p2.iy()); y <=Math.max(p1.iy(), p2.iy()); y++) {
			for(int x = Math.min(p1.ix(), p2.ix()); x <=Math.max(p1.ix(), p2.ix()); x++) {
				//set the pixel my the slope
				if(slope >= 1 || slope <=-1) {this.setPixel((int)Math.round((y-y_cros)/slope), y, v);}
				else if((slope <1||slope>-1)&& slope!=0) {this.setPixel(x,(int)Math.round((x*slope)+y_cros), v);}
				else {this.setPixel(x, y, v);

				}

			}

		}
	}





	//the method works like a printer, it "prints" the rectangle leyer by leyer
	@Override
	public void drawRect(Point2D p1, Point2D p2, int col) {
		if(is_valid(p1, p2)!= true) {System.err.println("invalid input"); return;}//if valid
		Point2D bot_r = new Point2D(Math.min(p1.ix(), p2.ix()),Math.min(p1.iy(), p2.iy()));///bottom right corner point

		for(int x = bot_r.ix(); x<=Math.max(p1.ix(), p2.ix()) ;x++) {	//prints the rectangle
			for(int y =bot_r.iy(); y<=Math.max(p1.iy(), p2.iy()); y++) {
				this.setPixel(x,y,col);
			}
		}
	}







	//the method works like a printer, it "prints" the circle leyer by leyer 
	@Override
	public void drawCircle(Point2D p, double rad, int col) {
		for(int x = p.ix()+(int)rad ; x >= p.ix()-(int)rad ; x--){ //sets the x value of a point in the circle
			if(x>this.getWidth()-1||x<0) {continue;}//skip x value out of the Matrix  
			int y = (p.iy() +(int) Math.sqrt((rad*rad) - Math.pow(x -p.ix(), 2))); //sets the x value of a point in the circle
			int yneg = p.iy() - (y-p.iy()); 
			for( y+=0 ; y >= yneg ; y--) {
				if(y>this.getHeight()-1||y<0) {continue;}  //skip y value out of the Matrix
				this.setPixel(x, y, col);
			}	
		}
	}


	//the method works like a stack, it push the related points one by one and sets them
	@Override
	public int fill(Point2D p, int new_v) {
		if(new_v ==this.getPixel(p)) {return 0;}
		LinkedList<Point2D> q = new LinkedList<>();  // new linked list to be used as a stack
		int col = this.getPixel(p);					 // define color to replace 

		q.push(p);								 //poll from the stack
		while (q.size()!=0) {					 //loop that covers all the pixsels related to the starting point
			p = q.peek();					//pull from the stack
			this.setPixel(p, new_v);		

			//check if the right one is related and push it to the stack 
			if (p.ix()<this.getWidth()-1 && this.getPixel(p.ix()+1 , p.iy())== col) { 
				q.push(new Point2D(p.ix()+1 , p.iy()));}

			//check if the left one is related and push it to the stack 
			if (p.ix()>=1 && this.getPixel(p.ix()-1 , p.iy())== col) {
				q.push(new Point2D(p.ix()-1 , p.iy()));}

			//check if the above one is related and push it to the stack 
			if (p.iy()<this.getHeight()-1 && this.getPixel(p.ix(), p.iy()+1)== col) {
				q.push(new Point2D(p.ix() , p.iy()+1));}

			//check if the below one is related and push it to the stack 
			if ( p.iy()>=1 && this.getPixel(p.ix() , p.iy()-1)== col) {
				q.push(new Point2D(p.ix() , p.iy()-1));}

			q.remove(p);
		}
		return 0;
	}



	//unites x and y to Point2D and use the original algorithmv
	@Override
	public int fill(int x, int y, int new_v) {
		Point2D p =new  Point2D (x,y);				
		fill( p,new_v);
		return 0;
	}




	/*this method work like a line it pushes the points and marks them by the number of steps
	 * then go to p2 and saves the way back to p1 in answer array
	 */
	@Override
	public Point2D[] shortestPath(Point2D p1, Point2D p2) {
		//stops the function if p1 and p2 are different colors
		if(this.getPixel(p1)!=this.getPixel(p2)) {return null;}

		int[][] Matrix=  Map4SP(this.getWidth(),this.getHeight(), this.getPixel(p1));// explanation in the Private section
		LinkedList<Point2D> q = new LinkedList<>(); // new linked list and push p1 to it
		Point2D p = p1;
		Matrix[p.ix()][p.iy()]=0;
		q.push(p1);
		p2 = new Point2D(p2.ix(),p2.iy());

		//loop that marks the distance from p1 till hits p2 or till covers all the related pixels starting from 0
		//every if check different neighbor of the point 
		while (q.contains(p2)!= true && q.isEmpty() != true) { 
			p = q.peekLast();

			if (p.ix()<this.getWidth()-1 && Matrix[p.ix()+1][p.iy()]== -1) {
				q.push(new Point2D(p.ix()+1 , p.iy()));
				Matrix[p.ix()+1][p.iy()]=Matrix[p.ix()][p.iy()] + 1;}

			if (p.ix()>=1 && Matrix[p.ix()-1][p.iy()]== -1) {
				q.push(new Point2D(p.ix()-1 , p.iy()));
				Matrix[p.ix()-1][p.iy()]=Matrix[p.ix()][p.iy()] + 1;}

			if (p.iy()<this.getHeight()-1 && Matrix[p.ix()][p.iy()+1]== -1) {
				q.push(new Point2D(p.ix() , p.iy()+1));
				Matrix[p.ix()][p.iy()+1]=Matrix[p.ix()][p.iy()]+1;}

			if (p.iy()>=1 && Matrix[p.ix()][p.iy()-1]== -1) {
				q.push(new Point2D(p.ix() , p.iy()-1));
				Matrix[p.ix()][p.iy()-1]=Matrix[p.ix()][p.iy()]+1;}

			q.remove(p);
		}
		if(Matrix[p2.ix()][p2.iy()]==-1) {return null;}// stops the function if there is no connectivity

		Point2D[] ans = new Point2D[Matrix[p2.ix()][p2.iy()]+1];
		ans[0] = new Point2D(p2.ix(),p2.iy());
		p = ans[0] ;
		//loop that tracks back the shortest path by the mark on the pixel and put that in array 
		//every "if" check different neighbor of the point and if the mark is x-1 it adds it into the array
		for(int i = 1; i < ans.length; i++ ) {
			if (p.ix()<this.getWidth()-1 && Matrix[p.ix()+1][ p.iy()]== Matrix[p.ix()][p.iy()]-1) {
				ans[i]=new Point2D(p.ix()+1 , p.iy());
				p = ans[i]; continue;}

			if (p.ix()>=1 && Matrix[p.ix()-1][p.iy()]== Matrix[p.ix()][p.iy()]-1) {
				ans[i]=new Point2D(p.ix()-1 , p.iy());
				p = ans[i]; continue;}

			if (p.iy()<this.getHeight()-1 && Matrix[p.ix()][ p.iy()+1]== Matrix[p.ix()][p.iy()]-1) {
				ans[i]=new Point2D(p.ix() , p.iy()+1);
				p = ans[i]; continue;}

			if ( p.iy()>=1 && Matrix[p.ix()][ p.iy()-1]== Matrix[p.ix()][p.iy()]-1) {
				ans[i]=new Point2D(p.ix() , p.iy()-1);
				p = ans[i]; continue;}
		}

		return ans;
	}

	//calculate the length of the ans array from shortestPath and subtracts 1
	@Override
	public int shortestPathDist(Point2D p1, Point2D p2) {
		Point2D[] ans =shortestPath(p1,p2);
		return ans.length-1;
	}



	/*this method copy the current map make the changes on the copy while checking on the original
	 *all according to the game of life rule set
	 *public int Neighbours(int px,int py) is used to tell how much Neighbours for pixel (in previt section) 
	 */
	@Override
	public void nextGenGol() {
		int[][]copy = new int[this.getWidth()][this.getHeight()];//copy of _map
		//scan the matrix using 2 for loops and sets color by the number of neighbours
		for(int x = 0; x<this.getWidth();x++) {
			for(int y = 0; y<this.getHeight();y++) {
				if(this._map[x][y]== -1 && Neighbours(x,y)==3) {copy[x][y]=BLACK;}
				else if(this._map[x][y]!=-1 && (Neighbours(x,y)==2 ||Neighbours(x,y)== 3))
				{copy[x][y]=BLACK;}
				else {copy[x][y]=-1;}
			}
		}
		this._map = copy;
	}

	//prints the map again with the wanted color
	@Override
	public void fill(int c) {
		for(int x = 0;x<this.getWidth();x++) {
			for(int y = 0;y<this.getHeight();y++) {
				this.setPixel(x, y, c);
			}
		}

	}










	//_____________________________________________________________________________________________________//
	//				privet section 
//used in shortest path
	public int[][] Map4SP(int width,int height,int col){
		int[][] Matrix=  new int[width][height]; //making new matrix
		for(int x = 0; x<width; x++) {
			for(int y = 0; y<height ;y++) {
				if(this.getPixel(x, y) == col){Matrix[x][y] = -1;}
				else {Matrix[x][y]= -2;}
			}
		}
		return Matrix;
	}


//used in game of life
	public int Neighbours(int px,int py) {
		int ans = 0;

		for(int x =px+1;x>=px-1;x--) {
			if(x>this.getWidth()-1||x<0) {continue;}
			for(int y =py+1;y>=py-1;y--) {
				if(y>this.getHeight()-1||y<0) {continue;}
				if(x==px && y==py){continue;}
				if(this.getPixel(x,y)!= -1) {ans++;}
			}
		}
		return ans;
	}


//used in circle
	public static double distance(Point2D p1,Point2D p2) {
		return Math.sqrt(Math.pow(p1.ix()-p2.ix(),2)+Math.pow(p1.iy()-p2.iy(),2));
	}
//used in many
	public boolean is_valid(Point2D p1, Point2D p2) {
		if (p1.ix()>this.getWidth()-1||p1.ix()<0) {return false;}
		if (p1.iy()>this.getHeight()-1||p1.iy()<0) {return false;}
		if (p2.ix()>this.getWidth()-1||p2.ix()<0) {return false;}
		if (p2.iy()>this.getHeight()-1 || p2.iy()<0) {return false;}
		if (p1==null ||p2 == null) {return false;}
			
		return true;
//		if(is_valid(p1, p2)!= true) {System.err.println("invalid input"); return;} //use this
	}
	
	
	//used in many	
	public boolean is_valid(Point2D p) {
		if (p.ix()>this.getWidth()-1||p.ix()<0) {return false;}
		if (p.iy()>this.getHeight()-1||p.iy()<0) {return false;}
		if (p == null ) {return false;}
			
		return true;
//		if(is_valid(p)!= true) {System.err.println("invalid input"); return;} //use this
	}

	// this version is recursiv and grate for higher resolution
	public void drawSegment_recursiv(Point2D p1, Point2D p2, int v) {
		Point2D mid = new Point2D(Math.round((p1.ix()+p2.ix()+0.5) / 2),Math.round((p1.iy()+p2.iy()+0.5) /2));
		this.setPixel(p1, v);
		this.setPixel(p2, v);
		if(Math.abs(p1.ix()-p2.ix()) <= 1 && Math.abs(p1.iy()-p2.iy()) <= 1) {return;}
		drawSegment(p1 , mid, v);
		drawSegment(mid ,p2, v);
	}

}







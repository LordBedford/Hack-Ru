
public class Location {
	private int x;
	private int y;
	
	public Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX(){return x;}
	public int getY() {return y;}
	public void incX(int x) {this.x += x;} //increments x by given value in param
	public void incY(int y) {this.y += y;}
	
}

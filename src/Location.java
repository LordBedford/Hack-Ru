
public class Location {
	private double x;
	private double y;
	
	public Location(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double getX(){return x;}
	public double getY() {return y;}
	public void incX(double x) {this.x += x;} //increments x by given value in param
	public void incY(double y) {this.y += y;}
	
}

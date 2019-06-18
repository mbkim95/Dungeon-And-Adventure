package Model;

public class Wave {
	
	public int dir;
	public float x, y;
	public float speed;
	public int power;
	public boolean hit = false;
	
	public Wave(int dir, float x, float y, float speed, int power){
		
		this.dir = dir;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.power = power;
	}

	public boolean process(){
		
		if(dir==0)
			x -= speed;
		else
			x += speed;
		
		if(x<0 || x>1024)
			return false;
		
		return true;
	}
}

package Model;

public class Home extends Map {

	public boolean home;

	public void home(Player player) {
		player.x = 500;
		player.y = 350;
		home = true;
	}
}

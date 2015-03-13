package game.world;

public class TileGraph {

	private final int[] tiles;
	private final int width;
	
	public TileGraph(int x, int y){
		width = x-1;
		tiles = new int[x*y];

		for (int i = 0; i < tiles.length; i++){
			tiles[i] = i;
			if (i%width == 0 || i/width == 0 || i/width == y-1)
				tiles[i] = 0;
		}
	}
	
	public void join(int x1, int y1, int x2, int y2){

		if (getRoot(x2, y2) == 0)
			setRoot(x1, y1, 0);
		else if (getRoot(x1, y1) == 0)
			setRoot(x2, y2, 0);
		else
		setRoot(x1, y1, getRoot(x2, y2));
	}
	
	public int getRoot(int x1, int y1){
		int a = x1 + y1*width;
		
		int c = tiles[a];
		while (tiles[c] != c){
			c = tiles[c];
		}
		return c;
	}
	
	public void setRoot(int x1, int y1, int root){
		int a = x1 + y1*width;
		int c = tiles[a];
		while (tiles[c] != c){
			c = tiles[c];
		}
		tiles[c] = root;
	}
	
}

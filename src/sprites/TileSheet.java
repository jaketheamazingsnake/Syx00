package sprites;

import data.CONST;
import engine.QuadColor;
import engine.Renderer;
import engine.Tex;

public class TileSheet {

	private final Tex texture;
	private final float texSize;
	private final float quadSize;
	private final int tilesNr;
	private int tileX;
	private int tileY;
	
	public TileSheet(String path, int tileWidth, int tileHeight){
		this.texture = new Tex(path);
		this.texSize = tileWidth/texture.getWidth();
		this.quadSize = tileWidth*CONST.SCALE;
		this.tilesNr = (int) (texture.getHeight()/tileHeight);
	}
	
	/**
	 * 
	 * @param tile
	 * @param x1
	 * @param y1
	 * @param color
	 */
	public void render(short[] tile, float x1, float y1){
		for (int i = 0; i < tile.length; i++){
			if (tile[i] >= 0){
				tileX = tile[i]%tilesNr;
				tileY = tile[i]/tilesNr;
				Renderer.texture(x1, x1+quadSize, y1, y1+quadSize, 
						tileX*texSize, tileX*texSize+texSize, tileY*texSize, tileY*texSize+texSize,
						QuadColor.fullWhite);
			}
		}
	}
	
	/**
	 * 
	 * @param tile
	 * @param x1
	 * @param y1
	 * @param color
	 */
	public void render(short tile, float x1, float y1){
		tileX = tile%tilesNr;
		tileY = tile/tilesNr;
		Renderer.texture(x1, x1+quadSize, y1, y1+quadSize, 
				tileX*texSize, tileX*texSize+texSize, tileY*texSize, tileY*texSize+texSize,
				QuadColor.fullWhite);
	}
	
	public void flush(){
		texture.flush();
	}
	
	/**
	 * binds the texture
	 */
	public void bind(){
		texture.bind();
	}
	
	public void release(){
		texture.release();
	}
	
}

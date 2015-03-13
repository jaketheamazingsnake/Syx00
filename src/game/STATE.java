package game;

import engine.PSettings;

public interface STATE {

	public void render(float dms, GameInfo nfo);
	public int update(float dms, GameInfo nfo);
	public void hover(GameInfo nfo);
	public String hover(float ms);
	/**
	 * 
	 * @param k the key that has been pressed
	 * @return if state should be changed, the int of that state, or back if returning
	 */
	public int input(PSettings.KEY k);
	/**
	 * 
	 * @param mouseButton
	 * @param pressed
	 * @param nfo
	 * @return if state should be changed, the int of that state, or back if returning
	 */
	public int click(int mouseButton, boolean pressed, GameInfo nfo);
	
}

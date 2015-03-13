package engine;

import org.lwjgl.input.Keyboard;

import gui.MultiValueA;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

import data.Paths;

/**
 * The settings of the player
 * @author mail__000
 *
 */
public class PSettings {

	/**
	 * All available settings
	 * @author mail__000
	 *
	 */
	public enum SETT {
		
		/**
		 * play in fullscreen or not
		 */
		FULLSCREEN(1,"Fullscreen"),
		/**
		 * Vsync avaliable or not
		 */
		VSYNCH(1, "Vsynch"),
		/**
		 * which displaymode to use. Based on the displaymode your monitor
		 * haves and those who are accepted
		 */
		DISPLAYMODE(0,"Resolution"),
		/**
		 * How many FPS to update/render
		 */
		FPS(40,"FPS");
	
		private int code;
		private final int defaultValue;
		private final String label;
			
		SETT(int defaultValue, String label){
			this.defaultValue = defaultValue;
			this.label = label;
		}
		
		public int getCode(){
			return code;
		}
		
		public String getLabel(){
			return label;
		}
	}
	
	public enum KEY {
		
		NONE(Keyboard.CHAR_NONE),
		LEFT(Keyboard.KEY_A),
		RIGHT(Keyboard.KEY_D),
		UP(Keyboard.KEY_W),
		DOWN(Keyboard.KEY_S),
		MAP(Keyboard.KEY_M),
		ESCAPE(Keyboard.KEY_ESCAPE),
		SPACE(Keyboard.KEY_SPACE);
	
		private int currentKey;
		private final int defaultKey;
			
		KEY(int defaultKey){
			this.defaultKey = defaultKey;
		}
		
		public int getKeyCode(){
			return currentKey;
		}
		
	}
	
	private Properties settings;
	private final int[] settLookup;
	private final KEY[] keyLookup;
	private final LinkedList<MultiValueA.Observer> observers = new LinkedList<MultiValueA.Observer>();
	
	
	PSettings () {
		settLookup = new int[SETT.values().length];
		keyLookup = new KEY[500];
		read();
	}
	
	/**
	 * reads from disk and inits the settings
	 */
	private void read(){
		
		try {
			FileReader reader = new FileReader(Paths.Data().PlayerSettings);
			settings = new Properties();
			settings.load(reader);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int i = 0;
		for (SETT sett : SETT.values()){
			sett.code = i;
			i++;
		}
		
		for (SETT sett : SETT.values()){
			if (settings.getProperty(sett.label) == null){
				reinit();
				return;
			}
			settLookup[sett.getCode()] = Integer.valueOf(settings.getProperty(sett.label));	
		}
		
		for (KEY k : KEY.values()){
			if (settings.getProperty("key_" + k.toString()) == null){
				reinit();
				return;
			}
			keyLookup[Integer.valueOf(settings.getProperty("key_" + k.toString()))] = k;
			k.currentKey = Integer.valueOf(settings.getProperty("key_" + k.toString()));
			
		}
		
	}
	
	private void reinit(){

		for (SETT sett : SETT.values()){
			settings.put(sett.label, String.valueOf(sett.defaultValue));
		}

		for (KEY k : KEY.values()){
			settings.put("key_"+k.toString(), String.valueOf(k.defaultKey));
		}
		
		store();
		read();
	}
	
	private void store(){
		try {
			FileOutputStream fos = new FileOutputStream(Paths.Data().PlayerSettings);
			settings.store(fos, "messing with this file may corrupt the game and\n"
					+ "will probably destroy your computer. Change settings in launcher or\n"
					+ "in-game menu.");
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * stores the current values on disk
	 */
	public void save () {
		for (MultiValueA.Observer o : observers){
			settLookup[o.getCode()] = o.getCurrentValue();
		}
		
		int i = 0;
		for (SETT s : SETT.values()){
			settings.put(s.getLabel(), String.valueOf(settLookup[i]));
			i++;
		}
		
		for (KEY k : KEY.values()){
			settings.put("key_" + k.toString(), String.valueOf(k.currentKey));
		}
		
		store();
	}
	
	/**
	 * 
	 * @param s
	 * @return the value of this settings 0 = false, 1 = true
	 */
	public int getValue(SETT s){
		return settLookup[s.code];
	}
	
	/**
	 * 
	 * @param s
	 * @return value
	 */
	public boolean getBooValue(SETT s){
		if (settLookup[s.code] == 0)
			return false;
		return true;
	}
	
	/**
	 * sets all buttons and stuff to the value of the current settings
	 */
	public void restoreObservers(){
		for (MultiValueA.Observer o : observers){
			o.setCurrentValue(settLookup[o.getCode()]);
		}
	}
	
	/**
	 * Adds an observer to this
	 * @param observer
	 */
	public void addObserver(MultiValueA.Observer observer){
		if (observer.getCode() < 0 || observer.getCode() >= settLookup.length)
			try {
				throw new Exception("illegal adding of observer. Observer discarded");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		observers.add(observer);
	}
	
	public KEY getKey(int k){
		return keyLookup[k];
	}
	
	public void setKey(KEY k, int keyCode){
		KEY k2 = keyLookup[k.getKeyCode()];
		if (k2 != null)
			k2.currentKey = 0;
		keyLookup[k.getKeyCode()] = null;
		k.currentKey = keyCode;
		keyLookup[k.getKeyCode()] = k;
	}
}

package menu;

import sprites.Gui;
import sprites.RenSprite;
import gui.ClText;
import gui.GuiSection;
import gui.TextType;
import menu.MenuCore.UpdateSheet;

class ScreenMain extends SCREEN{

	private final int PLAY = 0;
	private final int SETTINGS = 1;
	private final int EXIT = 2;
	private final int CAMPAIGN = 3;
	private final int SANDBOX = 4;
	private final int MULTIPLAYER = 5;
	private final int BACK = 6;
	private final int NEW = 7;
	private final int CONTINUE = 8;
	private final int LOAD = 9;
	private final int LOC = 10;
	private final int C2 = 11;
	private final int C3 = 12;
	
	private ScreenMainBackground bg;
	
	@Override
	protected int click(UpdateSheet s){
		
		int click = sections[sectionNr].click();
		if (click < 0)
			return id;
		
		switch (click){
		case PLAY: 			sectionNr ++; 		break;
		case SETTINGS: 							break;
		case EXIT: 			s.start(null); 		break;
		case CAMPAIGN: 		sectionNr++; 		break;
		case SANDBOX: 							return MenuCore.SANDBOX;
		case MULTIPLAYER:						break;
		case BACK: 			sectionNr --; 		break;
		case NEW: 			sectionNr ++; 		break;
		case CONTINUE: 							break;
		case LOAD: 								break;
		case LOC: 								break;
		case C2:  								break;
		case C3:  								break;
		}
		
		return id;
	}
	
	ScreenMain(Resources res) { 
		super(MenuCore.MAIN, 4);
		bg = new ScreenMainBackground(res, bounds);
		reinit(res);
	}
	
	@Override
	protected boolean update(UpdateSheet s){
		bg.update(s);
		return sections[sectionNr].update(s.mouseCoo());
	}

	@Override
	protected void render(float dms) {
		bg.render(dms);
		Gui.bind();
		sections[sectionNr].render(dms);
		
	}
	
	@Override
	protected void reinit(Resources res) {
		
		bg.reinit(res, bounds);
		
		sections[0] = new GuiSection(res.select1, res.click1);
		sections[1] = new GuiSection(res.select1, res.click1);
		sections[2] = new GuiSection(res.select1, res.click1);
		sections[3] = new GuiSection(res.select1, res.click1);
		
		
		RenSprite logo = new RenSprite(Gui.sprites.logo_menu, 0, bounds.getY1());
		logo.centerX(bounds.getX1(), bounds.getX2());
		logo.centerY(bg.getY1(), bg.getY2()-200);
		
		
		ClText temp;
		sections[0].add(logo);
		temp = new ClText(PLAY, "Play", 0, logo.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[0].add(temp);
		temp = new ClText(SETTINGS, "Options", 0, temp.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[0].add(temp);
		temp = new ClText(EXIT, "Quit", 0, temp.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[0].add(temp);
		sections[0].init();
		
		sections[1].add(logo);
		temp = new ClText(CAMPAIGN, "Campaign", 0, logo.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[1].add(temp);
		temp = new ClText(SANDBOX, "SandBox", 0, temp.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[1].add(temp);
		temp = new ClText(MULTIPLAYER, "Multiplayer", 0, temp.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[1].add(temp);
		temp = new ClText(BACK, "Back", 0, temp.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[1].add(temp);
		sections[1].init();
		
		sections[2].add(logo);
		temp = new ClText(NEW, "New", 0, logo.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[2].add(temp);
		temp = new ClText(CONTINUE, "Continue", 0, temp.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[2].add(temp);
		temp = new ClText(LOAD, "Load", 0, temp.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[2].add(temp);
		temp = new ClText(BACK, "Back", 0, temp.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[2].add(temp);
		sections[2].init();
		
		sections[3].add(logo);
		temp = new ClText(LOC, "Lord of Cerebus", 0, logo.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[3].add(temp);
		temp = new ClText(C2, "Campaign", 0, temp.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[3].add(temp);
		temp = new ClText(C3, "Campaign", 0, temp.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[3].add(temp);
		temp = new ClText(BACK, "Back", 0, temp.getY2(), TextType.Menu);
		temp.centerX(bounds.getX1(), bounds.getX2());
		sections[3].add(temp);
		sections[3].init();
		
	}

}

package menu;

import sprites.Gui;
import util.Direction;
import gui.ClText;
import gui.TextType;
import menu.MenuCore.UpdateSheet;

public class ScreenSandbox extends SCREEN{

	ClText start;
	private final int START = 0;
	
	
	ScreenSandbox(Resources res) {
		super(MenuCore.SANDBOX, 1);
		reinit(res);
	}

	@Override
	boolean update(UpdateSheet s) {
		if (start.hover(s.mouseCoo()) > -1)
		return true;
		return false;
	}

	@Override
	int click(UpdateSheet s) {
		if (start.click())
			s.start(new GameSpark());
		return 0;
	}

	@Override
	void render(float dms) {
		Gui.bind();
		start.render(dms);
		
	}

	@Override
	void reinit(Resources res) {
		start = new ClText(START, "Start", 0, 0, TextType.Menu);
		start.centerIn(bounds, Direction.C);
	}

}

package rach.android_swzom;

import rach.android_swzom.framework.Screen;
import rach.android_swzom.framework.impl.AndroidGame;

public class MyGame extends AndroidGame {

	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}

}

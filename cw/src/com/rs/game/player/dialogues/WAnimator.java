// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemMessage.java

package com.rs.game.player.dialogues;

// Referenced classes of package com.rs.game.player.dialogues:
//            Dialogue

public class WAnimator extends Dialogue {

	public WAnimator() {
	}

	public void start() {
		sendDialogue(
				"The animator hums. Something seems to be working.",
				"You stand back..." );
	}

	public void run(int interfaceId, int componentId) {
		end();
	}

	public void finish() {
	}
}

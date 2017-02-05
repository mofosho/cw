package com.rs.game.player.dialogues;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.utils.ShopsHandler;

public class Shanomi extends Dialogue {

    public Shanomi() {
    }

    public void start() {
        npcId = ((Integer) parameters[0]).intValue();
        sendEntityDialogue(
                (short) 243,
                new String[]{
                    NPCDefinitions.getNPCDefinitions(npcId).name,
                    "Hello, warrior. My name is Shanomi, and you shall address",
                    "me with respect. I have a shop as to which you might",
                    "be interested. It is your choice to see it or not."},
                (byte) 1, npcId, DialAnims.HAPPY_TALKING.getAnim());
    }

    public void run(int interfaceId, int componentId) {
        if (stage == -1) {
            stage = 0;
            sendDialogue(
                        "What would you like to say?",
                        "I would like to see it, honorable Shanomi.",
                        "Nothing, never mind.");
        } else if (stage == 0) {
            switch (componentId) {
                case 1:
                    ShopsHandler.openShop(player, 46);
                    break;
                case 2:
                    end();
                    break;
            }
        } else {
            end();
        }
    }

    public void finish() {
    }
    int npcId;
}

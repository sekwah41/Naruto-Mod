package com.sekwah.narutomod.capabilities;

import com.sekwah.narutomod.capabilities.toggleabilitydata.ToggleAbilityData;

/**
 * This is to track the double jump status of the player.
 *
 * Including additional client variables which will be reset every time the value recieves a packet syncing.
 *
 * Though this is to ensure the player doesn't get stuck in a broken state.
 */
public class DoubleJumpData {
    /**
     * Only this value will be used to sync with the client. The rest are either for the server or to block a broken state on network lag.
     */
    public boolean canDoubleJumpServer;

    public boolean canDoubleJumpClient;

    public int diffUpdateTicksClient = 0;

    /**
     * This will likely only end up being used on client side.
     * @param canDoubleJump
     */
    public DoubleJumpData(boolean canDoubleJump) {
        this.canDoubleJumpServer = canDoubleJump;
        this.canDoubleJumpClient = canDoubleJump;
    }

    public void stuckCheck() {
        this.diffUpdateTicksClient++;
        if(this.canDoubleJumpClient != this.canDoubleJumpServer && this.diffUpdateTicksClient > 20) {
            this.canDoubleJumpClient = this.canDoubleJumpServer;
        }
    }

    public void clientJump() {
        this.canDoubleJumpClient = false;
        this.diffUpdateTicksClient = 0;
    }

    /**
     * Abusing this so the server only flags for updates if its jump status is different.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof DoubleJumpData doubleJumpData) {
            return doubleJumpData.canDoubleJumpServer == this.canDoubleJumpServer;
        } else {
            return false;
        }
    }
}

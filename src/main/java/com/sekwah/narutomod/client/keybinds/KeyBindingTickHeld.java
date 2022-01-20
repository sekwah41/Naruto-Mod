package com.sekwah.narutomod.client.keybinds;

import net.minecraft.client.KeyMapping;

// TODO add a way to store or handle what the value was when the key was released

/**
 * If a click consumer is added then update will automatically consume the clicks.
 */
public class KeyBindingTickHeld extends KeyMapping {

    boolean hasConsumedClickState = false;

    int heldTicks = 0;
    private Runnable onClick = null;

    public KeyBindingTickHeld(String name, int keyCode, String category) {
        super(name, keyCode, category);
    }

    public void registerClickConsumer(Runnable runnable) {
        this.onClick = runnable;
    }

    public enum KeyState {
        CLICK,
        HELD,
        NOT_PRESSED
    }

    public void update() {
        if (this.isDown()) {
            heldTicks++;
        }
        if (this.onClick != null) {
            KeyState clickState = this.consumeClickState();
            if(clickState == KeyState.CLICK) {
                onClick.run();
            }
        }
    }

    // Could be a little overkill though should make keys more consistent
    public KeyState consumeClickState() {
        if (this.isDown()) {
            if(hasConsumedClickState) {
                return KeyState.HELD;
            }
            hasConsumedClickState = true;
            return KeyState.CLICK;
        } else {
            if(this.clickCount == 0) {
                return KeyState.NOT_PRESSED;
            } else {
                --this.clickCount;
                return KeyState.CLICK;
            }
        }
    }

    /**
     * People wont be rapidly using this so its fine to miss this by mistake. Its more to help abilities like the leap.
     *
     * Depending on the key you may want to check against a held threshold to see if it should be activated or not
     *
     * @return
     */
    public int consumeReleaseDuration() {
        if (this.isDown()) {
            return 0;
        }

        int returnValue = heldTicks;
        heldTicks = 0;
        return returnValue;
    }

    /**
     * Helpful for animations and checking if its past the threshold
     * @return
     */
    public int currentHeldValue() {
        return heldTicks;
    }

    /**
     * Check if the key is currently being held
     * @return
     */
    public boolean isCurrentlyHeld() {
        return heldTicks > 20 && this.isDown();
    }

    @Override
    public void setDown(boolean down) {
        // Resets clicks to 0 if extra clicks are a result of a consumed click state. Should account for lag properly.
        if(this.isDown() && !down) {
            if(hasConsumedClickState && this.clickCount > 0) {
                this.clickCount = 0;
            }
            hasConsumedClickState = false;
        }
        else if(!this.isDown() && down) {
            this.heldTicks = 0;
        }

        super.setDown(down);
    }
}

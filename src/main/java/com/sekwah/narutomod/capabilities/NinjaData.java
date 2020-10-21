package com.sekwah.narutomod.capabilities;

public class NinjaData implements INinjaData {

    private float chakra;
    private float stamina;

    @Override
    public float getChakra() {
        return this.chakra;
    }

    @Override
    public float getStamina() {
        return this.stamina;
    }

    @Override
    public void setChakra(float chakra) {
        this.chakra = chakra;
    }

    @Override
    public void setStamina(float stamina) {
        this.stamina = stamina;
    }
}

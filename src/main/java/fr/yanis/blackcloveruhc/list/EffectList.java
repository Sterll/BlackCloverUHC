package fr.yanis.blackcloveruhc.list;

public enum EffectList {

    SPEED1(20),
    SPEED2(40),
    SPEED3(60),
    RESISTANCE1(15),
    RESISTANCE2(30),
    STRENGTH1(15),
    STRENGTH2(30);

    private int multiplier;
    EffectList(int multiplier){
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }
}

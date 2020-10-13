package com.nick777.tgdr.common.ai;

public class StaticAI {

    public Decision currentDec = new Decision();

    public void makeDecision() {
        Decision decision = new Decision();
        decision.vel.x = 0;
        decision.vel.y = 0;
        currentDec = decision;
    }
}

package com.autoflow.agent;

import java.lang.instrument.Instrumentation;

public class AutoFlowAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("[AutoFlow] Agent loaded.");
        inst.addTransformer(new AutoFlowTransformer());
    }
}

package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import java.util.Random;

public class AOSAnimation {

    public static String getAnimation() {
        String[] efectos = {
                "fade-up", "fade-down", "fade-left", "fade-right",
                "flip-left", "flip-right", "zoom-in", "zoom-in-up",
                "zoom-in-down", "zoom-out"
        };

        Random random = new Random();
        int index = random.nextInt(efectos.length);

        return "data-aos=\"" + efectos[index] + "\"";
    }
}

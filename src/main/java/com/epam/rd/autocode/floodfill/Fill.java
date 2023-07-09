package com.epam.rd.autocode.floodfill;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Fill implements FloodFill {

    @Override
    public void flood(String map, FloodLogger logger) {
        logger.log(map);
        boolean isFill = true;
        String[] str = map.split("\n");
        char[][] place = new char[str.length][];
        char[][] placeFill = new char[str.length][];
        for (int i = 0; i < place.length; i++) {
            place[i] = str[i].toCharArray();
            placeFill[i] = str[i].toCharArray();
        }
        for (int i = 0; i < place.length; i++) {
            for (int j = 0; j < place[0].length; j++) {
                if (place[i][j] == WATER) {
                    if (j > 0) {
                        placeFill[i][j - 1] = WATER;
                    }
                    if (j < place[0].length - 1) {
                        placeFill[i][j + 1] = WATER;
                    }
                    if (i > 0) {
                        placeFill[i - 1][j] = WATER;
                    }
                    if (i < place.length - 1) {
                        placeFill[i + 1][j] = WATER;
                    }
                } else {
                    isFill = false;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        System.out.println();
        for (char[] s : placeFill) {
            for (char c : s) {
                sb.append(c);
            }
            sb.append("\n");
        }
        sb.setLength(sb.length()-1);
        map = sb.toString();
        if (!isFill) {
            flood(map, logger);
        }
    }
}

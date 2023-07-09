package com.epam.rd.autocode.floodfill;

import java.util.ArrayList;
import java.util.List;

public class Logger implements FloodLogger{
    List logger=new ArrayList();
    @Override
    public void log(String floodState) {
        logger.add(floodState);
    }
}

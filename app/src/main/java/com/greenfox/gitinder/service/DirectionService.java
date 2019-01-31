package com.greenfox.gitinder.service;

import android.graphics.Path;

import com.yuyakaido.android.cardstackview.Direction;

public class DirectionService {

    public static String directionToString(Direction direction){
        String directionString = "";
            if (direction.equals(Direction.Right)){
                directionString = "right";
            } else if (direction.equals(Direction.Left)){
                directionString = "left";
            }

        return directionString;
    }
}

package org.mowitnow.mower;


import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Lawn {
    private List<Mower> mowers;

    private Integer xMax;
    private Integer yMax;

    public Lawn(List<Mower> mowers, Integer xMax, Integer yMax) {
        this.mowers = mowers;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        for (var mower : mowers) {
            stringBuilder.append(mower.getX());
            stringBuilder.append(StringUtils.SPACE);
            stringBuilder.append(mower.getY());
            stringBuilder.append(StringUtils.SPACE);
            stringBuilder.append(mower.getOrientation().getCode());
            stringBuilder.append(System.lineSeparator());
        }
        stringBuilder.append("NB");
        return stringBuilder.toString();
    }

    public List<Mower> getMowers() {
        return mowers;
    }

    public void setMowers(List<Mower> mowers) {
        this.mowers = mowers;
    }

    public Integer getxMax() {
        return xMax;
    }

    public void setxMax(Integer xMax) {
        this.xMax = xMax;
    }

    public Integer getyMax() {
        return yMax;
    }

    public void setyMax(Integer yMax) {
        this.yMax = yMax;
    }
}

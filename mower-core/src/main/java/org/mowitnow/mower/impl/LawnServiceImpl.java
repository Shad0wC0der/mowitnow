package org.mowitnow.mower.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.mowitnow.mower.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class LawnServiceImpl implements LawnService {

    private MowerService mowerService;

    public LawnServiceImpl(MowerService mowerService) {
        this.mowerService = mowerService;
    }

    @Override
    public Lawn loadInput(File file) throws IOException {
        var lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        var size = lines.get(0).split(StringUtils.SPACE);
        var xMax = Integer.valueOf(size[0]);
        var yMax = Integer.valueOf(size[1]);
        var mowers = buildMowers(lines);
        return new Lawn(mowers, xMax, yMax);
    }

    private static ArrayList<Mower> buildMowers(List<String> lines) {
        var mowers = new ArrayList<Mower>();
        for (int i = 1; i < lines.size(); i += 2) {
            addMower(lines, mowers, i);
        }
        return mowers;
    }

    private static void addMower(List<String> lines, ArrayList<Mower> mowers, int i) {
        var mowerAttributes = lines.get(i).split(StringUtils.SPACE);
        var mowerActions = lines.get(i + 1);
        List<Action> actions = getActions(mowerActions);
        var mower = new Mower(Integer.valueOf(mowerAttributes[0]),
                Integer.valueOf(mowerAttributes[1]),
                Orientation.fromCode(mowerAttributes[2].charAt(0)), actions);
        mowers.add(mower);
    }

    private static List<Action> getActions(String mowerActions) {
        var characterStream = mowerActions.chars().mapToObj(value -> (char) value);
        var actions = characterStream.map(Action::fromCode).toList();
        return actions;
    }

    @Override
    public void doExplorations(Lawn lawn) {
        for (var mower : lawn.getMowers()) {
            for (var action : mower.getActions()) {
                mowerService.doAction(mower, action, lawn);
            }
        }
    }
}

package org.mowitnow.mower.batch;

import org.mowitnow.mower.Lawn;
import org.mowitnow.mower.LawnService;
import org.springframework.batch.item.ItemProcessor;

public class MowerProcessor implements ItemProcessor<Lawn, Lawn> {

    private LawnService lawnService;

    public MowerProcessor(LawnService lawnService) {
        this.lawnService = lawnService;
    }

    @Override
    public Lawn process(Lawn item) {
        lawnService.doExplorations(item);
        return item;
    }
}

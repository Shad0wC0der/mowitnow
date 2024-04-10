package org.mowitnow.mower;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

public interface LawnService {
    public Lawn loadInput(File file) throws IOException;

    public void doExplorations(Lawn lawn);
}

package org.mowitnow.mower.impl;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class MowerServiceImplTest {

    @Test
    void requestedTest() throws IOException {
        var lawnService = new LawnServiceImpl(new MowerServiceImpl());
        var lawn = lawnService.loadInput(new File("src/test/resources/input"));
        lawnService.doExplorations(lawn);
        Approvals.verify(lawn);
    }
}
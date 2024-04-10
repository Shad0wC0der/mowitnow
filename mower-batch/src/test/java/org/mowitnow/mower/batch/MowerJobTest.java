package org.mowitnow.mower.batch;

import org.apache.commons.io.FileUtils;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
class MowerJobTest {
    @Autowired
    private Job mowerJob;
    @Autowired
    private JobLauncher jobLauncher;

    @Test
    void should_return_the_kata_expected_file() throws Exception {
        var launcher = new JobLauncherTestUtils();
        launcher.setJob((Job) mowerJob);
        launcher.setJobLauncher(jobLauncher);
        launcher.launchJob();
        Approvals.verify(FileUtils.readFileToString(new File("src/main/resources/output")));
    }
}
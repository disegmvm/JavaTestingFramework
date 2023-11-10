package com.epam.rp.runners;

import com.epam.rp.api.tests.steps.DashboardApiSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.Arrays;
import java.util.List;

public class JBehaveApiRunner extends JUnitStories {

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withFailureTrace(true)
                                .withFailureTraceCompression(true)
                                .withDefaultFormats()
                                .withFormats(Format.HTML, Format.TXT, Format.XML, Format.CONSOLE)
                );
    }

    @Override
    public Embedder configuredEmbedder() {
        Embedder embedder = super.configuredEmbedder();
        embedder.embedderControls()
                .doGenerateViewAfterStories(true)
                .doIgnoreFailureInStories(true)
                .doIgnoreFailureInView(false)
                .useThreads(2);
        return embedder;
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new DashboardApiSteps());
    }

    @Override
    public List<String> storyPaths() {
        return Arrays.asList("stories/api/Dashboard.story");
    }
}

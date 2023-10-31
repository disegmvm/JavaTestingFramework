package com.epam.rp.runners;

import com.epam.rp.api.tests.steps.DashboardApiSteps;
import com.epam.rp.ui.steps.DashboardUiSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.RethrowingFailure;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.Arrays;
import java.util.List;

public class JBehaveRunner extends JUnitStories {

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
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new DashboardUiSteps());
    }

    @Override
    public List<String> storyPaths() {
        return Arrays.asList("stories/ui/Dashboard.story");
    }
}

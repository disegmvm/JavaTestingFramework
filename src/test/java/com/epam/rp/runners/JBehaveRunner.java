package com.epam.rp.runners;

import com.epam.rp.api.tests.steps.DashboardApiSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.Arrays;
import java.util.List;

public class JBehaveRunner extends JUnitStories {

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration();
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

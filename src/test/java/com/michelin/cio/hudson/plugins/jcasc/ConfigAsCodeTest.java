package com.michelin.cio.hudson.plugins.jcasc;

import io.jenkins.plugins.casc.ConfigurationAsCode;
import io.jenkins.plugins.casc.ConfiguratorException;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class ConfigAsCodeTest {

    @Rule
    public JenkinsRule r = new JenkinsRule();

    @Test
    public void should_support_configuration_as_code() throws Exception {
        configure();
    }

    private void configure() throws ConfiguratorException {
        String s = getClass().getResource("/configuration-as-code.yml").toString();
        ConfigurationAsCode.get().configure(s);
    }

    @Test
    public void export_configuration() throws Exception {
        configure();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ConfigurationAsCode.get().export(out);
        String exportedConfig = out.toString();
        assertThat(exportedConfig, containsString("password-something"));
    }
}
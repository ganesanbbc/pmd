package com.cts.day1;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Properties;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "classpath:features/" },
		glue = "com.foreach.cuke",
		format = {
				"json:target/cucumber/cucumber-report.json",
				"html:target/cucumber/plain-html-reports",
				"com.foreach.cuke.core.formatter.ConsoleReporter"
		}
)
public class Day1ApplicationTests {

	@Autowired
	@Qualifier("dataProperties")
	private Properties dataProperties;

	@PostConstruct
	public void bootApplication() {
		EmbeddedWebApplicationContext webApplicationContext
				= (EmbeddedWebApplicationContext) SpringApplication.run( Day1Application.class );

		// retrieve the actual webserver port
		int webServerPort = webApplicationContext.getEmbeddedServletContainer().getPort();

		// modify the rest.base.url, replace the default port by the actual
		String restBaseUrl = dataProperties.get( "rest.base.url" ).toString();
		dataProperties.put( "rest.base.url", StringUtils.replace( restBaseUrl, "8080", "" + webServerPort ) );
	}

}

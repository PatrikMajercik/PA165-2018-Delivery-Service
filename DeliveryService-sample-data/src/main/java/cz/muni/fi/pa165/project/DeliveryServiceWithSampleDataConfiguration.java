package cz.muni.fi.pa165.project;


import cz.muni.fi.pa165.project.SampleDataLoadingFacade;
import cz.muni.fi.pa165.project.configuration.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author Patrik Majercik
 */

@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class DeliveryServiceWithSampleDataConfiguration {

    @Autowired
    SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() throws IOException {
        //log.debug("dataLoading()");
        sampleDataLoadingFacade.loadData();
    }

}
package org.example.rest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CarServiceTestCaseStructure {

    @Deployment(testable = true)
    public static Archive<?> createTestArchive() {
        return Deployments.createDeployment();
    }
}

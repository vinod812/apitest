package utility;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

public class CustomReporter implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        // Implement your custom report generation logic here
        for (ISuite suite : suites) {
            System.out.println("Suite Name: " + suite.getName());
            suite.getResults().forEach((name, result) -> {
                System.out.println("Test Name: " + name);
                System.out.println("Passed Tests: " + result.getTestContext().getPassedTests().size());
                System.out.println("Failed Tests: " + result.getTestContext().getFailedTests().size());
                System.out.println("Skipped Tests: " + result.getTestContext().getSkippedTests().size());
            });
        }
    }
}

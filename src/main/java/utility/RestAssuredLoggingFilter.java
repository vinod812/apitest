package utility;


import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import com.aventstack.extentreports.Status;

public class RestAssuredLoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        // Log request details
        ExtentReportListener.getTest().log(Status.INFO, "Request URI: " + requestSpec.getURI());
        ExtentReportListener.getTest().log(Status.INFO, "Request Method: " + requestSpec.getMethod());
        ExtentReportListener.getTest().log(Status.INFO, "Request Headers: " + requestSpec.getHeaders().toString());
        if (requestSpec.getBody() != null) {
            ExtentReportListener.getTest().log(Status.INFO, "Request Body: " + requestSpec.getBody());
        }
        // Proceed with the request and get the response
        Response response = ctx.next(requestSpec, responseSpec);

        // Log response details
        ExtentReportListener.getTest().log(Status.INFO, "Response Status Code: " + response.getStatusCode());
        ExtentReportListener.getTest().log(Status.INFO, "Response Headers: " + response.getHeaders().toString());
        ExtentReportListener.getTest().log(Status.INFO, "Response Body: " + response.getBody().asString());

        return response;
    }
}

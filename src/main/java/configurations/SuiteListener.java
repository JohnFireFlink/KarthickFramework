package configurations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.Reporter;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

public class SuiteListener implements ISuiteListener {
	
	@Override
    public void onFinish(ISuite suite) {
        // Get suite name dynamically at runtime
        String suiteName = suite.getName();
       
        try {
			sendHtmlReport("./target/Report.html", suiteName, DefaultValues.TOEMAILGROUP);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public static void sendHtmlReport(String reportPath, String suite, String[] emails) throws IOException {
        // Sender and receiver
        String fromEmail = DefaultValues.FROMEMAIL; // must be verified in SendGrid
        String subject = suite+" Execution Report";

        // Read your HTML report
        byte[] fileContent = Files.readAllBytes(Paths.get(reportPath));
        String encodedFile = Base64.getEncoder().encodeToString(fileContent);

        // Build email
        Email from = new Email(fromEmail, "Suite Execution Report");
        Content content = new Content("text/plain", "Hi Team,\n\nPlease find the attached automation execution report of "+suite+"\n\nRegards,\nEasyAutomation Team");
       
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setSubject(subject);
        mail.addContent(content);
        
        Personalization personalization = new Personalization();
        for (String email : emails) {
        	personalization.addTo(new Email(email));
		}
        mail.addPersonalization(personalization);
        
        // Attach the HTML report
        Attachments attachment = new Attachments();
        attachment.setContent(encodedFile);
        attachment.setType("text/html");
        attachment.setFilename("AutomationReport.html");
        attachment.setDisposition("attachment");
        mail.addAttachments(attachment);

        //Use your SendGrid API key
        String apiKey = System.getProperty("apiKey");
        SendGrid sg = new SendGrid(apiKey);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            Reporter.log("Status Code: " + response.getStatusCode(), true);
            Reporter.log("Response Body: " + response.getBody(), true);
            Reporter.log("Report email sent successfully!", true);
        } catch (IOException ex) {
            throw ex;
        }
    }
	
	
}

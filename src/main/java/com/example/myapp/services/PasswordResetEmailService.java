package com.example.myapp.services;

import com.sendgrid.*;
import java.io.IOException;

public class PasswordResetEmailService {
	public Response sendPasswordResetEmail(String emailId,String link) throws IOException {
    Email from = new Email("webdev-shubham-rastogi@fun.com");
    String subject = "Password Reset Email";
    Email to = new Email(emailId);
    //String link="";
    Content content = new Content("text/plain", "This is a verification email sent from the server."
    		+ " Please click on the link given below to reset the password.\n"
    		+ "Link: "+link);
    Mail mail = new Mail(from, subject, to, content);
    //SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
    SendGrid sg = new SendGrid("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");
    
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getBody());
      return response;
    } catch (IOException ex) {
      throw ex;
    }
  }
}

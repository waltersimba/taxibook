package co.za.rightit.messaging.email;

import java.util.ArrayList;
import java.util.List;

public class EmailMessage {

	private String senderName;
	private String senderEmail;
	private List<String> recipients;
	private String subject;
	private String message;
	private EmailOptions emailOptions;
	private EmailContentType contentType;
	
	public EmailMessage(EmailMessageBuilder builder) {
		this.senderName = builder.senderName;
		this.senderEmail = builder.senderEmail;
		this.recipients = builder.recipients;
		this.subject = builder.subject;
		this.message = builder.message;
		this.emailOptions = builder.emailOptions;
		this.contentType = builder.contentType;
	}
		
	public String getSenderName() {
		return senderName;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}
	
	public EmailOptions getEmailOptions() {
		return emailOptions;
	}

	public EmailContentType getContentType() {
		return contentType;
	}

	public static class EmailMessageBuilder {
		private String senderName;
		private String senderEmail;
		private List<String> recipients;
		private String subject;
		private String message;
		private EmailOptions emailOptions;
		private EmailContentType contentType;
		
		public EmailMessageBuilder withSenderName(String senderName) {
			this.senderName = senderName;
			return this;
		}
		
		public EmailMessageBuilder withSenderEmail(String senderEmail) {
			this.senderEmail = senderEmail;
			return this;
		}
		
		public EmailMessageBuilder withRecipients(List<String> recipients) {
			this.recipients = new ArrayList<>();
			this.recipients.addAll(recipients);
			return this;
		}
		
		public EmailMessageBuilder withRecipient(String recipient) {
			this.recipients = new ArrayList<>();
			this.recipients.add(recipient);
			return this;
		}
		
		public EmailMessageBuilder withSubject(String subject) {
			this.subject = subject;
			return this;
		}
		
		public EmailMessageBuilder withMessage(String message) {
			this.message = message;
			return this;
		}
		
		public EmailMessageBuilder withContentType(EmailContentType contentType) {
			this.contentType = contentType;
			return this;
		} 
		
		public EmailMessageBuilder withEmailOptions(EmailOptions emailOptions) {
			this.emailOptions = emailOptions;
			return this;
		}
		
		public EmailMessage build() {
			return new EmailMessage(this);
		}
	}
	
	public enum EmailContentType {
		TEXT,
		HTML
	}
}

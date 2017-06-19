package co.za.rightit.messaging.web.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import co.za.rightit.messaging.email.CachingFileEmailAccountRepository;
import co.za.rightit.messaging.email.EmailAccountRepository;
import co.za.rightit.messaging.email.FileEmailAccountRepository;
import co.za.rightit.messaging.email.template.TemplateServiceRepository;
import co.za.rightit.messaging.web.api.EmailContactUsReplyProcessor;
import co.za.rightit.messaging.web.api.EmailContactUsRequestProcessor;
import co.za.rightit.messaging.web.model.ApplicationOptions;

public class MessagingModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(EmailContactUsRequestProcessor.class).asEagerSingleton();
		bind(EmailContactUsReplyProcessor.class).asEagerSingleton();
		bind(String.class).annotatedWith(Names.named("email-account-cache-spec")).toInstance("expireAfterAccess=30d");
		bind(String.class).annotatedWith(Names.named("template-service-cache-spec")).toInstance("expireAfterAccess=1d");
	}
	
	@Singleton
	@Provides
	public EmailAccountRepository emailAccountRepository(ApplicationOptions options, @Named("email-account-cache-spec") String cacheSpec) {
		return new CachingFileEmailAccountRepository(new FileEmailAccountRepository(options.getEmailAccountsFile()), cacheSpec);
	}
	
	@Singleton
	@Provides
	public TemplateServiceRepository templateServiceRepository(@Named("email-account-cache-spec") String cacheSpec, EmailAccountRepository emailAccountRepository) {
		return new TemplateServiceRepository(cacheSpec, emailAccountRepository);
	}

}

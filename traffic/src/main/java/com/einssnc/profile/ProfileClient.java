package com.einssnc.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile(value="client")
@PropertySource({"classpath:client/application.properties"})
public class ProfileClient {

}

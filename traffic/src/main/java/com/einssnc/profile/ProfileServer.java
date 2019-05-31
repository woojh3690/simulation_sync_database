package com.einssnc.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile(value="server")
@PropertySource({"classpath:server/application.properties"})
public class ProfileServer {

}

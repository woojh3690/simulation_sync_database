package com.einssnc.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({ ProfileClient.class, ProfileServer.class})
@Configuration
public class ProfileConfig {
	
}

package com.polarbookshop.catalogservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.cloud.context.config.annotation.RefreshScope

@ConstructorBinding
@ConfigurationProperties("polar")
data class PolarProperties(
    /**
     * A message to welcome users.
     */
    val greeting: String
)
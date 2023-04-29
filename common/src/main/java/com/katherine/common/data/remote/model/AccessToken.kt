package com.katherine.common.data.remote.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.katherine.common.data.remote.Api.ACCESS_TOKEN_VALUE

@JsonIgnoreProperties(ignoreUnknown = true)
data class AccessToken(@JsonProperty(ACCESS_TOKEN_VALUE) var value: String)
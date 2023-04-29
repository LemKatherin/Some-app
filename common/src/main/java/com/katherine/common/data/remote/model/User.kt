package com.katherine.common.data.remote.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.katherine.common.data.remote.Api

data class User(
    @JsonProperty(Api.USER_NAME) val username: String,
    @JsonProperty(Api.USER_PASSWORD) val password: String
)
package com.github.ftfetter.webfluxdemo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Document(collection = "tweets")
data class Tweet(@Id val id: String?, @NotBlank @Size(max = 140) var text: String, val createdAt: LocalDateTime = LocalDateTime.now())
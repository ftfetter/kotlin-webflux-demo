package com.github.ftfetter.webfluxdemo.repository

import com.github.ftfetter.webfluxdemo.model.Tweet
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.mongodb.repository.Tailable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface TweetRepository : ReactiveMongoRepository<Tweet, String> {

    @Tailable
    fun findWithTailableCursorBy(): Flux<Tweet>
}
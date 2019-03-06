package com.github.ftfetter.webfluxdemo.controller

import com.github.ftfetter.webfluxdemo.model.Tweet
import com.github.ftfetter.webfluxdemo.repository.TweetRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/tweets")
class TweetController(val tweetRepository: TweetRepository) {

    @GetMapping
    fun getAllTweets() = tweetRepository.findAll()

    @PostMapping
    fun createTweets(@Valid @RequestBody tweet: Tweet) = tweetRepository.save(tweet)

    @GetMapping("/{id}")
    fun getTweetById(@PathVariable("id") tweetId: String) = tweetRepository.findById(tweetId)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @PutMapping("/{id}")
    fun updateTweet(@PathVariable("id") tweetId: String, @Valid @RequestBody tweet: Tweet) = tweetRepository.findById(tweetId)
            .map { it.text = tweet.text; return@map tweetRepository.save(it) }
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun deleteTweet(@PathVariable("id") tweetId: String) = tweetRepository.findById(tweetId)
            .map { tweetRepository.delete(it) }
            .map { ResponseEntity<Void>(HttpStatus.OK) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @GetMapping("/stream", produces = [TEXT_EVENT_STREAM_VALUE])
    fun streamAllTweets() = tweetRepository.findWithTailableCursorBy()
}
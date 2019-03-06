## Spring WebFlux Demo written in Kotlin

My version of WebFlux Demo application from the article:  
https://www.callicoder.com/reactive-rest-apis-spring-webflux-reactive-mongo  
  
I've made some changes on the URIs and add other MongoDB features to stream tweets.
To enable the stream feature from Mongo, create your Collection via script setting the `capped` value `true`.  
Example: `db.createCollection("tweets", { capped: true, size: 10000, max: 1000 });`  

Have fun :)
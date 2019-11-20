
# urlShortener
This is a Spring Boot project for Url shortener

**Algorithms**
 - I am encoding unique counter sequence into Base62 to get the unique
   key.
   
 **Analytics**
 - storing totl number of hits on short url. storing other data such as browser , operating system and their respective view.
 - entire process is aynchronous so that url redirection does not get delayed and response time is maintained. 
 
 **deployment**
 - this app can be deployed in any containerised platform Ex. Aws ECS(elastic containerization service)
 - or it can be deployed in separate EC2 instaces
 - all instances have to share the Aws elasticache redis distributed server and mongodb

**DataStorage**

**`MongoDb`**
 - used for storing user mapping and analytics data
 - set below three prperties to configure redis in application.properties file 
 
   -  **`spring.cache.type=redis`**
   -  **`spring.redis.host=127.0.0.1`**
   - **`spring.redis.port=6379`**

**`Redis`**
 - used for storing distributed counter and caching original url. so that while resolving the shorturl application does not need to hit the database
 - set below property to configure mongo db in application.properties file
	 -  **`spring.data.mongodb.uri=mongodb+srv`**

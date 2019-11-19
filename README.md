# urlShortener


**Algorithms**
 - I am encoding unique counter sequence into Base62 to get the unique
   key.

**DataStorage**

**`MongoDb`**
 - used for storing user mapping and analytics data

**`Redis`**
 - used for storing distributed counter and caching original url. so that while resolving the shorturl application does not need to hit the database

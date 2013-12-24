urlshortener: A Java client for URL shortners
=============================================

[![Build Status](https://travis-ci.org/maginatics/urlshortener.png)](https://travis-ci.org/maginatics/urlshortener)

Usage:

```java
    UrlShortener shortener = UrlShorteners.bitlyUrlShortener(bitlyAccessToken);
    // Or googleUrlShortener(googleApiKey)
    URL shortUrl = shortener.shorten(longUrl);
```

That's it!

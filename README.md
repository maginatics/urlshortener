urlshortener: A Java client for URL shortners
=============================================

Usage:

```java
    UrlShortener shortener = UrlShorteners.bitlyUrlShortener(bitlyAccessToken);
    // Or googleUrlShortener(googleApiKey)
    URL shortUrl = shortener.shorten(longUrl);
```

That's it!

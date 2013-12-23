urlshortener: A Java client for URL shortening services
=======================================================“

Usage:

    UrlShortener shortener = UrlShorteners.bitlyUrlShortener(
        bitlyAccessToken);
    // Or googleUrlShortener(googleApiKey)
    URL shortUrl = shortener.shorten(longUrl);

That's it!

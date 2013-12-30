urlshortener: A Java client for URL shortners
=============================================

[![Build Status](https://travis-ci.org/maginatics/urlshortener.png)](https://travis-ci.org/maginatics/urlshortener)

This is a dead simple client for interacting with various URL shortening services. Supported services: [bit.ly](https://bit.ly) and [goo.gl](https//goo.gl). [Diwaker Gupta](https://github.com/diwakergupta) originally wrote urlshortener at [Maginatics](https://github.com/maginatics).

Usage:

```java
    UrlShortener shortener = UrlShorteners.bitlyUrlShortener(bitlyAccessToken);
    // Or googleUrlShortener(googleApiKey)
    URL shortUrl = shortener.shorten(longUrl);
```

That's it!

Get It
------

urlshortener is available via Maven Central:

```xml
<dependency>
    <groupId>com.maginatics</groupId>
    <artifactId>urlshortener</artifactId>
    <version>0.3.0</version>
</dependency>
```

License
-------

Copyright (C) 2013-2014 Maginatics, Inc.

Licensed under the Apache License, Version 2.0

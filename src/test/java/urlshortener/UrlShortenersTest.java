/*
 * Copyright 2013 Maginatics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package urlshortener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;
import org.junit.Test;

/** Tests for UrlShorteners. */
public final class UrlShortenersTest {
    private static final URL longUrl;

    static {
        URL tmpUrl = null;
        try {
            tmpUrl = new URL("http://127.0.0.1/some/random/url");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        longUrl = tmpUrl;
    }

    private static void validateRedirectedUrl(final URL shortUrl)
            throws IOException {
        HttpURLConnection conn = (HttpURLConnection) shortUrl.openConnection();
        conn.setInstanceFollowRedirects(false);
        conn.connect();
        assertThat(longUrl.toString()).isEqualTo(conn.getHeaderField(
            "Location"));
    }

    @Test
    public void testBitlyUrlShortener() throws Exception {
        String accessToken = System.getProperty(
            "urlshortener.bitlyAccessToken");
        assumeTrue(accessToken != null);
        UrlShortener urlShortener = UrlShorteners.bitlyUrlShortener(
            accessToken);
        URL shortUrl = urlShortener.shorten(longUrl);
        validateRedirectedUrl(shortUrl);
    }

    @Test
    public void testGoogleUrlShortener() throws Exception {
        String apiKey = System.getProperty("urlshortener.googleApiKey");
        assumeTrue(apiKey != null);
        UrlShortener urlShortener = UrlShorteners.googleUrlShortener(apiKey);
        URL shortUrl = urlShortener.shorten(longUrl);
        validateRedirectedUrl(shortUrl);
    }
}

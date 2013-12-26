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

/** Factory for URL shorteners. */
public final class UrlShorteners {
    private UrlShorteners() {
        // Utility class
    }

    /**
     * New URL Shortener using the bit.ly API.
     *
     * @param accessToken bit.ly access token
     * @return UrlShortener instance
     */
    public static UrlShortener bitlyUrlShortener(final String accessToken) {
        return new BitlyUrlShortener(accessToken);
    }

    /**
     * New URL Shortener using the goo.gl API.
     *
     * @param apiKey Google API key
     * @return UrlShortener instance
     */
    public static UrlShortener googleUrlShortener(final String apiKey) {
        return new GoogleUrlShortener(apiKey);
    }

    /** New URL shortener that returns the URLs as-is. */
    public static UrlShortener identityUrlShortener() {
        return new IdentityUrlShortener();
    }
}

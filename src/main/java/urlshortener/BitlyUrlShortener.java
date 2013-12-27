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

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.Server;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Query;

/** bit.ly URL Shortener implementation. */
final class BitlyUrlShortener implements UrlShortener {
    private static final Logger logger = LoggerFactory.getLogger(
        BitlyUrlShortener.class);
    private static final Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
    private static final RestAdapter restAdapter = new RestAdapter.Builder()
        .setServer(new Server("https://api-ssl.bitly.com"))
        .setConverter(new GsonConverter(gson))
        .build();
    private static final BitlyService bitly = restAdapter.create(
        BitlyService.class);
    private static final int HTTP_OK = 200;

    private final String accessToken;

    BitlyUrlShortener(final String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public URI shorten(final URI longUrl) {
        ShortenResponse response = bitly.shorten(accessToken, longUrl);
        // If an error happened, returned the original as-is
        if (response.statusCode != HTTP_OK) {
            logger.debug("Request to shorten URL {} failed with code {}",
                longUrl, response.statusCode);
            return longUrl;
        }
        return response.data.uri;
    }

    interface BitlyService {
        // See http://dev.bitly.com/links.html#v3_shorten
        @GET("/v3/shorten")
        ShortenResponse shorten(@Query("access_token") String accessToken,
                                @Query("longUrl") URI longUrl);
    }

    private static final class ShortenResponse {
        // For the response content
        private Data data;
        // The actual status of the request
        private int statusCode;

        // Empty constructor for gson
        ShortenResponse() {
        }

        static final class Data {
            private URI uri;

            // Empty constructor for gson
            Data() {
            }
        }
    }
}

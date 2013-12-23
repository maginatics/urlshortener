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

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.Server;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Query;

final class GoogleUrlShortener implements UrlShortener {
    private static final Logger logger = LoggerFactory.getLogger(
        GoogleUrlShortener.class);
    private static final RestAdapter restAdapter = new RestAdapter.Builder()
        .setServer(new Server("https://www.googleapis.com/urlshortener"))
        .build();
    private static final GoogleService google = restAdapter.create(
        GoogleService.class);
    private final String apiKey;

    GoogleUrlShortener(final String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public URL shorten(final URL longUrl) {
        try {
            return google.shorten(apiKey, new Payload(longUrl)).id;
        } catch (RetrofitError e) {
            ErrorPayload error = ((ErrorResponse) e.getBodyAs(
                ErrorResponse.class)).error;
            logger.debug("Request to shorten {} failed with {}",
                longUrl, error.message);
            return longUrl;
        }
    }

    interface GoogleService {
        @POST("/v1/url")
        Payload shorten(@Query("key") String apiKey, @Body Payload req);
    }

    private static final class Payload {
        private URL id;
        private URL longUrl;

        Payload(final URL longUrl) {
            this.id = null;
            this.longUrl = longUrl;
        }
    }

    private static final class ErrorResponse {
        private ErrorPayload error;
        ErrorResponse() { }
    }

    private static final class ErrorPayload {
        private int code;
        private String message;
        ErrorPayload() { }
    }
}

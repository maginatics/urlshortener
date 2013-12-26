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

/** Minimal API for URL Shorteners. */
public interface UrlShortener {
    /**
     * Returned a shortened version of the given URL.
     *
     * @param longUrl URL to shorten. Must not be null.
     * @return shortened URL. Implementations define what "shortened" means.
     *         This is a best-effort interface and implementers may return the
     *         longUrl in case of errors.
     */
    URI shorten(URI longUrl);
}

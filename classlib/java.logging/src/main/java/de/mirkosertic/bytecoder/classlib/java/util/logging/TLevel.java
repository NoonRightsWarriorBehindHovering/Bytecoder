/*
 * Copyright 2020 Mirko Sertic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mirkosertic.bytecoder.classlib.java.util.logging;

import de.mirkosertic.bytecoder.api.SubstitutesInClass;

import java.util.logging.Level;

@SubstitutesInClass(completeReplace = false)
public class TLevel {

    @SubstitutesInClass(completeReplace = true)
    static final class KnownLevel {

        public static void add(final Level level) {
        }
    }
}

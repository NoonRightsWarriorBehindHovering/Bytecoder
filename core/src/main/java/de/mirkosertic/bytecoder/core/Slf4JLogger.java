/*
 * Copyright 2017 Mirko Sertic
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
package de.mirkosertic.bytecoder.core;

import de.mirkosertic.bytecoder.api.Logger;
import org.slf4j.LoggerFactory;

public class Slf4JLogger implements Logger {

    public static final Slf4JLogger INSTANCE = new Slf4JLogger();

    private final org.slf4j.Logger logger;

    public Slf4JLogger() {
        logger = LoggerFactory.getLogger(Slf4JLogger.class);
    }

    @Override
    public void info(final String aMessage, final Object... aArguments) {
        logger.info(aMessage, aArguments);
    }

    @Override
    public void warn(final String aMessage, final Object... aArguments) {
        logger.warn(aMessage, aArguments);
    }

    @Override
    public void debug(final String aMessage, final Object... aArguments) {
        logger.debug(aMessage, aArguments);
    }
}

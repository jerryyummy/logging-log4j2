/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.logging.log4j.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.core.util.Constants;
import org.apache.logging.log4j.message.DefaultFlowMessageFactory;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.ReusableMessageFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junitpioneer.jupiter.SetSystemProperty;

class LoggerMessageFactoryDefaultsTlaEnabledTest {

    @Test
    @SetSystemProperty(key = "log4j2.isWebapp", value = "false")
    @SetSystemProperty(key = "log4j2.enableThreadlocals", value = "true")
    void defaults_should_match_when_thread_locals_enabled(TestInfo testInfo) {
        assertThat(Constants.ENABLE_THREADLOCALS).isTrue();
        try (LoggerContext loggerContext =
                new LoggerContext(LoggerMessageFactoryDefaultsTlaEnabledTest.class.getSimpleName())) {
            Logger logger = loggerContext.getLogger(testInfo.getDisplayName());
            assertThat((MessageFactory) logger.getMessageFactory()).isSameAs(ReusableMessageFactory.INSTANCE);
            assertThat(logger.getFlowMessageFactory()).isSameAs(DefaultFlowMessageFactory.INSTANCE);
        }
    }
}

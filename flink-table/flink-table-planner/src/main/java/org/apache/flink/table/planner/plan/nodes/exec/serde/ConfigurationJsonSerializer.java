/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.table.planner.plan.nodes.exec.serde;

import org.apache.flink.annotation.Internal;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.table.planner.plan.nodes.exec.ExecNodeBase;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonGenerator;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.TreeMap;

/**
 * Custom serializer for {@link Configuration} used for {@link ExecNodeBase#getPersistedConfig()}.
 */
@Internal
class ConfigurationJsonSerializer extends StdSerializer<Configuration> {

    ConfigurationJsonSerializer() {
        super(Configuration.class);
    }

    @Override
    public void serialize(
            Configuration configuration,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider)
            throws IOException {
        // Use TreeMap to have a nice -sorted by key- output of the config options.
        serializerProvider.defaultSerializeValue(
                new TreeMap<>(configuration.toMap()), jsonGenerator);
    }
}

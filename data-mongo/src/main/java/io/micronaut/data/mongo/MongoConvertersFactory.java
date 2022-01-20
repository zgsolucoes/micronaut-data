/*
 * Copyright 2017-2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.data.mongo;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.data.runtime.convert.DataTypeConverter;
import org.bson.BsonValue;
import org.bson.types.ObjectId;

import java.util.Optional;

/**
 * MongoDB's converters.
 *
 * @author Denis Stepanov
 * @since 3.3.0
 */
@Factory
final class MongoConvertersFactory {

    @Prototype
    DataTypeConverter<BsonValue, ObjectId> bsonValueAsObjectId() {
        return (value, targetType, context) -> {
            if (value.isObjectId()) {
                return Optional.of(value.asObjectId().getValue());
            }
            return Optional.empty();
        };
    }

    @Prototype
    DataTypeConverter<BsonValue, String> bsonValueAsString() {
        return (value, targetType, context) -> {
            if (value.isObjectId()) {
                return Optional.of(value.asObjectId().getValue().toHexString());
            }
            return Optional.empty();
        };
    }

    @Prototype
    DataTypeConverter<ObjectId, String> objectIdAsString() {
        return (value, targetType, context) -> Optional.of(value.toHexString());
    }

}

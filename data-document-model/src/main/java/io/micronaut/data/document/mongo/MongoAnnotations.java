/*
 * Copyright 2017-2022 original authors
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
package io.micronaut.data.document.mongo;

import io.micronaut.core.annotation.Internal;

/**
 * Mongo annotations constants.
 *
 * @author Denis Stepanov
 * @since 3.3.0
 */
@Internal
public interface MongoAnnotations {

    String REPOSITORY = "io.micronaut.data.mongodb.annotation.MongoRepository";
    String FIND_QUERY = "io.micronaut.data.mongodb.annotation.MongoFindQuery";
    String AGGREGATION_QUERY = "io.micronaut.data.mongodb.annotation.MongoAggregateQuery";
    String DELETE_QUERY = "io.micronaut.data.mongodb.annotation.MongoDeleteQuery";
    String UPDATE_QUERY = "io.micronaut.data.mongodb.annotation.MongoUpdateQuery";
    String FILTER = "io.micronaut.data.mongodb.annotation.MongoFilter";
    String SORT = "io.micronaut.data.mongodb.annotation.MongoSort";
    String PROJECTION = "io.micronaut.data.mongodb.annotation.MongoProjection";
    String COLLATION = "io.micronaut.data.mongodb.annotation.MongoCollation";

}

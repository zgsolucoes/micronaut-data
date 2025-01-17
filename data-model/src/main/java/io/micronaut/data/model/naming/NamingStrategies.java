/*
 * Copyright 2017-2020 original authors
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
package io.micronaut.data.model.naming;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.TypeHint;
import io.micronaut.core.naming.NameUtils;

import java.util.Locale;

/**
 * Naming strategy enum for when a class or property name has no explicit mapping.
 *
 * @author graemerocher
 * @since 1.0
 */
public class NamingStrategies {
    /**
     * Example: FOO_BAR.
     */
    @TypeHint(UnderScoreSeparatedUpperCase.class)
    public static class UnderScoreSeparatedUpperCase implements NamingStrategy {
        @NonNull
        @Override
        public String mappedName(@NonNull String name) {
            return NameUtils.environmentName(name);
        }
    }
    /**
     * Example: foo_bar.
     */
    @TypeHint(UnderScoreSeparatedLowerCase.class)
    public static class UnderScoreSeparatedLowerCase implements NamingStrategy {
        @NonNull
        @Override
        public String mappedName(@NonNull String name) {
            return NameUtils.underscoreSeparate(name).toLowerCase(Locale.ENGLISH);
        }
    }
    /**
     * Example: foo-bar.
     */
    @TypeHint(KebabCase.class)
    public static class KebabCase implements NamingStrategy {
        @NonNull
        @Override
        public String mappedName(@NonNull String name) {
            return NameUtils.hyphenate(name);
        }
    }
    /**
     * Example: foobar.
     */
    @TypeHint(LowerCase.class)
    public static class LowerCase implements NamingStrategy {
        @NonNull
        @Override
        public String mappedName(@NonNull String name) {
            return name.toLowerCase(Locale.ENGLISH);
        }
    }
    /**
     * Example: foobar.
     */
    @TypeHint(UpperCase.class)
    public static class UpperCase implements NamingStrategy {
        @NonNull
        @Override
        public String mappedName(@NonNull String name) {
            return name.toUpperCase(Locale.ENGLISH);
        }
    }
    /**
     * No naming conversion.
     */
    @TypeHint(Raw.class)
    public static class Raw implements NamingStrategy {
        @NonNull
        @Override
        public String mappedName(@NonNull String name) {
            return name;
        }
    }
}

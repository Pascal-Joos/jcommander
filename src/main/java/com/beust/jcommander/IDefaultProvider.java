/**
 * Copyright (C) 2010 the original author or authors. See the notice.md file distributed with this
 * work for additional information regarding copyright ownership.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beust.jcommander;
import javax.annotation.Nullable;

/**
 * Allows the specification of default values.
 *
 * @author cbeust
 */
public interface IDefaultProvider {

  /**
   * @param optionName The name of the option as specified in the names() attribute of
   *     the @Parameter option (e.g. "-file").
   * @return the default value for this option.
   */
  @Nullable String getDefaultValueFor(String optionName);

  /**
   * Returns a default provider which attempts to query a default value from a sequence of default
   * providers. The first produced non-null value will get finally returned.
   *
   * @param defaultProviders A sorted sequence of default providers.
   * @return The first non-null value provided, or {@code null} if all providers returned {@code
   *     null}.
   */
  static IDefaultProvider sequenceOf(final IDefaultProvider... defaultProviders) {
    return new IDefaultProvider() {
      @Nullable @Override
      public String getDefaultValueFor(final String optionName) {
        for (final var defaultProvider : defaultProviders) {
          final var defaultValue = defaultProvider.getDefaultValueFor(optionName);
          if (defaultValue != null) return defaultValue;
        }
        return null;
      }
    };
  }
}

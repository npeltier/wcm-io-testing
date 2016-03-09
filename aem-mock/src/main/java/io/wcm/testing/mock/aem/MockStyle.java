/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2016 wcm.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.wcm.testing.mock.aem;

import java.util.HashMap;

import org.apache.sling.api.resource.Resource;

import com.day.cq.wcm.api.designer.Cell;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Style;

class MockStyle extends HashMap<String, Object> implements Style {
  private static final long serialVersionUID = 1L;


  // --- unsupported operations ---

  @Override
  public Design getDesign() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getPath() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Cell getCell() {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> T get(String name, Class<T> type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> T get(String name, T defaultValue) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Resource getDefiningResource(String name) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getDefiningPath(String name) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Style getSubStyle(String relPath) {
    throw new UnsupportedOperationException();
  }

}

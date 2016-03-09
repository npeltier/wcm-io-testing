/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2014 wcm.io
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

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.wcm.api.NameConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Designer;
import com.day.cq.wcm.api.designer.Style;
import com.day.cq.wcm.commons.WCMUtils;

/**
 * Mock implementation of {@link Designer}.
 * This returns no designs at all (not even a default design).
 */
class MockDesigner implements Designer {

  private final ResourceResolver resourceResolver;

  static final String DESIGN_ID_PREFIX = "/etc/designs/";

  MockDesigner(ResourceResolver resourceResolver) {
    this.resourceResolver = resourceResolver;
  }

  @Override
  public String getDesignPath(Page page) {
    if (page == null) {
      return null;
    }
    String designPath = WCMUtils.getInheritedProperty(page, resourceResolver, NameConstants.PN_DESIGN_PATH);
    return StringUtils.defaultString(designPath, DEFAULT_DESIGN_PATH);
  }

  @Override
  public Design getDesign(Page page) {
    String designPath = getDesignPath(page);
    return getDesignForPath(designPath);
  }

  @Override
  public boolean hasDesign(String id) {
    return getDesign(id) != null;
  }

  @Override
  public Design getDesign(String id) {
    String designPath = getDesignPathForId(id);
    return getDesignForPath(designPath);
  }

  @Override
  public Style getStyle(Resource resource) {
    PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
    Page page = pageManager.getContainingPage(resource);
    Design design = getDesign(page);
    if (design != null) {
      return design.getStyle(resource);
    }
    return null;
  }

  @Override
  public Style getStyle(Resource resource, String cellPath) {
    PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
    Page page = pageManager.getContainingPage(resource);
    Design design = getDesign(page);
    if (design != null) {
      return design.getStyle(cellPath);
    }
    return null;
  }

  private Design getDesignForPath(String designPath) {
    if (StringUtils.isEmpty(designPath)) {
      return null;
    }
    return new MockDesign(designPath, resourceResolver);
  }

  private String getDesignPathForId(String id) {
    if (StringUtils.isNotEmpty(id)) {
      if (StringUtils.startsWith(id, "/")) {
        return id;
      }
      else {
        return DESIGN_ID_PREFIX + "/" + id;
      }
    }
    return null;
  }

}

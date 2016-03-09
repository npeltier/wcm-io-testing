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

import static io.wcm.testing.mock.aem.MockDesigner.DESIGN_ID_PREFIX;

import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.designer.Cell;
import com.day.cq.wcm.api.designer.ComponentStyle;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Style;

class MockDesign implements Design {

  private final String designPath;
  private final Resource designResource;

  MockDesign(String designPath, ResourceResolver resourceResolver) {
    this.designPath = designPath;
    this.designResource = resourceResolver.getResource(designPath);
  }

  @Override
  public String getPath() {
    return designPath;
  }

  @Override
  public String getId() {
    if (StringUtils.startsWith(designPath, DESIGN_ID_PREFIX)) {
      return StringUtils.substringAfter(designPath, DESIGN_ID_PREFIX);
    }
    else {
      return designPath;
    }
  }

  @Override
  public Resource getContentResource() {
    if (designResource == null) {
      return null;
    }
    return designResource.getChild(JcrConstants.JCR_CONTENT);
  }

  @Override
  public boolean hasContent() {
    return getContentResource() != null;
  }

  @Override
  public String getCssPath() {
    return designPath + ".css";
  }

  @Override
  public String getStaticCssPath() {
    if (designResource == null) {
      return null;
    }
    Resource staticCssResource = designResource.getChild("static.css");
    if (staticCssResource != null) {
      return staticCssResource.getPath();
    }
    else {
      return null;
    }
  }

  @SuppressWarnings("deprecation")
  @Override
  public Calendar getLastModified() {
    Resource contentResource = getContentResource();
    ValueMap props = ResourceUtil.getValueMap(contentResource);
    return props.get(JcrConstants.JCR_LASTMODIFIED, Calendar.class);
  }


  // --- unsupported operations ---

  @Override
  public Style getStyle(String path) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Style getStyle(Cell cell) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Style getStyle(Resource res) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void writeCssIncludes(Writer out) throws IOException {
    throw new UnsupportedOperationException();
  }

  @SuppressWarnings("deprecation")
  @Override
  public void writeCssIncludes(Writer out, com.day.cq.commons.Doctype doctype) throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void writeCssIncludes(PageContext page) throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void writeCSS(Writer out, boolean includeCustom) throws IOException, RepositoryException {
    throw new UnsupportedOperationException();
  }

  @SuppressWarnings("deprecation")
  @Override
  public com.day.cq.commons.Doctype getDoctype(Style currentStyle) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Map<String, ComponentStyle> getComponentStyles(Cell cell) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getJSON() {
    throw new UnsupportedOperationException();
  }

}

/**
 * Copyright (c) 2017, 2019 Kichwa Coders Ltd. and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */
package org.eclipse.lsp4j.debug;

import org.eclipse.lsp4j.debug.ValueFormat;
import org.eclipse.lsp4j.debug.VariablesArgumentsFilter;
import org.eclipse.lsp4j.debug.util.Preconditions;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * Arguments for 'variables' request.
 */
@SuppressWarnings("all")
public class VariablesArguments {
  /**
   * The Variable reference. The value should be less than or equal to 2147483647 (2^31 - 1).
   */
  @NonNull
  private Integer variablesReference;
  
  /**
   * Optional filter to limit the child variables to either named or indexed. If omitted, both types are fetched.
   * <p>
   * This is an optional property.
   */
  private VariablesArgumentsFilter filter;
  
  /**
   * The index of the first variable to return; if omitted children start at 0.
   * <p>
   * This is an optional property.
   */
  private Integer start;
  
  /**
   * The number of variables to return. If count is missing or 0, all variables are returned.
   * <p>
   * This is an optional property.
   */
  private Integer count;
  
  /**
   * Specifies details on how to format the Variable values.
   * <p>
   * This is an optional property.
   */
  private ValueFormat format;
  
  /**
   * The Variable reference. The value should be less than or equal to 2147483647 (2^31 - 1).
   */
  @Pure
  @NonNull
  public Integer getVariablesReference() {
    return this.variablesReference;
  }
  
  /**
   * The Variable reference. The value should be less than or equal to 2147483647 (2^31 - 1).
   */
  public void setVariablesReference(@NonNull final Integer variablesReference) {
    this.variablesReference = Preconditions.checkNotNull(variablesReference, "variablesReference");
  }
  
  /**
   * Optional filter to limit the child variables to either named or indexed. If omitted, both types are fetched.
   * <p>
   * This is an optional property.
   */
  @Pure
  public VariablesArgumentsFilter getFilter() {
    return this.filter;
  }
  
  /**
   * Optional filter to limit the child variables to either named or indexed. If omitted, both types are fetched.
   * <p>
   * This is an optional property.
   */
  public void setFilter(final VariablesArgumentsFilter filter) {
    this.filter = filter;
  }
  
  /**
   * The index of the first variable to return; if omitted children start at 0.
   * <p>
   * This is an optional property.
   */
  @Pure
  public Integer getStart() {
    return this.start;
  }
  
  /**
   * The index of the first variable to return; if omitted children start at 0.
   * <p>
   * This is an optional property.
   */
  public void setStart(final Integer start) {
    this.start = start;
  }
  
  /**
   * The number of variables to return. If count is missing or 0, all variables are returned.
   * <p>
   * This is an optional property.
   */
  @Pure
  public Integer getCount() {
    return this.count;
  }
  
  /**
   * The number of variables to return. If count is missing or 0, all variables are returned.
   * <p>
   * This is an optional property.
   */
  public void setCount(final Integer count) {
    this.count = count;
  }
  
  /**
   * Specifies details on how to format the Variable values.
   * <p>
   * This is an optional property.
   */
  @Pure
  public ValueFormat getFormat() {
    return this.format;
  }
  
  /**
   * Specifies details on how to format the Variable values.
   * <p>
   * This is an optional property.
   */
  public void setFormat(final ValueFormat format) {
    this.format = format;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("variablesReference", this.variablesReference);
    b.add("filter", this.filter);
    b.add("start", this.start);
    b.add("count", this.count);
    b.add("format", this.format);
    return b.toString();
  }
  
  @Override
  @Pure
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    VariablesArguments other = (VariablesArguments) obj;
    if (this.variablesReference == null) {
      if (other.variablesReference != null)
        return false;
    } else if (!this.variablesReference.equals(other.variablesReference))
      return false;
    if (this.filter == null) {
      if (other.filter != null)
        return false;
    } else if (!this.filter.equals(other.filter))
      return false;
    if (this.start == null) {
      if (other.start != null)
        return false;
    } else if (!this.start.equals(other.start))
      return false;
    if (this.count == null) {
      if (other.count != null)
        return false;
    } else if (!this.count.equals(other.count))
      return false;
    if (this.format == null) {
      if (other.format != null)
        return false;
    } else if (!this.format.equals(other.format))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.variablesReference== null) ? 0 : this.variablesReference.hashCode());
    result = prime * result + ((this.filter== null) ? 0 : this.filter.hashCode());
    result = prime * result + ((this.start== null) ? 0 : this.start.hashCode());
    result = prime * result + ((this.count== null) ? 0 : this.count.hashCode());
    return prime * result + ((this.format== null) ? 0 : this.format.hashCode());
  }
}

/**
 * Copyright (c) 2016-2018 TypeFox and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */
package org.eclipse.lsp4j;

import com.google.common.annotations.Beta;
import java.util.Arrays;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.SymbolKind;
import org.eclipse.lsp4j.SymbolTag;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * The result of a {@code textDocument/callHierarchy} request.
 */
@Beta
@SuppressWarnings("all")
public class CallHierarchyItem {
  /**
   * The name of the item targeted by the call hierarchy request.
   */
  @NonNull
  private String name;
  
  /**
   * More detail for this item, e.g the signature of a function.
   */
  private String detail;
  
  /**
   * The kind of this item.
   */
  @NonNull
  private SymbolKind kind;
  
  /**
   * Tags for this item.
   */
  private SymbolTag[] tags;
  
  /**
   * The resource identifier of this item.
   */
  @NonNull
  private String uri;
  
  /**
   * The range enclosing this symbol not including leading/trailing whitespace but everything else
   * like comments. This information is typically used to determine if the the clients cursor is
   * inside the symbol to reveal in the symbol in the UI.
   */
  @NonNull
  private Range range;
  
  /**
   * The range that should be selected and revealed when this symbol is being picked, e.g the name of a function.
   * Must be contained by the the {@link CallHierarchyItem#getRange range}.
   */
  @NonNull
  private Range selectionRange;
  
  /**
   * The name of the item targeted by the call hierarchy request.
   */
  @Pure
  @NonNull
  public String getName() {
    return this.name;
  }
  
  /**
   * The name of the item targeted by the call hierarchy request.
   */
  public void setName(@NonNull final String name) {
    this.name = Preconditions.checkNotNull(name, "name");
  }
  
  /**
   * More detail for this item, e.g the signature of a function.
   */
  @Pure
  public String getDetail() {
    return this.detail;
  }
  
  /**
   * More detail for this item, e.g the signature of a function.
   */
  public void setDetail(final String detail) {
    this.detail = detail;
  }
  
  /**
   * The kind of this item.
   */
  @Pure
  @NonNull
  public SymbolKind getKind() {
    return this.kind;
  }
  
  /**
   * The kind of this item.
   */
  public void setKind(@NonNull final SymbolKind kind) {
    this.kind = Preconditions.checkNotNull(kind, "kind");
  }
  
  /**
   * Tags for this item.
   */
  @Pure
  public SymbolTag[] getTags() {
    return this.tags;
  }
  
  /**
   * Tags for this item.
   */
  public void setTags(final SymbolTag[] tags) {
    this.tags = tags;
  }
  
  /**
   * The resource identifier of this item.
   */
  @Pure
  @NonNull
  public String getUri() {
    return this.uri;
  }
  
  /**
   * The resource identifier of this item.
   */
  public void setUri(@NonNull final String uri) {
    this.uri = Preconditions.checkNotNull(uri, "uri");
  }
  
  /**
   * The range enclosing this symbol not including leading/trailing whitespace but everything else
   * like comments. This information is typically used to determine if the the clients cursor is
   * inside the symbol to reveal in the symbol in the UI.
   */
  @Pure
  @NonNull
  public Range getRange() {
    return this.range;
  }
  
  /**
   * The range enclosing this symbol not including leading/trailing whitespace but everything else
   * like comments. This information is typically used to determine if the the clients cursor is
   * inside the symbol to reveal in the symbol in the UI.
   */
  public void setRange(@NonNull final Range range) {
    this.range = Preconditions.checkNotNull(range, "range");
  }
  
  /**
   * The range that should be selected and revealed when this symbol is being picked, e.g the name of a function.
   * Must be contained by the the {@link CallHierarchyItem#getRange range}.
   */
  @Pure
  @NonNull
  public Range getSelectionRange() {
    return this.selectionRange;
  }
  
  /**
   * The range that should be selected and revealed when this symbol is being picked, e.g the name of a function.
   * Must be contained by the the {@link CallHierarchyItem#getRange range}.
   */
  public void setSelectionRange(@NonNull final Range selectionRange) {
    this.selectionRange = Preconditions.checkNotNull(selectionRange, "selectionRange");
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("name", this.name);
    b.add("detail", this.detail);
    b.add("kind", this.kind);
    b.add("tags", this.tags);
    b.add("uri", this.uri);
    b.add("range", this.range);
    b.add("selectionRange", this.selectionRange);
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
    CallHierarchyItem other = (CallHierarchyItem) obj;
    if (this.name == null) {
      if (other.name != null)
        return false;
    } else if (!this.name.equals(other.name))
      return false;
    if (this.detail == null) {
      if (other.detail != null)
        return false;
    } else if (!this.detail.equals(other.detail))
      return false;
    if (this.kind == null) {
      if (other.kind != null)
        return false;
    } else if (!this.kind.equals(other.kind))
      return false;
    if (this.tags == null) {
      if (other.tags != null)
        return false;
    } else if (!Arrays.deepEquals(this.tags, other.tags))
      return false;
    if (this.uri == null) {
      if (other.uri != null)
        return false;
    } else if (!this.uri.equals(other.uri))
      return false;
    if (this.range == null) {
      if (other.range != null)
        return false;
    } else if (!this.range.equals(other.range))
      return false;
    if (this.selectionRange == null) {
      if (other.selectionRange != null)
        return false;
    } else if (!this.selectionRange.equals(other.selectionRange))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.name== null) ? 0 : this.name.hashCode());
    result = prime * result + ((this.detail== null) ? 0 : this.detail.hashCode());
    result = prime * result + ((this.kind== null) ? 0 : this.kind.hashCode());
    result = prime * result + ((this.tags== null) ? 0 : Arrays.deepHashCode(this.tags));
    result = prime * result + ((this.uri== null) ? 0 : this.uri.hashCode());
    result = prime * result + ((this.range== null) ? 0 : this.range.hashCode());
    return prime * result + ((this.selectionRange== null) ? 0 : this.selectionRange.hashCode());
  }
}

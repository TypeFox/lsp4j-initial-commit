/**
 * Copyright (c) 2017, 2018 Kichwa Coders Ltd. and others.
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

import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * Arguments for 'stepIn' request.
 */
@SuppressWarnings("all")
public class StepInArguments {
  /**
   * Execute 'stepIn' for this thread.
   */
  @NonNull
  private Long threadId;
  
  /**
   * Optional id of the target to step into.
   * <p>
   * This is an optional property.
   */
  private Long targetId;
  
  /**
   * Execute 'stepIn' for this thread.
   */
  @Pure
  @NonNull
  public Long getThreadId() {
    return this.threadId;
  }
  
  /**
   * Execute 'stepIn' for this thread.
   */
  public void setThreadId(@NonNull final Long threadId) {
    if (threadId == null) {
      throw new IllegalArgumentException("Property must not be null: threadId");
    }
    this.threadId = threadId;
  }
  
  /**
   * Optional id of the target to step into.
   * <p>
   * This is an optional property.
   */
  @Pure
  public Long getTargetId() {
    return this.targetId;
  }
  
  /**
   * Optional id of the target to step into.
   * <p>
   * This is an optional property.
   */
  public void setTargetId(final Long targetId) {
    this.targetId = targetId;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("threadId", this.threadId);
    b.add("targetId", this.targetId);
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
    StepInArguments other = (StepInArguments) obj;
    if (this.threadId == null) {
      if (other.threadId != null)
        return false;
    } else if (!this.threadId.equals(other.threadId))
      return false;
    if (this.targetId == null) {
      if (other.targetId != null)
        return false;
    } else if (!this.targetId.equals(other.targetId))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.threadId== null) ? 0 : this.threadId.hashCode());
    return prime * result + ((this.targetId== null) ? 0 : this.targetId.hashCode());
  }
}

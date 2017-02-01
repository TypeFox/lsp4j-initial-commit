package org.eclipse.lsp4j;

import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * Document link options
 */
@SuppressWarnings("all")
public class DocumentLinkOptions {
  /**
   * Document links have a resolve provider as well.
   */
  private Boolean resolveProvider;
  
  /**
   * Document links have a resolve provider as well.
   */
  @Pure
  public Boolean getResolveProvider() {
    return this.resolveProvider;
  }
  
  /**
   * Document links have a resolve provider as well.
   */
  public void setResolveProvider(final Boolean resolveProvider) {
    this.resolveProvider = resolveProvider;
  }
  
  public DocumentLinkOptions() {
    
  }
  
  public DocumentLinkOptions(final Boolean resolveProvider) {
    this.resolveProvider = resolveProvider;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("resolveProvider", this.resolveProvider);
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
    DocumentLinkOptions other = (DocumentLinkOptions) obj;
    if (this.resolveProvider == null) {
      if (other.resolveProvider != null)
        return false;
    } else if (!this.resolveProvider.equals(other.resolveProvider))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.resolveProvider== null) ? 0 : this.resolveProvider.hashCode());
    return result;
  }
}

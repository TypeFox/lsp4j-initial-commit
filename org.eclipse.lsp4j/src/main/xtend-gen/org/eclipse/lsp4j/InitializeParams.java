package org.eclipse.lsp4j;

import org.eclipse.lsp4j.ClientCapabilities;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * The initialize request is sent as the first request from the client to the server.
 */
@SuppressWarnings("all")
public class InitializeParams {
  /**
   * The process Id of the parent process that started the server.
   */
  private Integer processId;
  
  /**
   * The rootPath of the workspace. Is null if no folder is open.
   * 
   * @deprecared in favour of rootUri.
   */
  @Deprecated
  private String rootPath;
  
  /**
   * The rootUri of the workspace. Is null if no
   * folder is open.
   */
  private String rootUri;
  
  /**
   * User provided initialization options.
   */
  private Object initializationOptions;
  
  /**
   * The capabilities provided by the client (editor)
   */
  private ClientCapabilities capabilities;
  
  /**
   * An optional extension to the protocol.
   * To tell the server what client (editor) is talking to it.
   */
  @Deprecated
  private String clientName;
  
  /**
   * The initial trace setting. If omitted trace is disabled ('off').
   * 
   * Legal values: 'off' | 'messages' | 'verbose'
   */
  private String trace;
  
  /**
   * The process Id of the parent process that started the server.
   */
  @Pure
  public Integer getProcessId() {
    return this.processId;
  }
  
  /**
   * The process Id of the parent process that started the server.
   */
  public void setProcessId(final Integer processId) {
    this.processId = processId;
  }
  
  /**
   * The rootPath of the workspace. Is null if no folder is open.
   * 
   * @deprecared in favour of rootUri.
   */
  @Pure
  @Deprecated
  public String getRootPath() {
    return this.rootPath;
  }
  
  /**
   * The rootPath of the workspace. Is null if no folder is open.
   * 
   * @deprecared in favour of rootUri.
   */
  @Deprecated
  public void setRootPath(final String rootPath) {
    this.rootPath = rootPath;
  }
  
  /**
   * The rootUri of the workspace. Is null if no
   * folder is open.
   */
  @Pure
  public String getRootUri() {
    return this.rootUri;
  }
  
  /**
   * The rootUri of the workspace. Is null if no
   * folder is open.
   */
  public void setRootUri(final String rootUri) {
    this.rootUri = rootUri;
  }
  
  /**
   * User provided initialization options.
   */
  @Pure
  public Object getInitializationOptions() {
    return this.initializationOptions;
  }
  
  /**
   * User provided initialization options.
   */
  public void setInitializationOptions(final Object initializationOptions) {
    this.initializationOptions = initializationOptions;
  }
  
  /**
   * The capabilities provided by the client (editor)
   */
  @Pure
  public ClientCapabilities getCapabilities() {
    return this.capabilities;
  }
  
  /**
   * The capabilities provided by the client (editor)
   */
  public void setCapabilities(final ClientCapabilities capabilities) {
    this.capabilities = capabilities;
  }
  
  /**
   * An optional extension to the protocol.
   * To tell the server what client (editor) is talking to it.
   */
  @Pure
  @Deprecated
  public String getClientName() {
    return this.clientName;
  }
  
  /**
   * An optional extension to the protocol.
   * To tell the server what client (editor) is talking to it.
   */
  @Deprecated
  public void setClientName(final String clientName) {
    this.clientName = clientName;
  }
  
  /**
   * The initial trace setting. If omitted trace is disabled ('off').
   * 
   * Legal values: 'off' | 'messages' | 'verbose'
   */
  @Pure
  public String getTrace() {
    return this.trace;
  }
  
  /**
   * The initial trace setting. If omitted trace is disabled ('off').
   * 
   * Legal values: 'off' | 'messages' | 'verbose'
   */
  public void setTrace(final String trace) {
    this.trace = trace;
  }
  
  public InitializeParams() {
    
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("processId", this.processId);
    b.add("rootPath", this.rootPath);
    b.add("rootUri", this.rootUri);
    b.add("initializationOptions", this.initializationOptions);
    b.add("capabilities", this.capabilities);
    b.add("clientName", this.clientName);
    b.add("trace", this.trace);
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
    InitializeParams other = (InitializeParams) obj;
    if (this.processId == null) {
      if (other.processId != null)
        return false;
    } else if (!this.processId.equals(other.processId))
      return false;
    if (this.rootPath == null) {
      if (other.rootPath != null)
        return false;
    } else if (!this.rootPath.equals(other.rootPath))
      return false;
    if (this.rootUri == null) {
      if (other.rootUri != null)
        return false;
    } else if (!this.rootUri.equals(other.rootUri))
      return false;
    if (this.initializationOptions == null) {
      if (other.initializationOptions != null)
        return false;
    } else if (!this.initializationOptions.equals(other.initializationOptions))
      return false;
    if (this.capabilities == null) {
      if (other.capabilities != null)
        return false;
    } else if (!this.capabilities.equals(other.capabilities))
      return false;
    if (this.clientName == null) {
      if (other.clientName != null)
        return false;
    } else if (!this.clientName.equals(other.clientName))
      return false;
    if (this.trace == null) {
      if (other.trace != null)
        return false;
    } else if (!this.trace.equals(other.trace))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.processId== null) ? 0 : this.processId.hashCode());
    result = prime * result + ((this.rootPath== null) ? 0 : this.rootPath.hashCode());
    result = prime * result + ((this.rootUri== null) ? 0 : this.rootUri.hashCode());
    result = prime * result + ((this.initializationOptions== null) ? 0 : this.initializationOptions.hashCode());
    result = prime * result + ((this.capabilities== null) ? 0 : this.capabilities.hashCode());
    result = prime * result + ((this.clientName== null) ? 0 : this.clientName.hashCode());
    result = prime * result + ((this.trace== null) ? 0 : this.trace.hashCode());
    return result;
  }
}

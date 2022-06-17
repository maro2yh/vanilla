package vanilla.stocks.ebestapi.session;

import com4j.COM4J;

/**
 * Defines methods to create COM objects
 */
public abstract class XASessionFactory {
  private XASessionFactory() {} // instanciation is not allowed


  /**
   * XASession Class
   */
  public static IXASession createXASession() {
    return COM4J.createInstance( IXASession.class, "{7FEF321C-6BFD-413C-AA80-541A275434A1}" );
  }
}

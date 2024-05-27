package se.kth.iv1350.pos.integration;

 /**
 * This exception is thrown when the database cannot be accessed.
 */
public class DatabaseUnavailableException  extends RuntimeException
{

   /**
     * an instance of the <code>DatabaseUnavailableException </code>.
     *  @param message The message that describes what went wrong.
     */
    public DatabaseUnavailableException(String message) {
      super(message);
  }

}
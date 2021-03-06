package clipboard;

/**
 * Exception that is thrown when trying to deserialize data in an invalid format
 * i.e. data that was not generated by a conforming serializer.
 * 
 * @author geomaster
 *
 */
public class InvalidDataFormatException extends SerializationDeserializationException {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * Constructor.
	 * 
	 * @param message
	 *            Exception message
	 */
	public InvalidDataFormatException(String message) {
		super(message);
	}
}

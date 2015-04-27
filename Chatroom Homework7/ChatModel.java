/**
 * The model for the MVC pattern.
 */
public class ChatModel {
	public String reverseText(String message) {
		StringBuffer buffer = new StringBuffer(message.length());

        // now reverse it
        for (int i = message.length()-1; i >= 0; i--)
            buffer.append(message.charAt(i));
        
        buffer.append('\n');

		return new String(buffer);
	}

}

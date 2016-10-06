package groupFiles;

import java.io.IOException;

public interface Topic {
	public void talk() throws IOException;
	public boolean isTriggered(String userInput);
}

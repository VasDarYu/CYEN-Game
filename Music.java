import java.io.*;
import sun.audio.*;
 
/**
 * A simple Java sound file example (i.e., Java code to play a sound file).
 * AudioStream and AudioPlayer code comes from a javaworld.com example.
 * @author alvin alexander, devdaily.com.
 */
class Music
{
	public Music(String path) throws Exception
	{
		// open the sound file as a Java input stream
		InputStream in = new FileInputStream(path);
 
		// create an audiostream from the inputstream
		AudioStream audioStream = new AudioStream(in);
		// play the audio clip with the audioplayer class
		AudioPlayer.player.start(audioStream);
	}
}
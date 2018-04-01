import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFile {
	
	public byte[] GetFileArray(String absolutePath) throws IOException
	{
		
		FileInputStream fs=new FileInputStream(absolutePath);
		
		byte[] filearr=new byte[fs.available()];
		
		try
		{
			while(fs.read(filearr)>0){}
		}
		finally
		{
			fs.close();
		}
		
		return filearr;
	}

}

package ioutil;
import java.io.*;

public interface IOable
{
	//Write an object to a given ObjectOutputStream stream.
	public void save(ObjectOutputStream oos) throws IOException;

	//Reads an object  from a given ObjectInputStream stream.
	public void load(ObjectInputStream ois) throws IOException, ClassNotFoundException;
}
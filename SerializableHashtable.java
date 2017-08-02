import java.io.Serializable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ArrayList;

public class SerializableHashtable implements Serializable {
	private static final long serialVersionUID = 1L;
	private Hashtable<String, String> hashtable = new Hashtable<String, String>();
	
	public SerializableHashtable() {
		super();
	}
	
	public SerializableHashtable(ArrayList<String[]> pairs) {
		super();
		
		for (Iterator<String[]> pairsIterator = pairs.iterator(); pairsIterator.hasNext();) {
			String[] pair = pairsIterator.next();
			this.hashtable.put(pair[0], pair[1]);
		}
	}
	
	public Hashtable<String, String> get() {
		return this.hashtable;
	}
	
	public boolean store(String path) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(path)));
			objectOutputStream.writeObject(this);
			objectOutputStream.flush();
			objectOutputStream.close();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public SerializableHashtable load(String path) {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(path)));
			SerializableHashtable serializableHashtable = (SerializableHashtable)objectInputStream.readObject();
			
			objectInputStream.close();
			return serializableHashtable;
		}
		catch (Exception e) {
			return null;
		}
	}
}

package clipboard;
import model.elements.GraphicShape;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.Set;

public class GraphicElementClipboardManager {
	private static GraphicElementClipboardManager instance = null;
	private GraphicElementClipboardSerializer serializer = new GraphicElementClipboardSerializer();
	private GraphicElementClipboardDeserializer deserializer = new GraphicElementClipboardDeserializer();
	private String lastEntryString = null;
	private GraphicElementClipboardEntry lastEntry = null;
	
	private GraphicElementClipboardManager() {
		
	}
	
	public static GraphicElementClipboardManager getInstance()
	{
		if (instance == null) {
			instance = new GraphicElementClipboardManager();
		}
		return instance;
	}
	
	public void toClipboard(GraphicElementClipboardEntry entry)
	{
		StringSelection str = new StringSelection(serializer.serialize(entry.getShapes()));
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		cb.setContents(str, str);
	}
	
	public GraphicElementClipboardEntry fromClipboard() throws SerializationDeserializationException, ClipboardException
	{
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable t = cb.getContents(null);
		if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			try {
				Object o = t.getTransferData(DataFlavor.stringFlavor);
				String s = (String)o;
				if (s != null) {
					if (s.equals(lastEntryString)) {
						return lastEntry;
					}
					
					Set<GraphicShape> shapes = deserializer.deserialize(s);
					GraphicElementClipboardEntry e = new GraphicElementClipboardEntry(shapes);
					lastEntry = e;
					lastEntryString = s;
					
					return e;
				} else {
					return null;
				}
			} catch (UnsupportedFlavorException e) {
				throw new ClipboardException("This clipboard is not supported");
			} catch (IOException e) {
				throw new ClipboardException("I/O error while accessing the clipboard");
			}
		} else {
			return null;
		}
	}
}
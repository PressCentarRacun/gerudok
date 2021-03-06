package view.elements;

import java.awt.Dimension;
import java.awt.event.MouseListener;

import model.Model;
import model.elements.GraphicElement;
import view.ElementView;

/**
 * View for graphic elements.
 * 
 * @author geomaster
 *
 */
public class GraphicElementView extends ElementView {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * Canvas view to use.
	 */
	private GraphicCanvasView canvas;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param graphicElement
	 *            Graphic element to display
	 */
	public GraphicElementView(Model model, GraphicElement graphicElement) {
		super(model, graphicElement);
		canvas = new GraphicCanvasView(graphicElement);

		canvas.setMinimumSize(new Dimension(500, 30));
		add(canvas);
		for (MouseListener l : deferredListeners) {
			canvas.addMouseListener(l);
		}
	}

	/**
	 * Called when the element is edited. Repaints the canvas view.
	 */
	@Override
	public void onEditNotification(Object obj) {
		canvas.repaint();
	}

	/**
	 * Gets the graphic element being displayed.
	 * 
	 * @return Graphic element
	 */
	public GraphicElement getGraphicElement() {
		return (GraphicElement) getElementContainer();
	}
}

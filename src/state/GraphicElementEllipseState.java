package state;
import model.elements.GraphicEllipseShape;
import model.elements.Point;
import model.elements.Rectangle;
import view.elements.GraphicCanvasView;

import java.awt.event.MouseEvent;

import command.elements.GraphicElementInvoker;
import command.elements.GraphicElementAddShapeCommand;
import model.elements.GraphicElement;

public class GraphicElementEllipseState extends GraphicElementState {
	private Point startPoint = new Point(0, 0);
	private GraphicElement element;
	private GraphicEllipseShape shape = null;
	
	public GraphicElementEllipseState(GraphicElement element, GraphicCanvasView view)
	{
		super(view);
		this.element = element;
	}
	
	@Override
	public void enter()
	{
	}
	
	@Override
	public void mouseDrag(MouseEvent e)
	{
		Rectangle rect = shape.getBounds();
		Point origin = new Point(Math.min(startPoint.getX(), e.getX()), Math.min(startPoint.getY(), e.getY()));
		rect.setOrigin(origin);
		rect.setWidth(Math.abs(e.getX() - startPoint.getX()));
		rect.setHeight(Math.abs(e.getY() - startPoint.getY()));
		
		view.repaint();
	}
	
	@Override
	public void mouseDown(MouseEvent e)
	{
		startPoint = new Point(e.getX(), e.getY());
		shape = new GraphicEllipseShape(new Rectangle(startPoint, 0, 0));
		view.getTempShapes().add(shape);

		view.repaint();
	}
	
	@Override
	public void mouseUp(MouseEvent e)
	{
		view.getTempShapes().clear();
		GraphicElementInvoker.getInstance().executeCommand(new GraphicElementAddShapeCommand(element, shape));
	}
}
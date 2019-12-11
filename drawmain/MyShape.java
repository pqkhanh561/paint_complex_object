import java.awt.*;

public class MyShape extends AbstractDrawable{
	public MyShape(State state){
		super(state);
	}	
	@Override
	public Shape getShape(){
		return(new Rectangle(0,0,90,90));
	}
}

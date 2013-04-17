package net.enib.mymorse;

public abstract class AbstractInterfaceController {

	final int POINT_UNIT = 1;
	final int TRAIT_UNIT = 3;
	final int SLASH_UNIT = 7;
	
	protected AbstractInterfaceController(){
		
	}
	
	public void espace(int pointTime){
		try{
			Thread.sleep(pointTime*TRAIT_UNIT);
		} catch (Exception e){
			e.printStackTrace(); 
		}
	}
	
	public void slash(int pointTime){
		try{
			Thread.sleep(pointTime*SLASH_UNIT);
		} catch (Exception e){
			e.printStackTrace(); 
		}
	}
	
	public void pointOff(int pointTime){
		try{
			Thread.sleep(pointTime*POINT_UNIT);
		} catch (Exception e){
			e.printStackTrace(); 
		}
	}
	
	public void traitOff(int pointTime){
		try{
			Thread.sleep(pointTime*TRAIT_UNIT);
		} catch (Exception e){
			e.printStackTrace(); 
		}
	}
	
	
	abstract public void pointOn(int pointTime);
	abstract public void traitOn(int pointTime);
}

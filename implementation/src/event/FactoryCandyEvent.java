package event;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;



public class FactoryCandyEvent {
	
	private static volatile FactoryCandyEvent instance = null;
	private Map<String, Class<CandyEvent>> map = new HashMap<String, Class<CandyEvent> >();
	
	private FactoryCandyEvent(){
		initMap();
	}
	public final static FactoryCandyEvent getInstance() {
		if (FactoryCandyEvent.instance == null) {
			synchronized(FactoryCandyEvent.class) {
				if (FactoryCandyEvent.instance == null) {
					FactoryCandyEvent.instance = new FactoryCandyEvent();
				}
			}
		}
		return FactoryCandyEvent.instance;
	}
	public  CandyEvent creerCandyEvent(String nom,Object source, Object value) {
		try {
			return (CandyEvent) map.get(nom).getDeclaredConstructor(Object.class,Object.class).newInstance(source,value) ;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int getSizeMap(){
		return map.size();
	}
	public String[] getNameIObjects(){
		return (String[]) map.keySet().toArray(new String[map.size()]) ;
	}
	@SuppressWarnings("unchecked")
	private void initMap(){
		
		try {
			map.put("CandyEventNewScore",(Class<CandyEvent>) Class.forName("event.candyEvent.CandyEventNewScore"));
			map.put("CandyEventNewDispositionGrid",(Class<CandyEvent>) Class.forName("event.candyEvent.CandyEventNewDispositionGrid"));
			map.put("CandyEventPlayStopZic",(Class<CandyEvent>) Class.forName("event.candyEvent.CandyEventPlayStopZic"));
			map.put("CandyEventChangeNom",(Class<CandyEvent>) Class.forName("event.candyEvent.CandyEventChangeNom"));
			map.put("CandyEvetNewLevel",(Class<CandyEvent>) Class.forName("event.candyEvent.CandyEvetNewLevel"));
			map.put("CandyEventGameOver",(Class<CandyEvent>) Class.forName("event.candyEvent.CandyEventGameOver"));
			map.put("CandyEventNbDeplacement",(Class<CandyEvent>) Class.forName("event.candyEvent.CandyEventNbDeplacement"));
			map.put("CandyEventNbDeplacementMAX",(Class<CandyEvent>) Class.forName("event.candyEvent.CandyEventNbDeplacementMAX"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}

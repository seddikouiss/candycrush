package modele;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class FactoryLevel {
	Map<String, Class<Level>> map = new HashMap<String, Class<Level> >();
	
	public FactoryLevel() {
		initMap();
	}
	public Level creerLevel(String nom,Partie partie) {		
		try {
			return (Level) map.get(nom).getDeclaredConstructor(Partie.class).newInstance(partie) ;
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
			map.put("Level1",(Class<Level>) Class.forName("modele.level.Level1"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}

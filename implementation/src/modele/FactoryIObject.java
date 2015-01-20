package modele;

import java.util.HashMap;
import java.util.Map;

public class FactoryIObject {
	
	Map<String, Class<IObject>> map = new HashMap<String, Class<IObject> >();
	
	public FactoryIObject() {
		initMap();
	}
	public IObject creerIObject(String nom) throws ClassNotFoundException, InstantiationException, IllegalAccessException{		
		return (IObject) map.get(nom).newInstance() ;
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
			map.put("Bonbon1",(Class<IObject>) Class.forName("modele.bonbon.Bonbon1"));
			map.put("Bonbon2",(Class<IObject>) Class.forName("modele.bonbon.Bonbon2"));
			map.put("Bonbon3",(Class<IObject>) Class.forName("modele.bonbon.Bonbon3"));
			map.put("Bonbon4",(Class<IObject>) Class.forName("modele.bonbon.Bonbon4"));
			map.put("Bonbon5",(Class<IObject>) Class.forName("modele.bonbon.Bonbon5"));
			map.put("Bonbon6",(Class<IObject>) Class.forName("modele.bonbon.Bonbon6"));
			map.put("Bonbon7",(Class<IObject>) Class.forName("modele.bonbon.Bonbon7"));
			map.put("Bonbon8",(Class<IObject>) Class.forName("modele.bonbon.Bonbon8"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}



}

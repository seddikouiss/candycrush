package modele.test;

import static org.junit.Assert.*;
import modele.FactoryIObject;

import org.junit.Before;
import org.junit.Test;

import modele.bonbon.* ;

public class FactoryIObjectTest {

	FactoryIObject f ;
	@Before
	public void init () throws ClassNotFoundException{
		f = new FactoryIObject() ;
	}
	@Test
	public void creerIObjectTest() throws Exception {
		assertTrue(f.creerIObject("Bonbon2") instanceof Bonbon2 ) ;
		assertTrue(f.creerIObject("Bonbon1") instanceof Bonbon1 ) ;
		assertTrue(f.creerIObject("Bonbon3") instanceof Bonbon3 ) ;
		assertTrue(f.creerIObject("Bonbon4") instanceof Bonbon4 ) ;
		assertTrue(f.creerIObject("Bonbon5") instanceof Bonbon5 ) ;
		assertTrue(f.creerIObject("Bonbon6") instanceof Bonbon6 ) ;
		assertTrue(f.creerIObject("Bonbon7") instanceof Bonbon7 ) ;
		assertTrue(f.creerIObject("Bonbon8") instanceof Bonbon8 ) ;
		
		
		assertFalse(f.creerIObject("Bonbon1") instanceof Bonbon8 ) ;
	}
	@Test(expected=Exception.class)
	public void creerIObjectTest2() throws Exception {
		f.creerIObject("xxxx") ;
	}

	@Test
	public void getNameIObjectsTest() {
		assertEquals(f.getSizeMap(), f.getNameIObjects().length);
	}

}

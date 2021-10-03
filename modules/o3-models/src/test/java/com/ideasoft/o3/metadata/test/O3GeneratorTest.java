//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: O3GeneratorTest.java,v 1.7 2006/05/19 14:36:47 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.test;

import com.ideasoft.o3.metadata.api.ConceptualModelSpec;
import com.ideasoft.o3.metadata.test.util.DefaultO3GeneratorTest;
import com.ideasoft.o3.metadata.util.XMLManager;
import com.ideasoft.o3.metadata.util.mdl.MDLGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This test checks the O3Generator functionalities
 * @author ignacio
 * @version $Revision: 1.7 $
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/com/ideasoft/o3/metadata/spring-application.xml", "/com/ideasoft/o3/metadata/test/spring-test.xml"})
public class O3GeneratorTest extends DefaultO3GeneratorTest {
	
	public static void main(String[] argv) throws Exception {
		System.setProperty("edf.cursor.checktime", "-1");
		
		ClassPathXmlApplicationContext applicationContext =	new ClassPathXmlApplicationContext(CONFIG_LOCATIONS);
		O3GeneratorTest o3GeneratorTest = new O3GeneratorTest();
		applicationContext.getBeanFactory().autowireBeanProperties(o3GeneratorTest, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);		
		o3GeneratorTest.generateSample();
	}
	
	@Test
	public void testSampleGeneration() throws Throwable {
		ConceptualModelSpec conceptualModel = (ConceptualModelSpec) xmlManager.unmarshall(O3GeneratorTest.class.getResourceAsStream("sample.xml"));
		mdlGenerator.getContext().put(MDLGenerator.TABLE_WHERE_CLAUSULE + "simulation.simScene", "where simulation_name = 'ancel2'");
		testModel(conceptualModel.getStarModel(), "sample", "/tmp/pepe.properties");
	}
	
	private void generateSample() throws Exception {
		ConceptualModelSpec conceptualModel = (ConceptualModelSpec) xmlManager.unmarshall(O3GeneratorTest.class.getResourceAsStream("sample.xml"));
		
		mdlGenerator.getContext().put(MDLGenerator.TABLE_WHERE_CLAUSULE + "simulation.simScene", "where simulation_name = 'ancel2'");
		
		generateModel(conceptualModel.getStarModel(), PATH, "sample", null);
		generateQuery(conceptualModel.getStarModel(), PATH, "sample");
		generateQueryPlugin(conceptualModel.getStarModel(), PATH, "sample");
	}

	public void setXmlManager(XMLManager xmlManager) {
		this.xmlManager = xmlManager;
	}
	
	@Autowired
	private XMLManager xmlManager;
//	private String PATH = "F:/Projects/o3/metadata/src/test/resources/com/ideasoft/o3/metadata/test/";
  private String PATH = "/tmp/";

	private static final String[] CONFIG_LOCATIONS =
		new String[] { "com/ideasoft/o3/metadata/spring-application.xml",
			"com/ideasoft/o3/metadata/test/spring-test.xml" };
	
}

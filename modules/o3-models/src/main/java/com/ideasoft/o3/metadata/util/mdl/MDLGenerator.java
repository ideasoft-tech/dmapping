//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2000-2001, IdeaSoft. All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: MDLGenerator.java,v 1.13 2006/05/19 14:36:10 ignacio Exp $
//-----------------------------------------------------------------------

package com.ideasoft.o3.metadata.util.mdl;

import com.ideasoft.o3.metadata.MDLGenerationException;
import ideasoft.util.log.ILog;
import ideasoft.util.log.ILogFactory;
import ideasoft.util.security.ISCipher;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogSystem;

import com.ideasoft.edf.core.env.SessionContext;
import com.ideasoft.o3.metadata.api.StarModelSpec;
import com.ideasoft.o3.metadata.util.mdl.api.O3ModelSpec;

/**
 * This class generate mdl's based on templates
 * @author ignacio
 * @version $Revision: 1.13 $
 */
public class MDLGenerator {

	public MDLGenerator() throws Exception {
		ve = new VelocityEngine();
	}
	
	/**
	 * @param context Is context to be added to the execution
	 */
	public void setContext(Map<String, Object> context) {
		this.context = context;
	}
	
	/**
	 * @return the context to be added to the execution
	 */
	public Map<String, Object> getContext() {
		return context;
	}

	/**
	 * @param sessionContext Is edf session context
	 * @param starModel An star model to build the API
	 * @return An string with the text for o3 model
	 * @throws com.ideasoft.o3.metadata.MDLGenerationException If some problem ocurrs in the generation
	 */
	public String getO3Model(SessionContext sessionContext, StarModelSpec starModel)
		throws MDLGenerationException {
		return getO3Model(sessionContext, starModel, null);
	}

	/**
	 * @param sessionContext Is edf session context
	 * @param starModel An star model to build the API
	 * @param propertiesFile
	 * @return An string with the text for o3 model
	 * @throws MDLGenerationException If some problem ocurrs in the generation
	 */
	public String getO3Model(SessionContext sessionContext, StarModelSpec starModel, String propertiesFile)
		throws MDLGenerationException {
		try {
			init();
		} catch (Exception ex) {
			throw new MDLGenerationException("Some problem ocurrs with velociy initialization.", ex);
		}

		VelocityContext velocityContext = new VelocityContext();

		ApiFactory starModelMDLApiWrapperFactory = new ApiFactory(propertiesFile);
		starModelMDLApiWrapperFactory.setContext(context);

		// Set Model variable
		try {
			O3ModelSpec o3Model = starModelMDLApiWrapperFactory.getO3Model(sessionContext, starModel);
			velocityContext.put(O3_MODEL_API_VARIABLE, o3Model);
		} catch (Exception ex) {
			log.debug(ex);
			throw new MDLGenerationException("The template aplication was failed.", ex);
		}

		// Set cipher variable
		velocityContext.put(CIPHER_VARIABLE, ISCipher.getInstance());
		
		// Set dinamic context
		for (String variable : context.keySet()) {
			velocityContext.put(variable, context.get(variable));
		}
		
		// Get the template
		Reader reader = new InputStreamReader(MDLGenerator.class.getResourceAsStream(O3_MDL_MODEL_TEMPLATE));

		StringWriter sw = new StringWriter();

		// Evaluate the template
		try {
			ve.evaluate(velocityContext, sw, "O3 Model", reader);
			sw.close();
		} catch (Exception ex) {
			log.debug(ex);
			throw new MDLGenerationException("The template application was failed.", ex);
		}

		return sw.getBuffer().toString();
	}

	private void init() throws Exception {
		ve.setProperty("resource.loader", "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		ve.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM, new LogSystem() {
			public void init(RuntimeServices rsvc) throws Exception {
			}

			public void logVelocityMessage(int level, String message) {
				switch (level) {
					case LogSystem.DEBUG_ID: log.debug(message); break;
					case LogSystem.INFO_ID: log.notice(message); break;
					case LogSystem.WARN_ID: log.warning(message); break;
					case LogSystem.ERROR_ID: log.error(message); break;
				}
			}
		});

		ve.init();
	}

	private VelocityEngine ve;
	private Map<String, Object> context = new HashMap<String, Object>();

	// Common variables
	private static final String CIPHER_VARIABLE = "cipher";
	private static final String O3_MODEL_API_VARIABLE = "o3Model";
	
	// Common properties
	public static final String TABLE_WHERE_CLAUSULE = ApiFactory.TABLE_WHERE_CLAUSULE;

	private static final String TEMPLATE_ROOT = "/com/ideasoft/o3/metadata/templates/";
	private static final String O3_MDL_MODEL_TEMPLATE = TEMPLATE_ROOT + "o3model.vm";

	private static ILog log = ILogFactory.getILog(MDLGenerator.class);

}

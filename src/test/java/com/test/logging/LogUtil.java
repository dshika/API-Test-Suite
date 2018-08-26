package com.test.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class LogUtil {
	private static Logger LOGGER = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
	
	public static Logger getLogger(){
		return LOGGER;
	}

}

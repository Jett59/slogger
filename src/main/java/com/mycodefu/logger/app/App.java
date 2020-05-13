package com.mycodefu.logger.app;

import com.mycodefu.logger.Logger;
import com.mycodefu.logger.LoggingLevel;

public class App {
public static void main(String[] args) {
	Logger logger = new Logger(LoggingLevel.info);
	logger.logWarning("test");
	
}
}

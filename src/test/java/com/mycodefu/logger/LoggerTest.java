package com.mycodefu.logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LoggerTest {

@Test
void logError_valid() throws Exception{
	Logger logger = new Logger(LoggingLevel.errors, "java.base");
	assertTrue(logger.logError(new Exception("testing")));
	logger.setLoggingLevel(LoggingLevel.trace);
	assertTrue(logger.logError(new Exception("testing")));
}
@Test
void logError_invalid() throws Exception{
	Logger logger = new Logger(LoggingLevel.off, "com.mycodefu.logger");
	assertFalse(logger.logError(new Exception("testing")));
}
@Test
void logWarning_valid() throws Exception{
	Logger logger = new Logger(LoggingLevel.warnings, "com.mycodefu.logger");
	assertTrue(logger.logWarning("test"));
	logger.setLoggingLevel(LoggingLevel.trace);
	assertTrue(logger.logWarning("test"));
}
@Test
void logWarning_invalid() {
	Logger logger = new Logger(LoggingLevel.errors, "com.myycodefu.logger");
	assertFalse(logger.logWarning("testing"));
}
@Test
void logInfo_valid() {
	Logger logger = new Logger(LoggingLevel.info, "com.mycodefu.logger");
	assertTrue(logger.logInfo("test"));
	logger.setLoggingLevel(LoggingLevel.trace);
	assertTrue(logger.logInfo("test"));
}
@Test
void logInfo_invalid() {
	Logger logger = new Logger(LoggingLevel.warnings, "com.mycodefu.logger");
	assertFalse(logger.logInfo("testing"));
}
@Test
void logDebugInfo_valid() {
	Logger logger = new Logger(LoggingLevel.debug, "com.mycodefu.logger");
	assertTrue(logger.logDebugInfo("test"));
	logger.setLoggingLevel(LoggingLevel.trace);
	assertTrue(logger.logDebugInfo("test"));
}
@Test
void logDebugInfo_invalid() {
	Logger logger = new Logger(LoggingLevel.info, "com.mycodefu.logger");
	assertFalse(logger.logDebugInfo("testing"));
}
@Test
void logTraceInfo_valid() {
	Logger logger = new Logger(LoggingLevel.trace, "com.mycodefu.logger");
	assertTrue(logger.logTraceInfo("test"));
}
@Test
void logTraceInfo_invalid() {
	Logger logger = new Logger(LoggingLevel.debug, "com.mycodefu.logger");
	assertFalse(logger.logTraceInfo("testing"));
}
}

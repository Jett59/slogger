package com.mycodefu.logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class LoggerTest {
PrintStream out = null;

public LoggerTest() throws Exception{
	out = new PrintStream(System.currentTimeMillis()+".log");
}
@Test
void logError_valid() throws Exception{
	Logger logger = new Logger(LoggingLevel.errors, out);
	assertTrue(logger.logError(new Exception("testing")));
	logger.setLoggingLevel(LoggingLevel.trace);
	assertTrue(logger.logError(new Exception("testing")));
}
@Test
void logError_invalid() throws Exception{
	Logger logger = new Logger(LoggingLevel.off, out);
	assertFalse(logger.logError(new Exception("testing")));
}
@Test
void logWarning_valid() throws Exception{
	Logger logger = new Logger(LoggingLevel.warnings, out);
	assertTrue(logger.logWarning("test"));
	logger.setLoggingLevel(LoggingLevel.trace);
	assertTrue(logger.logWarning("test"));
}
@Test
void logWarning_invalid() {
	Logger logger = new Logger(LoggingLevel.errors, out);
	assertFalse(logger.logWarning("testing"));
}
@Test
void logInfo_valid() {
	Logger logger = new Logger(LoggingLevel.info, out);
	assertTrue(logger.logInfo("test"));
	logger.setLoggingLevel(LoggingLevel.trace);
	assertTrue(logger.logInfo("test"));
}
@Test
void logInfo_invalid() {
	Logger logger = new Logger(LoggingLevel.warnings, out);
	assertFalse(logger.logInfo("testing"));
}
@Test
void logDebugInfo_valid() {
	Logger logger = new Logger(LoggingLevel.debug, out);
	assertTrue(logger.logDebugInfo("test"));
	logger.setLoggingLevel(LoggingLevel.trace);
	assertTrue(logger.logDebugInfo("test"));
}
@Test
void logDebugInfo_invalid() {
	Logger logger = new Logger(LoggingLevel.info, out);
	assertFalse(logger.logDebugInfo("testing"));
}
@Test
void logTraceInfo_valid() {
	Logger logger = new Logger(LoggingLevel.trace, out);
	assertTrue(logger.logTraceInfo("test"));
}
@Test
void logTraceInfo_invalid() {
	Logger logger = new Logger(LoggingLevel.debug, out);
	assertFalse(logger.logTraceInfo("testing"));
}
}

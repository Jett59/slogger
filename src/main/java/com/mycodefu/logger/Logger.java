package com.mycodefu.logger;

import java.io.PrintStream;

public class Logger {
private LoggingLevel logLevel = LoggingLevel.errors;
private PrintStream out;

public  void setLoggingLevel(LoggingLevel loggingLevel) {
	logLevel = loggingLevel;
}

public LoggingLevel getLogLevel() {
	return logLevel;
}

public Logger(LoggingLevel loggingLevel, PrintStream out) {
	this.logLevel = loggingLevel;
	this.out = out;
}

public Logger() {
	this.out = System.out;
}

public boolean logError(Throwable error) {
	if(logLevel.getId() >= LoggingLevel.errors.getId()) {
	error.printStackTrace(out);
	return true;
	}
	return false;
}

public boolean logWarning(String warning) {
	return log(warning, LoggingLevel.warnings);
}

public boolean logInfo(String info) {
	return log(info, LoggingLevel.info);
}

public boolean logDebugInfo(String debugInfo) {
	return log(debugInfo, LoggingLevel.debug);
}

public boolean logTraceInfo(String traceInfo) {
	return log(traceInfo, LoggingLevel.trace);
}

private boolean log(String log, LoggingLevel loggingLevel) {
	if(logLevel.getId() >= loggingLevel.getId()) {
		out.println(String.format("[%s] %s", loggingLevel.name(), log));
		return true;
	}
	return false;
}
}

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
	if(this.logLevel.getId() >= loggingLevel.getId()) {
		if(this.logLevel.equals(LoggingLevel.trace)) {
			var callerElement = new Exception().getStackTrace()[1];
			out.printf("[%s] %s, on line %s of class %s in module %s\n", loggingLevel.name(), log, callerElement.getLineNumber(), callerElement.getClassName(), callerElement.getModuleName());
		}
		out.printf("[%s] %s\n", loggingLevel.name(), log);
		return true;
	}
	return false;
}
}

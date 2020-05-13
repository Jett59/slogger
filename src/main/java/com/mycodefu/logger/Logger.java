package com.mycodefu.logger;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class Logger {
private LoggingLevel logLevel = LoggingLevel.errors;
private PrintStream console;
private PrintStream log;
private String[] moduleInterest;

public  void setLoggingLevel(LoggingLevel loggingLevel) {
	logLevel = loggingLevel;
}

public LoggingLevel getLogLevel() {
	return logLevel;
}

public Logger(LoggingLevel loggingLevel, PrintStream console, PrintStream log, String... moduleInterest) {
	this.logLevel = loggingLevel;
	this.console =console;
	this.log = log;
	this.moduleInterest = moduleInterest;
}

public Logger(LoggingLevel loggingLevel, PrintStream console, PrintStream log) {
	this.logLevel = loggingLevel;
	this.console = console;
	this.log = log;
}

public Logger(LoggingLevel loggingLevel, PrintStream log, String... moduleInterest) {
	this.setLoggingLevel(loggingLevel);
	this.console = System.out;
	this.log = log;
	this.moduleInterest = moduleInterest;
}

public Logger(LoggingLevel logLevel, PrintStream log) {
	this.logLevel = logLevel;
	this.console = System.out;
	this.log = log;
}

public Logger(LoggingLevel loggingLevel, String... moduleInterest) {
	this.setLoggingLevel(loggingLevel);
	this.console = System.out;
	this.log = defaultLog();
	this.moduleInterest = moduleInterest;
}

public Logger(PrintStream log, String... moduleInterest) {
	this.console = System.out;
	this.log = log;
	this.moduleInterest = moduleInterest;
}

public Logger(PrintStream log) {
	this.console = System.out;
	this.log = log;
}

public Logger(LoggingLevel logLevel) {
	this.logLevel = logLevel;
	this.console = System.out;
	this.log = defaultLog();
}

public Logger(String... moduleInterest) {
	this.console = System.out;
	this.log = defaultLog();
	this.moduleInterest = moduleInterest;
}

public Logger() {
	this.console = System.out;
	this.log = defaultLog();
}

public boolean logError(Throwable error) {
	if(logLevel.getId() >= LoggingLevel.errors.getId()) {
		if(moduleInterest == null) {
			error.printStackTrace(console);
			error.printStackTrace(log);
		}else {
			console.println(error.toString());
			log.println(error.toString());
			for(var stackTraceElement : error.getStackTrace()) {
				for(String moduleName : moduleInterest) {
					if(stackTraceElement.getModuleName() == moduleName) {
						console.println("@"+stackTraceElement.toString());
						log.println("@"+stackTraceElement.toString());
					}
				}
			}
		}
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
			var callerElement = new Exception().getStackTrace()[2];
			console.printf("[%s] %s, on line %s of class %s in module %s\n", loggingLevel.getDisplayName(), log, callerElement.getLineNumber(), callerElement.getClassName(), callerElement.getModuleName());
			this.log.printf("[%s] %s, on line %s of class %s in module %s\n", loggingLevel.getDisplayName(), log, callerElement.getLineNumber(), callerElement.getClassName(), callerElement.getModuleName());
		}
		console.printf("[%s] %s\n", loggingLevel.getDisplayName(), log);
		this.log.printf("[%s] %s\n", loggingLevel.getDisplayName(), log);
		return true;
	}
	return false;
}

private static Date date = new Date();

@SuppressWarnings({ "deprecation", "resource" })
private PrintStream defaultLog() {
	PrintStream result;
	try {
		result = new PrintStream(String.format("logs/%d-%d-%d-%d-%d-%d.log", date.getYear()+1900, date.getMonth()+1, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds()));
		}catch (FileNotFoundException e) {
			System.out.println("[info] log file not found: creating directory");
			try {
			Files.createDirectory(Paths.get("logs"));
			result = defaultLog();
			}catch (Exception e1) {
				throw new RuntimeException(e1);
			}
		}
	return result;
}
}

package com.mycodefu.logger;

public  enum LoggingLevel {
	off(-1, null),
	errors(0, "error"),
	warnings(1, "warning"),
	info(2, "info"),
	debug(3, "fine"),
	trace(4, "trace");

private int id;
private String displayName;

private LoggingLevel(int id, String displayName) {
	this.id = id;
	this.displayName = displayName;
}

public int getId() {
	return id;
}

public String getDisplayName() {
	return displayName;
}
	}

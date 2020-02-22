package com.mycodefu.logger;

public  enum LoggingLevel {
	off(-1),
	errors(0),
	warnings(1),
	info(2),
	debug(3),
	trace(4);

private int id;

private LoggingLevel(int id) {
	this.id = id;
}

public int getId() {
	return id;
}
	}

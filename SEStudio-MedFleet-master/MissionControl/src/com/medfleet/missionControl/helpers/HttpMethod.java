package com.medfleet.missionControl.helpers;

public enum HttpMethod {
	post, get, delete;
	
	public String toString() {
		return name().toUpperCase();
	}
}

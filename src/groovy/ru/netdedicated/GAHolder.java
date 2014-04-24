package ru.netdedicated;
import org.codehaus.groovy.grails.commons.GrailsApplication;

class GAHolder {
	private static GrailsApplication grailsApplication;
	public static void setGrailsApplication(GrailsApplication application){
		grailsApplication = application;
	}
	public static GrailsApplication getGrailsApplication(){
		return grailsApplication;
	}
}

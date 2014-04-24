package ru.netdedicated;

import org.codehaus.groovy.grails.commons.DomainClassArtefactHandler;
import org.codehaus.groovy.grails.commons.GrailsApplication;
import org.codehaus.groovy.grails.commons.GrailsDomainClass;

import java.util.ArrayList;
import java.util.List;

class GrailsUtils {
	public static Class getDomainClass(String name) throws Exception{
		// GrailsApplication application = GAHolder.getGrailsApplication();
		// if(application == null) throw new Exception("Application reference is not set");
		// GrailsDomainClass domain = (GrailsDomainClass) application.getArtefact(DomainClassArtefactHandler.TYPE, name) ;
		GrailsDomainClass domain = null;
		for(GrailsDomainClass dom : listDomainClasses() ){
			if(dom.getName().equals(name)){
				domain = dom;
			}
		}
		return domain.getClazz();
	}
	public static List<GrailsDomainClass> listDomainClasses() throws Exception{
		GrailsApplication application = GAHolder.getGrailsApplication();
		if(application == null) throw new Exception("Application reference is not set");
		List<GrailsDomainClass> domainClasses = new ArrayList<GrailsDomainClass>();
        for (int i = 0, max = application.getArtefacts(DomainClassArtefactHandler.TYPE).length; i < max; i++) {
            GrailsDomainClass grailsDomainClass = (GrailsDomainClass) application.getArtefacts(DomainClassArtefactHandler.TYPE)[i];
            domainClasses.add(grailsDomainClass);
        }
        return domainClasses;
	}
}

package ru.netdedicated.license;


class PluginConfig { 
	String key;
	String value;
	
	// String nscpUrl;
	// String nscpUsername;
	// String nscpPassword;
	static constraints = { 
		key inList : ['licenseUrl', 'licensePassword', 'licenseUsername'], unique: true
	}
	static transients = ['configFields']
	static configFields = [ 'licenseUrl' : 'License Server URL', 'licenseUsername' : 'License Server Username', 'licensePassword' : 'License Server Password']
}

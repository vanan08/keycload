package com.prudential;

public enum ModulesEnum {

	REPMODE,
	SALESMO,
	SGSMODE,
	PRURAISE,
	IFILECLAI,
	IDOC,
	IACT,
	EAPPROV,
	LOGOUT,
	PRUINFO,
	QUALITYB,
	USINESS,
	PRUGRAD,
	PRUCOAC;
	
	public String toString() {
		if (this == REPMODE) return "REPMODE";
		else if (this == SALESMO) return "SALESMO";
		else if (this == SGSMODE) return "SGSMODE";
		else if (this == PRURAISE) return "PRURAISE";
		else if (this == IFILECLAI) return "IFILECLAI";
		else if (this == IDOC) return "IDOC";
		else if (this == IACT) return "IACT";
		else if (this == LOGOUT) return "LOGOUT";
		else if (this == PRUINFO) return "PRUINFO";
		else if (this == USINESS) return "USINESS";
		else if (this == PRUGRAD) return "PRUGRAD";
		else if (this == PRUCOAC) return "PRUCOAC";
		else return null;
	}
	
}

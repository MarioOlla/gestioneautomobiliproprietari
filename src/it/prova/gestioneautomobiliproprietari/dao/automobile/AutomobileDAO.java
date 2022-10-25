package it.prova.gestioneautomobiliproprietari.dao.automobile;

import java.util.List;

import it.prova.gestioneautomobiliproprietari.dao.IBaseDAO;
import it.prova.gestioneautomobiliproprietari.model.Automobile;

public interface AutomobileDAO extends IBaseDAO<Automobile> {
	
	public List<Automobile> allAutomobiliConCodiceFiscaleProprietarioIniziaCon(String input)throws Exception;
	
	public List<Automobile> allAutomobiliConErrori()throws Exception;
}

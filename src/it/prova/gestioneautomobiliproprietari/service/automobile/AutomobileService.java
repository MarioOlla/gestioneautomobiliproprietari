package it.prova.gestioneautomobiliproprietari.service.automobile;

import java.util.List;

import it.prova.gestioneautomobiliproprietari.dao.automobile.AutomobileDAO;
import it.prova.gestioneautomobiliproprietari.model.Automobile;

public interface AutomobileService {

	void setAutomobileDAO(AutomobileDAO automobileDAOInstance);
	
	public List<Automobile> listAllAutomobili() throws Exception;

	public Automobile findById(Long id) throws Exception;

	public void aggiorna(Automobile input) throws Exception;

	public void inserisciNuovo(Automobile input) throws Exception;

	public void rimuovi(Automobile input) throws Exception;

}

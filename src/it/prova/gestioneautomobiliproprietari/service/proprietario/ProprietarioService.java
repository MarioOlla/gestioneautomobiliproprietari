package it.prova.gestioneautomobiliproprietari.service.proprietario;

import java.util.List;

import it.prova.gestioneautomobiliproprietari.dao.proprietario.ProprietarioDAO;
import it.prova.gestioneautomobiliproprietari.model.Proprietario;

public interface ProprietarioService {

	void setProprietarioDAO(ProprietarioDAO proprietarioDAOInstance);
	
	public List<Proprietario> listAllProprietari() throws Exception;

	public Proprietario findById(Long id) throws Exception;

	public void aggiorna(Proprietario input) throws Exception;

	public void inserisciNuovo(Proprietario input) throws Exception;

	public void rimuovi(Proprietario input) throws Exception;

}

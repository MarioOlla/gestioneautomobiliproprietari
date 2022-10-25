package it.prova.gestioneautomobiliproprietari.dao.proprietario;

import it.prova.gestioneautomobiliproprietari.dao.IBaseDAO;
import it.prova.gestioneautomobiliproprietari.model.Proprietario;

public interface ProprietarioDAO extends IBaseDAO<Proprietario> {

	public Long countQuantConAutoImmatricolataDal(int dataDa)throws Exception;
}

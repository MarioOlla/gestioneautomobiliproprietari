package it.prova.gestioneautomobiliproprietari.service;

import it.prova.gestioneautomobiliproprietari.dao.MyDaoFactory;
import it.prova.gestioneautomobiliproprietari.service.automobile.AutomobileService;
import it.prova.gestioneautomobiliproprietari.service.automobile.AutomobileServiceImpl;
import it.prova.gestioneautomobiliproprietari.service.proprietario.ProprietarioService;
import it.prova.gestioneautomobiliproprietari.service.proprietario.ProprietarioServiceImpl;

public class MyServiceFactory {

	// rendiamo le istanze restituite SINGLETON
	private static AutomobileService automobileServiceInstance = null;
	private static ProprietarioService proprietarioServiceInstance = null;

	public static AutomobileService getAbitanteServiceInstance() {
		if (automobileServiceInstance == null) {
			automobileServiceInstance = new AutomobileServiceImpl();
			automobileServiceInstance.setAutomobileDAO(MyDaoFactory.getAutomobileDAOInstance());
		}
		return automobileServiceInstance;
	}

	public static ProprietarioService getMunicipioServiceInstance() {
		if (proprietarioServiceInstance == null) {
			proprietarioServiceInstance = new ProprietarioServiceImpl();
			proprietarioServiceInstance.setProprietarioDAO(MyDaoFactory.getProprietarioDAOInstance());
		}
		return proprietarioServiceInstance;
	}

}

package it.prova.gestioneautomobiliproprietari.test;

import java.text.SimpleDateFormat;

import it.prova.gestioneautomobiliproprietari.dao.EntityManagerUtil;
import it.prova.gestioneautomobiliproprietari.model.Automobile;
import it.prova.gestioneautomobiliproprietari.model.Proprietario;
import it.prova.gestioneautomobiliproprietari.service.MyServiceFactory;
import it.prova.gestioneautomobiliproprietari.service.automobile.AutomobileService;
import it.prova.gestioneautomobiliproprietari.service.proprietario.ProprietarioService;

public class TestAutomobiliProprietari {

	public static void main(String[] args) {

		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {

			System.out.println(
					"Nel DB di proprietari ci sono " + proprietarioService.listAllProprietari().size() + " voci.");
			
			System.out.println(
					"Nel DB di automobili ci sono " + automobileService.listAllAutomobili().size() + " voci.");
			
			testInsertProprietario(proprietarioService);
			System.out.println(
					"Nel DB di proprietari ci sono " + proprietarioService.listAllProprietari().size() + " voci.");
			
			testFindProprietario(proprietarioService);
			System.out.println(
					"Nel DB di proprietari ci sono " + proprietarioService.listAllProprietari().size() + " voci.");
			
			testUpdateProprietario(proprietarioService);
			System.out.println(
					"Nel DB di proprietari ci sono " + proprietarioService.listAllProprietari().size() + " voci.");
			
			testInsertAutomobile(automobileService, proprietarioService);
			System.out.println(
					"Nel DB di automobili ci sono " + automobileService.listAllAutomobili().size() + " voci.");
			
			testFindAutomobile(automobileService);
			System.out.println(
					"Nel DB di automobili ci sono " + automobileService.listAllAutomobili().size() + " voci.");
			
			testUpdateAutomobile(automobileService);
			System.out.println(
					"Nel DB di automobili ci sono " + automobileService.listAllAutomobili().size() + " voci.");
			
			testDeleteAutomobile(automobileService);
			System.out.println(
					"Nel DB di automobili ci sono " + automobileService.listAllAutomobili().size() + " voci.");
			
			testDeleteProprietario(proprietarioService);
			System.out.println(
					"Nel DB di proprietari ci sono " + proprietarioService.listAllProprietari().size() + " voci.");

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	private static void testFindProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println("__inizio testFindProprietario...");
		if(proprietarioService.listAllProprietari().isEmpty())
			throw new RuntimeException("FAILED : database vuoto.");
		Long idCercato = proprietarioService.listAllProprietari().get(0).getId();
		Proprietario result = proprietarioService.findById(idCercato);
		if (result == null)
			throw new RuntimeException("FAILED : la ricerca non ha dato i risultati attesi.");
		System.out.println("__fine testFindProprietario : PASSED");
	}

	private static void testInsertProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println("__inizio testInsertProprietario...");
		Proprietario nuovoProprietario = new Proprietario("Marco", "Verdi", "MRCVRD99C22H501M", new SimpleDateFormat("yyyy-mm-dd").parse("1999-03-22"));
		if(nuovoProprietario.getId()!= null)
			throw new RuntimeException("FAILED : il record e' gia' in database.");
		proprietarioService.inserisciNuovo(nuovoProprietario);
		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("FAILED : il nuovo record non e' stato inserito.");
		System.out.println("__fine testInsertProprietario : PASSED");
	}

	private static void testUpdateProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println("__inizio testUpdateProprietario...");
		if(proprietarioService.listAllProprietari().isEmpty())
			throw new RuntimeException("FAILED : database vuoto.");
		Proprietario proprietarioAggiornato = new Proprietario("Paolo", "Bianchi", "CF di Paolo", new SimpleDateFormat("yyyy-mm-dd").parse("2010-03-22"));
		proprietarioAggiornato.setId(proprietarioService.listAllProprietari().get(0).getId());
		proprietarioService.aggiorna(proprietarioAggiornato);
		if (!proprietarioService.listAllProprietari().get(0).getCognome().equals("Bianchi"))
			throw new RuntimeException("FAILED : il record non e' stato aggiornato.");
		System.out.println("__fine testUpdateProprietario : PASSED");
	}

	private static void testDeleteProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println("__inizio testDeleteProprietario...");
		if(proprietarioService.listAllProprietari().isEmpty())
			throw new RuntimeException("FAILED : database vuoto.");
		Proprietario daEliminare = proprietarioService.listAllProprietari().get(0);
		proprietarioService.rimuovi(daEliminare);
		if (!proprietarioService.listAllProprietari().isEmpty())
			throw new RuntimeException("FAILED : il record non e' stato cancellato.");
		System.out.println("__fine testDeleteProprietario : PASSED");
	}
	
	private static void testFindAutomobile(AutomobileService automobileService) throws Exception {
		System.out.println("__inizio testFindAutomobile...");
		if(automobileService.listAllAutomobili().isEmpty())
			throw new RuntimeException("FAILED : database vuoto.");
		Long idCercato = automobileService.listAllAutomobili().get(0).getId();
		Automobile result = automobileService.findById(idCercato);
		if (result == null)
			throw new RuntimeException("FAILED : la ricerca non ha dato i risultati attesi.");
		System.out.println("__fine testFindAutomobile : PASSED");
	}

	private static void testInsertAutomobile(AutomobileService automobileService,ProprietarioService proprietarioService) throws Exception {
		System.out.println("__inizio testInsertAutomobile...");
		if(proprietarioService.listAllProprietari().isEmpty())
			throw new RuntimeException("FAILED : database di proprietari vuoto.");
		Automobile daInserire = new Automobile("Opel", "Agila", "KS563ND", 2016);
		daInserire.setProprietario(proprietarioService.listAllProprietari().get(0));
		if(daInserire.getId()!= null)
			
			throw new RuntimeException("FAILED : il record e' gia' in database.");
		automobileService.inserisciNuovo(daInserire);
		if (daInserire.getId() == null)
			throw new RuntimeException("FAILED : il nuovo record non e' stato inserito.");
		System.out.println("__fine testInsertAutomobile : PASSED");
	}

	private static void testUpdateAutomobile(AutomobileService automobileService) throws Exception {
		System.out.println("__inizio testUpdateAutomobile...");
		if(automobileService.listAllAutomobili().isEmpty())
			throw new RuntimeException("FAILED : database vuoto.");
		Automobile automobileAggiornata = new Automobile("Nissan", "Micra", "DX821CN", 2012); 
		automobileAggiornata.setId(automobileService.listAllAutomobili().get(0).getId());
		automobileService.aggiorna(automobileAggiornata);
		if (!automobileService.listAllAutomobili().get(0).getModello().equals("Micra"))
			throw new RuntimeException("FAILED : il record non e' stato aggiornato.");
		System.out.println("__fine testUpdateAutomobile : PASSED");
	}

	private static void testDeleteAutomobile(AutomobileService automobileService) throws Exception {
		System.out.println("__inizio testDeleteAutomobile...");
		if(automobileService.listAllAutomobili().isEmpty())
			throw new RuntimeException("FAILED : database vuoto.");
		Automobile daEliminare = automobileService.listAllAutomobili().get(0);
		automobileService.rimuovi(daEliminare);
		if(!automobileService.listAllAutomobili().isEmpty())
			throw new RuntimeException("FAILED : il record non e' stato cancellato.");
		System.out.println("__fine testDeleteAutomobile : PASSED");
	}

}

package it.prova.gestioneautomobiliproprietari.service.proprietario;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneautomobiliproprietari.dao.EntityManagerUtil;
import it.prova.gestioneautomobiliproprietari.dao.proprietario.ProprietarioDAO;
import it.prova.gestioneautomobiliproprietari.model.Proprietario;

public class ProprietarioServiceImpl implements ProprietarioService {

	private ProprietarioDAO proprietarioDAO;

	@Override
	public void setProprietarioDAO(ProprietarioDAO proprietarioDAOInstance) {
		this.proprietarioDAO = proprietarioDAOInstance;

	}

	@Override
	public List<Proprietario> listAllProprietari() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			proprietarioDAO.setEntityManager(entityManager);

			return proprietarioDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public Proprietario findById(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			proprietarioDAO.setEntityManager(entityManager);

			return proprietarioDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Proprietario input) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			proprietarioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			proprietarioDAO.update(input);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuovo(Proprietario input) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			proprietarioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			proprietarioDAO.insert(input);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Proprietario input) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			proprietarioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			proprietarioDAO.delete(input);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

}

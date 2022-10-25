package it.prova.gestioneautomobiliproprietari.dao.automobile;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneautomobiliproprietari.model.Automobile;

public class AutomobileDAOImpl implements AutomobileDAO {

	private EntityManager entityManager;

	@Override
	public List<Automobile> list() throws Exception {
		return entityManager.createQuery("from Automobile", Automobile.class).getResultList();
	}

	@Override
	public Automobile get(Long id) throws Exception {
		return entityManager.find(Automobile.class, id);
	}

	@Override
	public void update(Automobile o) throws Exception {
		if (o == null)
			throw new Exception("input non valido");
		entityManager.merge(o);
	}

	@Override
	public void insert(Automobile o) throws Exception {
		if (o == null)
			throw new Exception("input non valido");
		entityManager.persist(o);

	}

	@Override
	public void delete(Automobile o) throws Exception {
		if (o == null)
			throw new Exception("input non valido");
		entityManager.remove(entityManager.merge(o));

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

	@Override
	public List<Automobile> allAutomobiliConCodiceFiscaleProprietarioIniziaCon(String input) throws Exception {
		TypedQuery<Automobile> result = entityManager.createQuery(
				"select a from Proprietario p inner join p.automobili a where p.codiceFiscale like ?1",
				Automobile.class);
		return result.setParameter(1, input + "%").getResultList();
	}

	@Override
	public List<Automobile> allAutomobiliConErrori() throws Exception {
		return entityManager.createQuery(
				"select a from Proprietario p inner join p.automobili a where year(CURRENT_DATE)-year(p.dataDiNascita) < 18",
				Automobile.class).getResultList();
	}

}

package it.prova.gestioneautomobiliproprietari.dao.proprietario;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneautomobiliproprietari.model.Proprietario;

public class ProprietarioDAOImpl implements ProprietarioDAO {

	private EntityManager entityManager;

	@Override
	public List<Proprietario> list() throws Exception {
		return entityManager.createQuery("from Proprietario", Proprietario.class).getResultList();
	}

	@Override
	public Proprietario get(Long id) throws Exception {
		return entityManager.find(Proprietario.class, id);
	}

	@Override
	public void update(Proprietario o) throws Exception {
		if (o == null)
			throw new Exception("input non valido");
		o = entityManager.merge(o);
	}

	@Override
	public void insert(Proprietario o) throws Exception {
		if (o == null)
			throw new Exception("input non valido");
		entityManager.persist(o);
	}

	@Override
	public void delete(Proprietario o) throws Exception {
		if (o == null)
			throw new Exception("input non valido");
		entityManager.remove(entityManager.merge(o));

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

	@Override
	public Long countQuantConAutoImmatricolataDal(int dataDa) throws Exception {
		 TypedQuery<Long> result = entityManager.createQuery("select count(p) from Proprietario p inner join p.automobili a where a.annoImmatricolazione >= ?1", Long.class);
		 return result.setParameter(1, dataDa).getSingleResult();
	}

}

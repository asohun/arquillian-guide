package org.arquillian.example.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.arquillian.example.bean.domain.Language;

/**
 * 
 */
@Stateless
@Path("/languages")
public class LanguageEndpoint {
	
	@PersistenceContext(unitName = "forge-default")
	private EntityManager em;

	@POST
	@Consumes("application/json")
	public Response create(Language entity) {
		em.persist(entity);
		return Response.created(
				UriBuilder.fromResource(LanguageEndpoint.class)
						.path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		Language entity = em.find(Language.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(entity);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		TypedQuery<Language> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT l FROM Language l WHERE l.id = :entityId ORDER BY l.id",
						Language.class);
		findByIdQuery.setParameter("entityId", id);
		Language entity;
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	@GET
	@Produces("application/json")
	public List<Language> listAll() {
		final List<Language> results = em.createQuery(
				"SELECT DISTINCT l FROM Language l ORDER BY l.id",
				Language.class).getResultList();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(Language entity) {
		entity = em.merge(entity);
		return Response.noContent().build();
	}
}
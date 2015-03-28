package org.arquillian.example.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.arquillian.example.bean.Language;

/**
 * @author asohun
 * @version 28/03/2015
 */
@Stateless
@Path("/languages")
public class LanguageEndpoint {

	/**
	 * 
	 */
	@PersistenceContext(unitName = "forge-default")
	private EntityManager em;

	/**
	 * @param entity
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Language entity) {
		em.persist(entity);
		UriBuilder uriBuilder = UriBuilder.fromResource(LanguageEndpoint.class);
		uriBuilder = uriBuilder.path(String.valueOf(entity.getId()));
		URI uri = uriBuilder.build();
		return Response.created(uri).build();
	}

	/**
	 * @param id
	 * @return
	 */
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

	/**
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
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

	/**
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Language> listAll() {
		final List<Language> results = em.createQuery(
				"SELECT DISTINCT l FROM Language l ORDER BY l.id",
				Language.class).getResultList();
		return results;
	}

	/**
	 * @param entity
	 * @return
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, Language entity) {
		entity = em.merge(entity);
		return Response.noContent().build();
	}

}
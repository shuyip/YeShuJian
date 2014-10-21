/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS_REST;

import em.SessionBeanLocal;
import em.UserEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Administrator
 */
@Stateless
@Path("user")
public class UserEntityFacadeREST extends AbstractFacade<UserEntity> {
    @EJB
    private SessionBeanLocal SB;    
    
    @PersistenceContext(unitName = "IS3261PU")
    private EntityManager em;

    public UserEntityFacadeREST() {
        super(UserEntity.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(UserEntity entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, UserEntity entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        System.out.println("remove is called");
        SB.removeUser(id);
    }    

    @GET        
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public UserEntity find(@PathParam("id") Long id) {
        System.out.println("find is called");
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<UserEntity> findAll() {
        System.out.println("findAll is called");
        return super.findAll();
    }        

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

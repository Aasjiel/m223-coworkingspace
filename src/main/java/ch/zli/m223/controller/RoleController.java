package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.service.RolesService;
import ch.zli.m223.model.Role;

@Path("/roles")
public class RoleController {
    @Inject
    RolesService roleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Roles.", description = "Returns a list of all Roles.")
    public List<Role> getAllRoles() {
            return roleService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index one Roles", description = "Returns a Roles.")
    @Path("/{id}")
    public List<Role> index(Long id) {   
            return List.of(roleService.getRoleById(id));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new Roles.", description = "Creates a new Roles and returns the newly added entry.")
    public Role create(Role role) {
       return roleService.createRole(role);
    }

    @DELETE
    @Operation(
        summary = "Deletes a Roles",
        description = "Deletes a specified Roles and returns not a single thing"
    )
    @Path("/{id}")
    public void delete(Long id) {
        roleService.deleteRole(id);
    }

    @PUT
    @Operation(
        summary = "Updates a Role",
        description = "Updates a role specified by the id"
    )
    @Path("/{id}")
    public void update(Long id, Role role){
        role.setId(id);
        roleService.updateRole(role);
    }
}

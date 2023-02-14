package org.acme.controller;

import org.acme.dto.ProductDTO;
import org.acme.service.ProductService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/products")
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDTO findProductByid(Long id) {
        return productService.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDTO> findAllProducts() {
        return productService.getAllProducts();
    }

    @POST
    @Transactional
    public Response saveProduct(ProductDTO product){
        try {
            productService.createNewProduct(product);
            return Response.ok().build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response changeProduct(@PathParam("id") Long id, ProductDTO product){
        try {
            productService.changeProduct(id, product);
            return Response.ok().build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteProduct(@PathParam("id") Long id){
        try {
            productService.deleteProduct(id);
            return Response.ok().build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
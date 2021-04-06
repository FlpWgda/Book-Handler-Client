package io.swagger.api;

import io.swagger.model.Genre;
import io.swagger.model.Publication;
import io.swagger.model.PublicationList;
import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-25T09:56:25.993Z[GMT]")
@RestController
public class GenreApiController implements GenreApi {

    private static final Logger log = LoggerFactory.getLogger(GenreApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public GenreApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<PublicationList> addGenre(@Parameter(in = ParameterIn.COOKIE, description = "User info to check genre addition rights" ,required=true,schema=@Schema()) @CookieValue(value="user", required=true) User user,@Parameter(in = ParameterIn.DEFAULT, description = "Genre object that needs to be added to the genre list", required=true, schema=@Schema()) @Valid @RequestBody PublicationList body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PublicationList>(objectMapper.readValue("{\n  \"createdBy\" : {\n    \"password\" : \"password\",\n    \"userRole\" : \"admin\",\n    \"email\" : \"email\",\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"username\" : \"username\"\n  },\n  \"name\" : \"name\",\n  \"id\" : 0,\n  \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"publications\" : [ {\n    \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n    \"author\" : \"author\",\n    \"addedBy\" : {\n      \"password\" : \"password\",\n      \"userRole\" : \"admin\",\n      \"email\" : \"email\",\n      \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"username\" : \"username\"\n    },\n    \"genre\" : [ {\n      \"name\" : \"name\"\n    }, {\n      \"name\" : \"name\"\n    } ],\n    \"rating\" : 1.4658129,\n    \"id\" : 0,\n    \"title\" : \"title\",\n    \"releaseYear\" : 6,\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\"\n  }, {\n    \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n    \"author\" : \"author\",\n    \"addedBy\" : {\n      \"password\" : \"password\",\n      \"userRole\" : \"admin\",\n      \"email\" : \"email\",\n      \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"username\" : \"username\"\n    },\n    \"genre\" : [ {\n      \"name\" : \"name\"\n    }, {\n      \"name\" : \"name\"\n    } ],\n    \"rating\" : 1.4658129,\n    \"id\" : 0,\n    \"title\" : \"title\",\n    \"releaseYear\" : 6,\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\"\n  } ]\n}", PublicationList.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PublicationList>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PublicationList>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteGenre(@Parameter(in = ParameterIn.PATH, description = "Name of the genre to delete", required=true, schema=@Schema()) @PathVariable("genreName") String genreName,@Parameter(in = ParameterIn.COOKIE, description = "User info to check genre deletion rights" ,schema=@Schema()) @CookieValue(value="user", required=false) User user) {
        String accept = request.getHeader("Accept");
        List<Publication> tempList = new ArrayList<Publication>();
        Genre tempG = null;

        for(Genre g: Genre.genres){
            if(g.getName().equals(genreName)){
                tempG = g;
                Genre.genres.remove(g);
                break;
            }
        }
        if(tempG == null){
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

        for(Publication p:Publication.publications){
            List<Genre> newGenreList = new ArrayList<Genre>();
            for(Genre g: p.getGenre()){
                if(!tempG.getName().equals(g.getName())){
                    newGenreList.add(g);
                }
            }
            p.setGenre(newGenreList);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<Genre>> findAllGenres() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Genre>>(objectMapper.readValue("[ {\n  \"name\" : \"name\"\n}, {\n  \"name\" : \"name\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Genre>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Genre>>(Genre.genres,HttpStatus.OK);
    }

    public ResponseEntity<Void> updateGenre(@Parameter(in = ParameterIn.PATH, description = "Name of the genre to update", required=true, schema=@Schema()) @PathVariable("genreName") String genreName,@Parameter(in = ParameterIn.COOKIE, description = "User info to check modification rights" ,required=true,schema=@Schema()) @CookieValue(value="user", required=true) User user,@Parameter(in = ParameterIn.DEFAULT, description = "Genre object that needs to be updated", required=true, schema=@Schema()) @Valid @RequestBody Genre body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}

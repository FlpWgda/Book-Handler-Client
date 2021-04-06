package io.swagger;

import io.swagger.model.Genre;
import io.swagger.model.Publication;
import io.swagger.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.threeten.bp.OffsetDateTime;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableOpenApi
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" , "io.swagger.configuration"})
public class Swagger2SpringBoot implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        Genre.genres.add(new Genre("Poems"));
        Genre.genres.add(new Genre("Science-fiction"));
        Genre.genres.add(new Genre("Biography"));
        Genre.genres.add(new Genre("Crime novel"));
        Genre.genres.add(new Genre("Novel"));
        Genre.genres.add(new Genre("Historical"));
        Genre.genres.add(new Genre("Novel of manners"));
        User.users.add(new User("filip","filip@student.pwr.edu.pl","f1",User.UserRoleEnum.MEMBER, OffsetDateTime.now()));
        List<Genre> g1 = new ArrayList<Genre>();
        g1.add(Genre.genres.get(4));
        g1.add(Genre.genres.get(5));
        List<Genre> g2 = new ArrayList<Genre>();
        g2.add(Genre.genres.get(4));
        g2.add(Genre.genres.get(6));
        Publication.publications.add(new Publication((long) 0,"Krzyżacy","Henryk Sienkiewicz",1900, g1,OffsetDateTime.now()));
        Publication.publications.add(new Publication((long) 1,"Lalka","Bolesław Prus",1890, g2,OffsetDateTime.now()));


        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}

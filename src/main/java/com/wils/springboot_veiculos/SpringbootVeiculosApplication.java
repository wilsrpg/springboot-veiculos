package com.wils.springboot_veiculos;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootVeiculosApplication {
//public class SpringbootAlmoxarifadoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootVeiculosApplication.class, args);
	}

  //@Autowired
  ////private RepositorioDeCategoria repo;
  ////private RepositorioDeGrupo repo;
  ////private RepositorioDeItem repo;
  //private RepositorioDeMovimentacao repo;

  //@Override
  //public void run(String... args) throws Exception {
  //  //System.out.println("Categorias:");
  //  //for (Categoria categoria : repo.findAll()) {
  //  //  System.out.println(categoria);
  //  //}
  //  System.out.println();
  //  //String busca = "itens";
  //  //System.out.println("Categorias encontradas por findAllByNome(\""+busca+"\"):");
  //  System.out.println("Categorias encontradas por findAll:");
  //  //System.out.println("Categoria encontrada por findByNome(\""+busca+"\"):");
  //  System.out.println();
  //  //Optional<Categoria> achouCategoria = repo.findByNome(busca);
  //  //if (achouCategoria.isEmpty())
  //  //  System.out.println("Categoria não encontrada.");
  //  //else
  //  //  System.out.println(achouCategoria.get().obterNome());
  //  //System.out.println(repo.findByAnotacoes(busca).stream().map(c->c.obterNome()).collect(Collectors.joining(", ")));
  //  //System.out.println(repo.findAll().toString());
  //  System.out.println(repo.findAll().stream().map(c->c.obterNome()).collect(Collectors.joining(", ")));
  //  System.out.println();
  //  System.out.println("fim");
  //}
}

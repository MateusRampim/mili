package br.com.mili;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import br.com.mili.model.Produto;
import br.com.mili.model.ProdutoFisico;
import br.com.mili.model.ProdutoDigital;
import br.com.mili.repository.ProdutoRepository;

@SpringBootApplication
public class MiliApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiliApplication.class, args);
	}

}

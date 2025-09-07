package com.processador.processador_de_pedidos;

import com.processador.ProcessadorDePedidosApplication;
import org.springframework.boot.SpringApplication;

public class TestProcessadorDePedidosApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProcessadorDePedidosApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

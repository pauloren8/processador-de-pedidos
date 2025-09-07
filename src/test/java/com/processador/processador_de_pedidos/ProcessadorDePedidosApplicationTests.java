package com.processador.processador_de_pedidos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ProcessadorDePedidosApplicationTests {

	@Test
	void contextLoads() {
	}

}

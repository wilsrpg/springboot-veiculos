package com.wils.springboot_veiculos.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.awspring.cloud.sqs.operations.SqsTemplate;

@Service
public class ServicoDeFila {
	@Autowired
	private SqsTemplate sqsTemplate;
	
	private final String fila = "cadastro-de-veiculo";

	public void sendMessage(String message) {
		sqsTemplate.send(sqsSendOptions -> sqsSendOptions.queue(fila).payload(message));
		System.out.println("Mensagem enviada à fila '"+fila+"': "+message);
	}
}
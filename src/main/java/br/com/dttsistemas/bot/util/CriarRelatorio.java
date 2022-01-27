package br.com.dttsistemas.bot.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

public class CriarRelatorio {

	public static void relatorioImgAusentes(List<String> imgNaoListadas, String caminhoLocal) {

		try {

			FileWriter arq = new FileWriter(caminhoLocal + "Imagens nao enviada.txt");
			PrintWriter gravarArq = new PrintWriter(arq);

			gravarArq.printf("Imagens nao enviadas%n");

			imgNaoListadas.forEach(item -> gravarArq.printf(item + "%n"));

			arq.close();

			System.out.println("Relatorio de Imagens nao enviadas gerado");

		} catch (Exception e) {
			Logger.getLogger("error", e.getMessage());
		}

	}
	
}

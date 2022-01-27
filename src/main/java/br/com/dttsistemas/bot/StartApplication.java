package br.com.dttsistemas.bot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.dttsistemas.bot.aws.S3;
import br.com.dttsistemas.bot.modelo.TratamentoImagem;
import br.com.dttsistemas.bot.util.CriarRelatorio;

public class StartApplication {

	private static Integer cursor = 0;
	private static Integer total = 0;
	private static Integer idAtual = 0;

	private static S3 s3 = new S3();

	private static List<String> imgNaoListadas = new ArrayList<String>();

	public static void main(String[] args) {

		System.out.println("Iniciando Bot...");
		System.out.println("Construindo Conexão com banco de dados");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Connection");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		//altere o select para a tabela de sua preferencias
		total = Integer.parseInt(em.createQuery("select count(t) from TratamentoImagem t where awsSend = 0", Long.class).getSingleResult().toString());

		while (cursor < total) {
				
				//altere o select para a tabela de sua preferencias
				TratamentoImagem item = em.createQuery("from TratamentoImagem where awsSend = 0 order by idTratamento_imagem",TratamentoImagem.class)
						.setFirstResult(idAtual)
						.setMaxResults(1).getSingleResult();


				if (findFilesInFolder(item.getNomeImagem())) {

					try {
						s3.uploadImg(item.getNomeImagem(), getcaminhoLocal());
						
						item.setAwsSend(true); // altera o status da imagem no BD

						et.begin();
						em.merge(item);
						em.flush();
						et.commit();
						
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			

			idAtual++;

			console(item.getNomeImagem());

		}

		CriarRelatorio.relatorioImgAusentes(imgNaoListadas, getcaminhoLocal());

		System.out.println("Fim");

	}

	public static void console(String img) {

		try {

			String anim = "|/-\\";
			String data = "\r" + anim.charAt(idAtual % anim.length()) + " - " + "Total: " + idAtual
					+ " de " + total + " - " + img;

			System.out.write(data.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean findFilesInFolder(String nomeArq) {

		File file = new File(getcaminhoLocal() + nomeArq);

		// verifica se o IMG existe
		if (file.exists() && !file.isDirectory()) {
			System.out.println(nomeArq);

			return true;
		} else {
			imgNaoListadas.add(nomeArq);
			return false;
		}

	}

	public static String getcaminhoLocal() {

		String caminho = null;

		try {
			caminho = new File(".").getCanonicalPath() + "\\";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return caminho;
	}

}

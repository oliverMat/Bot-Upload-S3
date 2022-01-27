package br.com.dttsistemas.bot.aws;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Logger;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;


public class S3 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Informe a baixo os dados de acesso do seu bucket 
	
	private String bucketImgOriginal = "NOME-BUCKET";
	
	private String region = "REGIAO";

	private String key = "CHAVE";

	private String secret = "SEGREDO";


	private AmazonS3 amazonS3;

	
	public void uploadImg(String nomeCompleto, String caminhoLocal) throws Exception {// Envia a Imagem com o tamanho e resolucao original
		
		
		File caminhoRaiz =  new File(caminhoLocal + nomeCompleto);

		autheticationS3();
		
		amazonS3.putObject(bucketImgOriginal, nomeCompleto, caminhoRaiz);
	}

	public void deleteImg(String nomeImg) {//Deleta a imagem no bucket
		try {
			autheticationS3();
			
			amazonS3.deleteObject(bucketImgOriginal, nomeImg);
			
		} catch (AmazonServiceException e) {
			Logger.getLogger(e.getErrorMessage());
		}
	}
	
	public URL buscarImgOri(String nomeImg) {//Busca a imagem pelo nome e retorna uma URL pre-signed por vez 
		try {
			autheticationS3();
	
			// Generate the presigned URL.
			GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketImgOriginal	, nomeImg).withMethod(HttpMethod.GET).withExpiration(tempoExpiracao());
			URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
			
			return url;
		} catch (AmazonServiceException e) {
			Logger.getLogger(e.getErrorMessage());
			return null;
		}
	}
	
	public Date tempoExpiracao() {// Set the presigned URL to expire after one hour.
		Date expiration = new Date();
		long expTimeMillis = Instant.now().toEpochMilli();
		expTimeMillis += 1000 * 60 * 60;
		expiration.setTime(expTimeMillis);
		
		return expiration;
	}
	
	public void autheticationS3() {// Realiza a autenticaçao com a amazon antes de enviar as iamgens
		System.out.println("Autenticando S3...");
		amazonS3 = AmazonS3ClientBuilder.standard().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(key, secret))).build();
	}

}

package cn.clean;

import io.minio.MinioClient;

/**
 * @Author ji_ruixin
 * @Date 2023/6/15
 */
public class ClientUtil {

	public static MinioClient getClient(){
		MinioClient minioClient =
				MinioClient.builder()
						.endpoint("http://192.168.64.50:9003")
						.credentials("root", "cvicse@2022")
						.build();
		return minioClient;
	}

	public static String getBucket(){
		return "mldataset";
	}

}

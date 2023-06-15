package cn.clean.file;

import cn.clean.ClientUtil;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author ji_ruixin
 * @Date 2023/6/15
 */
public class GetInfoDemo {
	/**
	 *  1.路径前面不能带桶
	 *  2.路径开始带不带 “/”都可，但是路径结尾不能带 “/”，而且路径必须对应文件
	 *
	 */
	private static String path = "ml/dataset/dataset-1669258216733958145/dataset/mnist";
	public static void main(String[] args) throws Exception{
		getPath();
	}

	public static void getPath() throws Exception{
		MinioClient client = ClientUtil.getClient();
		StatObjectResponse stat =
				client.statObject(
						StatObjectArgs.builder()
								.bucket(ClientUtil.getBucket())
								.object(path)
								.build());
		System.out.println(stat.size());
	}
}

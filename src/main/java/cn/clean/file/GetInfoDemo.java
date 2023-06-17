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
	 *  测试获取minio文件的大小
	 *  路径必须对应文件，如果路径对应的文件夹将会报错
	 */
	private static String path = "/ml/dataset/dataset-1669307399327248387/dataset/mnist/train_0.png";
	public static void main(String[] args) throws Exception{
		getPath();
	}

	/**
	 *  1.路径前面不能带桶
	 *  2.路径开始带不带 “/”都可，但是路径结尾不能带 “/”，而且路径必须对应文件
	 *
	 */
	public static void getPath() throws Exception{
		MinioClient client = ClientUtil.getClient();
		StatObjectResponse stat =
				client.statObject(
						StatObjectArgs.builder()
								.bucket(ClientUtil.getBucket())
								.object(path)
								.build());
		System.out.println(stat.size());
		System.out.println(stat.contentType());
		System.out.println(stat.etag());
	}
}

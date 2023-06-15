package cn.clean.file;

import cn.clean.ClientUtil;
import io.minio.*;
import io.minio.messages.Item;

/**
 * @Author ji_ruixin
 * @Date 2023/6/15
 */
public class GetSubFileDemo {
	/**
	 *  1.路径前面带桶不报错，只是不到该路径下的路径
	 *  2.路径开始和结尾带不带 “/”都可以
	 */
	private static String path = "/ml/dataset/dataset-1669258216733958145/dataset/";

	public static void main(String[] args) throws Exception{
		getSubFile();
	}

	public static void getSubFile() throws Exception{
		MinioClient client = ClientUtil.getClient();
		Iterable<Result<Item>> results =
				client.listObjects(
						ListObjectsArgs.builder()
								.bucket(ClientUtil.getBucket())
								.prefix(path)
								.recursive(true)
								.build());

		for (Result<Item> result : results) {
			Item item = result.get();
			System.out.println(item.lastModified() + "\t" + item.size() + "\t" + item.objectName());
		}
	}
}

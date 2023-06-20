package cn.clean.file;

import cn.clean.ClientUtil;
import io.minio.*;
import io.minio.messages.Item;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author ji_ruixin
 * @Date 2023/6/15
 */
public class GetSubFileDemo {

	/**
	 *
	 *  测试获取某个路径下的所有文件
	 */
	private static String path = "/ml/dataset/dataset-1670714178216923138/";

	public static void main(String[] args) throws Exception{
		getSubFile();
	}

	/**
	 *  1.路径前面带桶不报错，只是不到该路径下的文件
	 *  2.路径开始和结尾带不带 “/”都可以
	 *  3.递归获取只能获取到文件
	 */
	public static List<String> getSubFile() throws Exception{
		List<String> strings = new LinkedList<>();
		MinioClient client = ClientUtil.getClient();
		Iterable<Result<Item>> results =
				client.listObjects(
						ListObjectsArgs.builder()
								.bucket(ClientUtil.getBucket())
								.prefix(path)
								// 是否递归获取
								.recursive(true)
								.build());

		for (Result<Item> result : results) {
			Item item = result.get();
			strings.add(item.objectName());
			// System.out.println(item.objectName());
			// System.out.println(item.lastModified() + "\t" + item.size() + "\t" + item.objectName());
		}
		return strings;

	}
}

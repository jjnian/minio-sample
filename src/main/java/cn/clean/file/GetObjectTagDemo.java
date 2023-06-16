package cn.clean.file;

import cn.clean.ClientUtil;
import io.minio.GetObjectTagsArgs;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import io.minio.messages.Tags;

/**
 * @Author ji_ruixin
 * @Date 2023/6/15
 */
public class GetObjectTagDemo {
	/**
	 *
	 * 测试路径的Tag
	 */
	private static String path = "ml/dataset/dataset-1669258216733958145/dataset/mnist/train_14.png";

	public static void main(String[] args) throws Exception{
		getObejctTag();
	}

	/**
	 *  1.路径前面带桶报错
	 *  2.路径开始可以不带 “/”
	 *  3.路径必须对应文件
	 */
	public static void getObejctTag() throws Exception{
		MinioClient client = ClientUtil.getClient();
		Tags tags =
				client.getObjectTags(
						GetObjectTagsArgs.builder()
								.bucket(ClientUtil.getBucket())
								.object(path)
								.build());
		System.out.println("Object tags: " + tags.get());
	}
}

package cn.clean.file;

import cn.clean.ClientUtil;
import io.minio.*;

import java.util.HashMap;

/**
 * @Author ji_ruixin
 * @Date 2023/6/16
 */
public class CopyFileDemo {
	/**
	 *
	 *  测试复制文件Api
	 */
	private static String path = "/ml/dataset/dataset-1669581898647949314/dataset/mnist";
	private static String copyPath = "/ml/dataset/dataset-1669581898647949314/dataset/copy";
	public static void main(String[] args) throws Exception{
		//copyFile();
		HashMap<String, Object> stringObjectHashMap = new HashMap<>();
		Object ji = stringObjectHashMap.get("ji");
		System.out.println(ji == null);
	}

	/**
	 *  1.路径前面不能带桶
	 *  2.路径开始带不带 “/”都可，但是路径结尾不能带 “/”，
	 *  3.minio不能复制文件夹，因此原路径必须指向文件，复制的路径结尾不能带 “/”
	 *
	 */
	public static void copyFile() throws Exception{
		MinioClient client = ClientUtil.getClient();
		client.copyObject(
				CopyObjectArgs.builder()
						.bucket(ClientUtil.getBucket())
						.object(copyPath)
						.source(
								CopySource.builder()
										.bucket(ClientUtil.getBucket())
										.object(path)
										.build())
						.build());
	}
}

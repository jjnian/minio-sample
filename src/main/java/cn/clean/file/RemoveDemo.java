package cn.clean.file;

import cn.clean.ClientUtil;
import io.minio.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class RemoveDemo {
	/**
	 *
	 *  测试获取某个路径下的所有文件
	 */
	private static String path = "/ml/dataset/dataset-1670714178216923138/";

	public static void main(String[] args) throws Exception{
		//removeObjectOne();
		removeManyObject();
	}

	/**
	 * 删除一个
	 */
	public static void removeObjectOne() throws Exception{
		MinioClient client = ClientUtil.getClient();
		client.removeObject(
				RemoveObjectArgs.builder()
						.bucket(ClientUtil.getBucket())
						.object(path)
						.build());
	}

	/**
	 * 删除多个
	 * @Date 2023/6/19 17:24
	 */
	public static void removeManyObject() throws Exception{
		MinioClient client = ClientUtil.getClient();
		List<String> subFile = GetSubFileDemo.getSubFile();
		List<DeleteObject> objects = new LinkedList<>();
		for(String s : subFile){
			objects.add(new DeleteObject(s));
		}
		Iterable<Result<DeleteError>> results =
				client.removeObjects(
						RemoveObjectsArgs.builder().bucket(ClientUtil.getBucket()).objects(objects).build());
		for (Result<DeleteError> result : results) {
			DeleteError error = result.get();
			System.out.println(
					"Error in deleting object " + error.objectName() + "; " + error.message());
		}
	}


}

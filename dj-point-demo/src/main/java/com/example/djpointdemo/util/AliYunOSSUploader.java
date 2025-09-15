package com.example.djpointdemo.util;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.example.djpointdemo.dto.CredentialsInfo;
import java.io.File;

/**
 * 上传航线文件到司空sync存储桶
 * @author qzz
 * @date 2025/8/27
 */
public class AliYunOSSUploader {

    public static void main(String[] args) {

        String securityToken = "CAISigV1q6Ft5B2yfSjIr5mACo6FjpF72vHYZ0PLj0UeQf4fqvLsujz2IH9JdHNgB+AasPozlGxT7Poblrt+UMQcHQnKbM99q49L9hmobIeZIwZhDFtf2vOfAmG2J0PRTKWwCryL+Lq/F96pb1fb7FgRpZLxaTSlWXG8LJSNkuQJR98LXw6+H1UkadBNPVkg0hZzVx3rOO2qLwThj0fJEUNsoXAcs25k7rmlycDuFnO8dTTzwfRHoJ/qcNr2LZtsLtJxUtO4mfdqcbDI3yMV8wJN+OAqhfJJpDuX4ZTDXQEOpknWY+GRooI3IUp1a/NrEKIa8vOtnKE/5bfYnd2vwB8INOpbWWGDTtj7hZqURb/sOIdoK7r2YHnAidrWccb56Fl9MS9KcVpEdYVkdnJ7WVkmFTiGb6H39V2WOw6oFfbdgeZ0o2EcrTjB+d6NOCLOIdGw2joZPZkRdl4hMwVss0Xqbq4BdX41ETRgHK3HaYRocDdUq67joRaoDE8G9HxMuODkbP77o70WbZ6FPqhLyo0Afp9LwRVIM138UOCpkVxGNj4nE7NHyKnqJZikkvznot6Pe+/ABdQGuVggEzfKtHi1U35eLD2Dn79EaVOe9NbRy7GLqcs5TUxsxKgGXV7cIYo18ww/uPfrtErPyoK5DCD2pFhf08LD4oxC5XFjevqZmOKItlbcxifPPPljyJmHBDMxHUTrICYlmernnW4c4hAT1m+1Pxta8EiWz2+/cYgDyPuHg2lDBqpDRVNttIIFPw6KYPD+ANZjMYsKFIgm2WakWZSWkPauKZHkDmltZYung3rONtpALccBSCnog+/OJvpNovQZZGSlQoUXx+UTuV/5llpE0a1nu4TaWSIwbBqAATPtTo21HRttkFudEyccSxYQBtl2SfjwlsD5Byr+w3s4zjOFHjlRp7lD5tfjRdb8kjVobyMotsQtUsaF+KqnU8Sy36aADSe8Tb5an0t30IaAs2AvTh2MBs6Q/Yi8/mmiZVaUcjiOn2vhUXTWjgK0AnEHEWaoRHA/fyHmH40uzrH9IAA=";

        CredentialsInfo credentialsInfo = new CredentialsInfo();
        credentialsInfo.setEndpoint("https://oss-cn-hangzhou.aliyuncs.com");
        credentialsInfo.setBucket("hz-file-storage-prod");
        credentialsInfo.setObjectKeyPrefix("4a6e5d87-6607-488c-870e-de39cd66c9a4/73cb10ca-acfb-4b03-9854-f996de3ee3b1/7d321859-1ff7-4ec8-b7a4-f8ee41ac6609");
        credentialsInfo.setAccessKeyId("STS.NZ5A41cNZm33eezkEKMq3E7GX");
        credentialsInfo.setAccessKeySecret("GkjuvstT5Bc4wiL1HEgpKeP7fTEcaBYh12UVtUgBD4XE");
        credentialsInfo.setExpire(43200L);
        credentialsInfo.setSecurityToken(securityToken);

        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
//        String filePath= "C:\\Users\\Admin\\Downloads\\test.kmz";
        String filePath= "file\\kmz\\1756285812774.kmz";
        uploadFileToDji(filePath, credentialsInfo);

    }

    /**
     * 上传文件
     * @param credentialsInfo 解析的临时凭证信息
     */
    public static void uploadFileToDji(String filePath, CredentialsInfo credentialsInfo) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = credentialsInfo.getEndpoint();
        // STS临时凭证
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(credentialsInfo.getAccessKeyId(), credentialsInfo.getAccessKeySecret(), credentialsInfo.getSecurityToken());
        // 填写Bucket名称，例如examplebucket。
        String bucketName = credentialsInfo.getBucket();
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = credentialsInfo.getObjectKeyPrefix() + "/" + System.currentTimeMillis() + ".kmz";
        // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
        String region = "cn-hangzhou";

        // 创建OSSClient实例。
        // 当OSSClient实例不再使用时，调用shutdown方法以释放资源。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();

        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传文件。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // 生成并返回上传文件后的访问地址
            String fileUrl = "https://" + bucketName + "." + endpoint.replace("https://", "") + "/" + objectName;
            System.out.println("上传成功，文件地址：" + fileUrl);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
package com.fsd07team3.CourseRegistrationSystem.aws;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class AmazonS3ClientBuilder {
    @Autowired
    private AmazonS3 amazonS3Client;
    @Value("${csyawsbucket02}")
    private String bucketName;

    public String getObjectUrl(String key) {
        return amazonS3Client.getUrl(bucketName, key).toExternalForm();
    }
}

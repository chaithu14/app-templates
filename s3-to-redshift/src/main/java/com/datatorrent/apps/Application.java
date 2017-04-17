/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.datatorrent.apps;

import javax.validation.constraints.Min;

import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;

import org.apache.apex.malhar.lib.db.redshift.RedshiftJdbcTransactionableOutputOperator;
import org.apache.hadoop.conf.Configuration;

import static com.datatorrent.api.Context.OperatorContext.TIMEOUT_WINDOW_COUNT;

@ApplicationAnnotation(name="S3-to-redshift")
public class Application implements StreamingApplication
{
  @Min(120)
  private int timeOutWindowCount = 6000;

  public void setS3FilesToInput(S3InputOperator inputOperator, Configuration conf)
  {
    String Schema = "s3n";
    String accessKey = conf.get("apex.app-param.accessKeyForS3Input");
    String secretKey = conf.get("apex.app-param.secretKeyForS3Input");
    String bucketName = conf.get("apex.app-param.bucketNameForS3Input");
    String files = conf.get("apex.app-param.filesForScanning");
    String inputURI = Schema + "://" + accessKey + ":" + secretKey + "@" + bucketName + "/";
    String uriFiles = "";
    String[] inputFiles = files.split(",");
    for (int i = 0; i < inputFiles.length; i++) {
      uriFiles += inputURI + inputFiles[i];
      if (i != inputFiles.length - 1) {
        uriFiles += ",";
      }
    }
    inputOperator.getScanner().setFiles(uriFiles);
  }

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    // Add S3 as input and Redshift as output operators respectively to dag.
    S3InputOperator inputOperator = dag.addOperator("S3Input", new S3InputOperator());
    inputOperator.setBlocksThreshold(1);
    setS3FilesToInput(inputOperator, conf);
    RedshiftJdbcTransactionableOutputOperator outputOperator = dag.addOperator("RedshiftOutput", new RedshiftJdbcTransactionableOutputOperator());
    outputOperator.setReaderMode("READ_FROM_S3");
    // Get the S3 credentials from config and set it to the redshift operator
    if (conf.get("apex.app-param.accessKeyForS3Input") != null) {
      outputOperator.setAccessKey(conf.get("apex.app-param.accessKeyForS3Input"));
    }
    if (conf.get("apex.app-param.secretKeyForS3Input") != null) {
      outputOperator.setSecretKey(conf.get("apex.app-param.secretKeyForS3Input"));
    }
    if (conf.get("apex.app-param.bucketNameForS3Input") != null) {
      outputOperator.setBucketName(conf.get("apex.app-param.bucketNameForS3Input"));
    }
    if (conf.get("apex.app-param.regionForS3Input") != null) {
      outputOperator.setRegion(conf.get("apex.app-param.regionForS3Input"));
    }

    dag.setOperatorAttribute(inputOperator, TIMEOUT_WINDOW_COUNT, timeOutWindowCount);
    dag.setOperatorAttribute(outputOperator, TIMEOUT_WINDOW_COUNT, timeOutWindowCount);

    // Create a stream for messages from Kinesis to S3
    dag.addStream("S3ToRedshift", inputOperator.outputPort, outputOperator.input);
  }
}

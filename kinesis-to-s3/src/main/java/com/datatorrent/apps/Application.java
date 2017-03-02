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

import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;

import org.apache.apex.malhar.lib.fs.s3.S3TupleOutputModule;
import org.apache.hadoop.conf.Configuration;

@ApplicationAnnotation(name="kinesis-to-S3")
public class Application implements StreamingApplication
{
  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    // Add Kinesis as input and S3 as output operators respectively to dag.
    KinesisByteArrayInputOperator inputModule = dag.addOperator("KinesisInput", new KinesisByteArrayInputOperator());
    S3TupleOutputModule.S3BytesOutputModule outputModule = dag.addModule("S3OutputModule", new S3TupleOutputModule.S3BytesOutputModule());

    // Create a stream for messages from Kinesis to S3
    dag.addStream("KinesisToS3", inputModule.outputPort, outputModule.input);
  }
}

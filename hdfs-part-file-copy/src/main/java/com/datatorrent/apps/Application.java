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

import com.datatorrent.api.Context;
import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.lib.io.fs.FSInputModule;

import org.apache.apex.malhar.lib.io.block.PartFileWriter;
import org.apache.hadoop.conf.Configuration;

@ApplicationAnnotation(name="Hdfs-part-file-copy-App")
public class Application implements StreamingApplication
{
  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    // Add FSInputModule as input and PartFileWriter as output operators to dag.
    FSInputModule input = dag.addModule("HDFSInputModule", new FSInputModule());
    PartFileWriter output = dag.addOperator("PartFileCopy", new PartFileWriter());

    dag.setInputPortAttribute(output.input, Context.PortContext.PARTITION_PARALLEL, true);
    dag.setInputPortAttribute(output.blockMetadataInput, Context.PortContext.PARTITION_PARALLEL, true);

    // Create a stream for blockData, fileMetadata, blockMetadata from Input to PartFileWriter
    dag.addStream("BlocksMetaData", input.blocksMetadataOutput, output.blockMetadataInput).setLocality(DAG.Locality.CONTAINER_LOCAL);
    dag.addStream("BlocksData", input.messages, output.input).setLocality(DAG.Locality.CONTAINER_LOCAL);
    dag.addStream("FileMetaData", input.filesMetadataOutput, output.fileMetadataInput);
  }
}

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

import java.io.IOException;
import java.util.LinkedList;

import org.apache.apex.malhar.lib.fs.FSRecordCompactionOperator;
import org.apache.hadoop.fs.Path;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.annotation.OperatorAnnotation;
import com.datatorrent.lib.io.fs.FileSplitterInput;

/**
 * Input operator that scans a directory for S3 files and emits the file metadata.
 */
@OperatorAnnotation(checkpointableWithinAppWindow = false, partitionable = false)
public class S3InputOperator extends FileSplitterInput
{
  public transient DefaultOutputPort<FSRecordCompactionOperator.OutputMetaData> outputPort = new DefaultOutputPort<>();

  @Override
  protected void process()
  {
    FileInfo fileInfo;
    while ((fileInfo = getFileInfo()) != null) {
      if (!processFileInfo(fileInfo)) {
        break;
      }
    }
  }

  // Functionality of this operator is to scan the files/directories and emit the file metadata.
  @Override
  protected boolean emitBlockMetadata()
  {
    return true;
  }

  @Override
  protected void replay(long windowId)
  {
    try {
      @SuppressWarnings("unchecked")
      LinkedList<ScannedFileInfo> recoveredData = (LinkedList<ScannedFileInfo>)getWindowDataManager().retrieve(windowId);
      if (recoveredData == null) {
        //This could happen when there are multiple physical instances and one of them is ahead in processing windows.
        return;
      }
      for (ScannedFileInfo info : recoveredData) {
        String path = info.getRelativeFilePath();
        if (path.charAt(0) == Path.SEPARATOR.charAt(0)) {
          path = path.substring(1);
        }

        FSRecordCompactionOperator.OutputMetaData metaData = new FSRecordCompactionOperator.OutputMetaData(path, path,0);
        outputPort.emit(metaData);
      }
    } catch (IOException e) {
      throw new RuntimeException("replay", e);
    }
    super.replay(windowId);
  }

  /**
   * Emit the file metada of type OutputMetaData
   * @param fileInfo file to process
   * @return true
   */
  @Override
  protected boolean processFileInfo(FileInfo fileInfo)
  {
    ScannedFileInfo scannedFileInfo = (ScannedFileInfo)fileInfo;
    currentWindowRecoveryState.add(scannedFileInfo);
    updateReferenceTimes(scannedFileInfo);
    String path = fileInfo.getRelativeFilePath();
    if (path.charAt(0) == Path.SEPARATOR.charAt(0)) {
      path = path.substring(1);
    }
    FSRecordCompactionOperator.OutputMetaData metaData = new FSRecordCompactionOperator.OutputMetaData(path, path,0);
    outputPort.emit(metaData);
    return true;
  }
}

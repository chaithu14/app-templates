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

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.lib.converter.Converter;

/**
 * This operator converts String to Byte array.
 *
 * @category Tuple Converters
 * @tags byte, string
 */
public class StringToByteConverter implements Converter<String,byte[]>
{
  /**
   * Input port which accepts String.
   */
  public final transient DefaultInputPort<String> input = new DefaultInputPort<String>()
  {

    @Override
    public void process(String s)
    {
      output.emit(convert(s));
    }
  };

  /**
   * Output port which outputs byte array converted from String.
   */
  public final transient DefaultOutputPort<byte[]> output = new DefaultOutputPort<byte[]>();

  /**
   * Convert String to byte array
   * @param tuple tuple of certain format
   * @return byte array
   */
  @Override
  public byte[] convert(String tuple)
  {
    return tuple.getBytes();
  }
}

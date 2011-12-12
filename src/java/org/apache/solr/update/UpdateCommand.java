/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.solr.update;


/** An index update command encapsulated in an object (Command pattern)
 *
 * @version $Id: UpdateCommand.java 565144 2007-08-12 20:47:42Z ryan $
 */
  public class UpdateCommand {
    protected String commandName;
    protected boolean updateStore = false;
    
    public UpdateCommand(String commandName) {
      this.commandName = commandName;
    }

    public String toString() {
      return commandName;
    }
    
    public void setUpdateStore(boolean updateStore){
    	this.updateStore = updateStore;
    }
    
    public boolean getUpdateStore(){
    	return this.updateStore;
    }
  }

/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.gwtproject.event.dom.client;

import org.gwtproject.event.dom.client.HasDragEndHandlers;
import org.gwtproject.event.dom.client.HasDragEnterHandlers;
import org.gwtproject.event.dom.client.HasDragHandlers;
import org.gwtproject.event.dom.client.HasDragLeaveHandlers;
import org.gwtproject.event.dom.client.HasDragOverHandlers;
import org.gwtproject.event.dom.client.HasDragStartHandlers;
import org.gwtproject.event.dom.client.HasDropHandlers;

/**
 * This is a convenience interface that includes all drag and drop handlers
 * defined by the core GWT system.
 * 
 * <p>
 * <span style="color:red">Experimental API: This API is still under development
 * and is subject to change.
 * </span>
 * </p>
 */
public interface HasAllDragAndDropHandlers extends HasDragEndHandlers, HasDragEnterHandlers,
        HasDragLeaveHandlers, HasDragHandlers, HasDragOverHandlers, HasDragStartHandlers,
        HasDropHandlers {
}
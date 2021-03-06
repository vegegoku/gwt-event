/*
 * Copyright 2010 Google Inc.
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

import org.gwtproject.event.dom.client.HasGestureChangeHandlers;
import org.gwtproject.event.dom.client.HasGestureEndHandlers;
import org.gwtproject.event.dom.client.HasGestureStartHandlers;

/**
 * This is a convenience interface that includes all gesture handlers defined by
 * the core GWT system.
 * <p>
 * WARNING, PLEASE READ: As this interface is intended for developers who wish
 * to handle all gesture events in GWT, new gesture event handlers will be added
 * to it. Therefore, updates can cause breaking API changes.
 * </p>
 */
public interface HasAllGestureHandlers extends HasGestureStartHandlers,
        HasGestureChangeHandlers, HasGestureEndHandlers {
}
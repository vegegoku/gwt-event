/*
 * Copyright 2008 Google Inc.
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

import org.gwtproject.event.dom.client.MouseWheelEvent;
import org.gwtproject.event.dom.client.MouseWheelHandler;
import org.gwtproject.event.shared.HandlerRegistration;
import org.gwtproject.event.shared.HasHandlers;

/**
 * A widget that implements this interface provides registration for
 * {@link MouseWheelHandler} instances.
 */
public interface HasMouseWheelHandlers extends HasHandlers {
  /**
   * Adds a {@link MouseWheelEvent} handler.
   * 
   * @param handler the mouse wheel handler
   * @return {@link HandlerRegistration} used to remove this handler
   */
  HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler);
}

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

import com.google.gwt.dom.client.BrowserEvents;
import org.gwtproject.event.dom.client.DomEvent;
import org.gwtproject.event.dom.client.DragDropEventBase;
import org.gwtproject.event.dom.client.DragHandler;

/**
 * Represents a native drag event.
 */
public class DragEvent extends DragDropEventBase<DragHandler> {

  /**
   * Event type for drag events. Represents the meta-data associated with this
   * event.
   */
  private static final Type<DragHandler> TYPE = new Type<DragHandler>(BrowserEvents.DRAG, new org.gwtproject.event.dom.client.DragEvent());

  /**
   * Gets the event type associated with drag events.
   * 
   * @return the handler type
   */
  public static Type<DragHandler> getType() {
    return TYPE;
  }

  /**
   * Protected constructor, use
   * {@link DomEvent#fireNativeEvent(com.google.gwt.dom.client.NativeEvent, org.gwtproject.event.shared.HasHandlers)}
   * or
   * {@link DomEvent#fireNativeEvent(com.google.gwt.dom.client.NativeEvent, org.gwtproject.event.shared.HasHandlers, com.google.gwt.dom.client.Element)}
   * to fire drag events.
   */
  protected DragEvent() {
  }

  @Override
  public final Type<DragHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DragHandler handler) {
    handler.onDrag(this);
  }
}

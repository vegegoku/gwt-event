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

import com.google.gwt.dom.client.BrowserEvents;
import org.gwtproject.event.dom.client.DomEvent;
import org.gwtproject.event.dom.client.TouchEndHandler;
import org.gwtproject.event.dom.client.TouchEvent;

/**
 * Represents a native touch end event.
 */
public class TouchEndEvent extends TouchEvent<TouchEndHandler> {

  /**
   * Event type for touch end events. Represents the meta-data associated with
   * this event.
   */
  private static final Type<TouchEndHandler> TYPE = new Type<TouchEndHandler>(
      BrowserEvents.TOUCHEND, new org.gwtproject.event.dom.client.TouchEndEvent());

  /**
   * Gets the event type associated with touch end events.
   *
   * @return the handler type
   */
  public static Type<TouchEndHandler> getType() {
    return TYPE;
  }

  /**
   * Protected constructor, use {@link
   * DomEvent#fireNativeEvent(com.google.gwt.dom.client.NativeEvent,
   * org.gwtproject.event.shared.HasHandlers)} to fire touch end events.
   */
  protected TouchEndEvent() {
  }

  @Override
  public final Type<TouchEndHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(TouchEndHandler handler) {
    handler.onTouchEnd(this);
  }
}
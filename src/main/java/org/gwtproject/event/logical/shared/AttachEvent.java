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

package org.gwtproject.event.logical.shared;

import org.gwtproject.event.logical.shared.HasAttachHandlers;
import org.gwtproject.event.shared.EventHandler;
import org.gwtproject.event.shared.GwtEvent;

/**
 * Fired when the event source is attached to the browser's document
 * or detached from it.
 */
public class AttachEvent extends GwtEvent<org.gwtproject.event.logical.shared.AttachEvent.Handler> {

  /**
   * Implemented by objects that handle {@link org.gwtproject.event.logical.shared.AttachEvent}.
   */
  public interface Handler extends EventHandler {
    void onAttachOrDetach(org.gwtproject.event.logical.shared.AttachEvent event);
  }

  /**
   * The event type.
   */
  static Type<org.gwtproject.event.logical.shared.AttachEvent.Handler> TYPE;

  /**
   * Fires an {@link org.gwtproject.event.logical.shared.AttachEvent} on all registered handlers in the handler
   * source.
   *
   * @param <S> The handler source type
   * @param source the source of the handlers
   * @param attached whether to announce an attach or detach
   */
  public static <S extends HasAttachHandlers> void fire(S source,
                                                        boolean attached) {
    if (TYPE != null) {
      org.gwtproject.event.logical.shared.AttachEvent event = new org.gwtproject.event.logical.shared.AttachEvent(attached);
      source.fireEvent(event);
    }
  }

  /**
   * Ensures the existence of the handler hook and then returns it.
   *
   * @return returns a handler hook
   */
  public static Type<org.gwtproject.event.logical.shared.AttachEvent.Handler> getType() {
    if (TYPE == null) {
      TYPE = new Type<org.gwtproject.event.logical.shared.AttachEvent.Handler>();
    }
    return TYPE;
  }

  private final boolean attached;

  /**
   * Construct a new {@link org.gwtproject.event.logical.shared.AttachEvent}.
   *
   * @param attached true if the source has been attached
   */
  protected AttachEvent(boolean attached) {
    this.attached = attached;
  }

  @Override
  public final Type<org.gwtproject.event.logical.shared.AttachEvent.Handler> getAssociatedType() {
    return TYPE;
  }

  /**
   * Returns true if this event announces that the source has been attached,
   * false if it has been detached.
   */
  public boolean isAttached() {
    return attached;
  }

  @Override
  public String toDebugString() {
    assertLive();
    return super.toDebugString() + " attached = " + attached;
  }

  @Override
  protected void dispatch(org.gwtproject.event.logical.shared.AttachEvent.Handler handler) {
    handler.onAttachOrDetach(this);
  }
}

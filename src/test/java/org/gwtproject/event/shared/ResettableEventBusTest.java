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
package org.gwtproject.event.shared;

import org.gwtproject.event.dom.client.DomEvent.Type;
import org.gwtproject.event.dom.client.MouseDownEvent;
import org.gwtproject.event.dom.client.MouseDownHandler;
import org.gwtproject.event.shared.HandlerRegistration;
import org.gwtproject.event.shared.ResettableEventBus;
import org.gwtproject.event.shared.SimpleEventBus;
import org.gwtproject.event.shared.testing.CountingEventBus;

/**
 * Eponymous unit test.
 */
public class ResettableEventBusTest extends HandlerTestBase {
  public void testSimple() {
    CountingEventBus wrapped = new CountingEventBus();
    org.gwtproject.event.shared.ResettableEventBus subject = new org.gwtproject.event.shared.ResettableEventBus(wrapped);

    Type<MouseDownHandler> type = MouseDownEvent.getType();

    assertEquals(0, wrapped.getCount(type));

    subject.addHandler(type, mouse1);
    subject.addHandlerToSource(type, "baker", mouse2);
    subject.addHandler(type, mouse3);

    assertEquals(3, wrapped.getCount(type));

    subject.fireEvent(new MouseDownEvent() {
    });
    assertFired(mouse1, mouse3);
    assertNotFired(mouse2);

    reset();

    subject.fireEventFromSource(new MouseDownEvent() {
    }, "baker");
    assertFired(mouse1, mouse2, mouse3);

    reset();

    subject.removeHandlers();
    assertEquals(0, wrapped.getCount(type));

    subject.fireEvent(new MouseDownEvent() {
    });
    assertNotFired(mouse1, mouse2, mouse3);
  }

  public void testNestedResetInnerFirst() {
    CountingEventBus wrapped = new CountingEventBus();
    org.gwtproject.event.shared.ResettableEventBus wideScope = new org.gwtproject.event.shared.ResettableEventBus(wrapped);
    org.gwtproject.event.shared.ResettableEventBus narrowScope = new org.gwtproject.event.shared.ResettableEventBus(wideScope);

    Type<MouseDownHandler> type = MouseDownEvent.getType();

    wideScope.addHandler(type, mouse1);
    narrowScope.addHandler(type, mouse2);

    wrapped.fireEvent(new MouseDownEvent() {
    });
    assertFired(mouse1, mouse2);

    reset();

    /*
     * When I remove handlers from the narrow resettable, it should have no
     * effect on handlers registered with the wider instance.
     */

    narrowScope.removeHandlers();

    wrapped.fireEvent(new MouseDownEvent() {
    });
    assertFired(mouse1);
    assertNotFired(mouse2);
  }

  public void testNestedResetOuterFirst() {
    CountingEventBus wrapped = new CountingEventBus();
    org.gwtproject.event.shared.ResettableEventBus wideScope = new org.gwtproject.event.shared.ResettableEventBus(wrapped);
    org.gwtproject.event.shared.ResettableEventBus narrowScope = new org.gwtproject.event.shared.ResettableEventBus(wideScope);

    Type<MouseDownHandler> type = MouseDownEvent.getType();

    wideScope.addHandler(type, mouse1);
    narrowScope.addHandler(type, mouse2);

    wrapped.fireEvent(new MouseDownEvent() {
    });
    assertFired(mouse1, mouse2);

    reset();

    /*
     * When I remove handlers from the first resettable, handlers registered by
     * the narrower scoped one that wraps it should also be severed.
     */

    wideScope.removeHandlers();

    wrapped.fireEvent(new MouseDownEvent() {
    });
    assertNotFired(mouse1);
    assertNotFired(mouse2);
  }

  public void testManualRemoveMemory() {
    org.gwtproject.event.shared.SimpleEventBus eventBus = new org.gwtproject.event.shared.SimpleEventBus();
    org.gwtproject.event.shared.ResettableEventBus subject = new org.gwtproject.event.shared.ResettableEventBus(eventBus);

    Type<MouseDownHandler> type = MouseDownEvent.getType();

    org.gwtproject.event.shared.HandlerRegistration registration1 = subject.addHandler(type, mouse1);
    org.gwtproject.event.shared.HandlerRegistration registration2 = subject.addHandler(type, mouse2);
    HandlerRegistration registration3 = subject.addHandler(type, mouse3);

    registration1.removeHandler();
    registration2.removeHandler();
    registration3.removeHandler();

    /*
     * removing handlers manually should remove registration from the internal
     * set.
     */

    assertEquals(0, subject.getRegistrationSize());

    subject.removeHandlers();
    
    // Expect nothing to happen. Especially no exceptions.
    registration1.removeHandler();
  }

  public void testNestedRemoveMemory() {
    org.gwtproject.event.shared.SimpleEventBus eventBus = new SimpleEventBus();
    org.gwtproject.event.shared.ResettableEventBus wideScope = new org.gwtproject.event.shared.ResettableEventBus(eventBus);
    org.gwtproject.event.shared.ResettableEventBus narrowScope = new ResettableEventBus(wideScope);

    Type<MouseDownHandler> type = MouseDownEvent.getType();
    wideScope.addHandler(type, mouse1);
    narrowScope.addHandler(type, mouse2);
    narrowScope.addHandler(type, mouse3);

    narrowScope.removeHandlers();
    wideScope.removeHandlers();

    /*
     * Internal registeration should be empty after calling removeHandlers
     */

    assertEquals(0, wideScope.getRegistrationSize());
    assertEquals(0, narrowScope.getRegistrationSize());

    wideScope.addHandler(type, mouse1);
    narrowScope.addHandler(type, mouse2);

    /*
     * Reverse remove order
     */

    wideScope.removeHandlers();
    narrowScope.removeHandlers();

    assertEquals(0, wideScope.getRegistrationSize());
    assertEquals(0, narrowScope.getRegistrationSize());
  }

}

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
package org.gwtproject.event.logical.shared;

import org.gwtproject.event.logical.shared.BeforeSelectionEvent;
import org.gwtproject.event.logical.shared.BeforeSelectionHandler;
import org.gwtproject.event.logical.shared.CloseEvent;
import org.gwtproject.event.logical.shared.CloseHandler;
import org.gwtproject.event.logical.shared.OpenEvent;
import org.gwtproject.event.logical.shared.OpenHandler;
import org.gwtproject.event.logical.shared.ResizeEvent;
import org.gwtproject.event.logical.shared.ResizeHandler;
import org.gwtproject.event.logical.shared.SelectionEvent;
import org.gwtproject.event.logical.shared.SelectionHandler;
import org.gwtproject.event.logical.shared.ValueChangeEvent;
import org.gwtproject.event.logical.shared.ValueChangeHandler;
import org.gwtproject.event.shared.EventHandler;
import org.gwtproject.event.shared.GwtEvent;
import org.gwtproject.event.shared.HandlerManager;

import junit.framework.TestCase;

/**
 * Tests of logical events.
 */
public class LogicalEventsTest extends TestCase {

  static class Fire implements SelectionHandler<String>,
          BeforeSelectionHandler<String>, CloseHandler<String>,
          OpenHandler<String>, ResizeHandler, ValueChangeHandler<String> {
    public boolean flag = false;

    @Override
    public void onBeforeSelection(org.gwtproject.event.logical.shared.BeforeSelectionEvent<String> event) {
      flag = true;
    }

    @Override
    public void onClose(org.gwtproject.event.logical.shared.CloseEvent<String> event) {
      flag = true;
    }

    @Override
    public void onOpen(org.gwtproject.event.logical.shared.OpenEvent<String> event) {
      flag = true;
    }

    @Override
    public void onResize(org.gwtproject.event.logical.shared.ResizeEvent event) {
      flag = true;
    }

    @Override
    public void onSelection(org.gwtproject.event.logical.shared.SelectionEvent<String> event) {
      flag = true;
    }

    @Override
    public void onValueChange(org.gwtproject.event.logical.shared.ValueChangeEvent<String> event) {
      flag = true;
    }
  }

  private HandlerManager manager;

  @Override
  public void setUp() {
    manager = new HandlerManager(this);
  }

  public void testSimpleFire() {
    simpleFire(org.gwtproject.event.logical.shared.BeforeSelectionEvent.getType(),
        new BeforeSelectionEvent<String>());
    simpleFire(org.gwtproject.event.logical.shared.SelectionEvent.getType(), new SelectionEvent<String>(null));
    simpleFire(org.gwtproject.event.logical.shared.CloseEvent.getType(), new CloseEvent<String>(null, false));
    simpleFire(org.gwtproject.event.logical.shared.OpenEvent.getType(), new OpenEvent<String>(null));
    simpleFire(org.gwtproject.event.logical.shared.ResizeEvent.getType(), new ResizeEvent(0, 0));
    simpleFire(org.gwtproject.event.logical.shared.ValueChangeEvent.getType(), new ValueChangeEvent<String>(null));
  }

  @SuppressWarnings("unchecked")
  private <H extends EventHandler> void simpleFire(GwtEvent.Type<H> type,
      @SuppressWarnings("rawtypes") GwtEvent instance) {
    Fire f = new Fire();
    manager.addHandler(type, (H) f);
    manager.fireEvent(instance);
    assertTrue(f.flag);
  }
}

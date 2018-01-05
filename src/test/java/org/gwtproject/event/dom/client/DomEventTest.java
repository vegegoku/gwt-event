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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import org.gwtproject.event.dom.client.ClickEvent;
import org.gwtproject.event.dom.client.ClickHandler;
import org.gwtproject.event.dom.client.DomEvent;
import org.gwtproject.event.dom.client.DoubleClickEvent;
import org.gwtproject.event.dom.client.DoubleClickHandler;
import org.gwtproject.event.dom.client.KeyDownEvent;
import org.gwtproject.event.dom.client.KeyDownHandler;
import org.gwtproject.event.dom.client.KeyPressEvent;
import org.gwtproject.event.dom.client.KeyPressHandler;
import org.gwtproject.event.dom.client.KeyUpEvent;
import org.gwtproject.event.dom.client.KeyUpHandler;
import org.gwtproject.event.dom.client.MouseDownEvent;
import org.gwtproject.event.dom.client.MouseDownHandler;
import org.gwtproject.event.dom.client.MouseEvent;
import org.gwtproject.event.dom.client.MouseMoveEvent;
import org.gwtproject.event.dom.client.MouseMoveHandler;
import org.gwtproject.event.dom.client.MouseOutEvent;
import org.gwtproject.event.dom.client.MouseOutHandler;
import org.gwtproject.event.dom.client.MouseOverEvent;
import org.gwtproject.event.dom.client.MouseOverHandler;
import org.gwtproject.event.dom.client.MouseUpEvent;
import org.gwtproject.event.dom.client.MouseUpHandler;
import org.gwtproject.event.dom.client.MouseWheelEvent;
import org.gwtproject.event.dom.client.MouseWheelHandler;
import org.gwtproject.event.shared.EventHandler;
import org.gwtproject.event.shared.HandlerManager;
import org.gwtproject.event.shared.HandlerRegistration;
import org.gwtproject.event.shared.HandlerTestBase;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Events test.
 */
public class DomEventTest extends HandlerTestBase {
  private HandlerManager manager;
  static class Flag {
    public boolean flag = false;
  }

  interface CustomClickHandler extends EventHandler {
    void onClick(CustomClickEvent evt);
  }

  static class CustomClickEvent extends MouseEvent<CustomClickHandler> {

    public static final Type<CustomClickHandler> TYPE =
        new Type<CustomClickHandler>("click", new CustomClickEvent());

    public static Type<CustomClickHandler> getType() {
      return TYPE;
    }

    @Override
    public Type<CustomClickHandler> getAssociatedType() {
      return TYPE;
    }

    @Override
    protected void dispatch(CustomClickHandler handler) {
      handler.onClick(this);
    }
  }

  public void testKeyEvents() {

    final Flag flag = new Flag();
    manager = new HandlerManager(flag);
    HandlerRegistration downRegistration = manager.addHandler(
        org.gwtproject.event.dom.client.KeyDownEvent.getType(), new KeyDownHandler() {
          @Override
          public void onKeyDown(org.gwtproject.event.dom.client.KeyDownEvent event) {
            flag.flag = true;
          }
        });
    HandlerRegistration upRegistration = manager.addHandler(
        org.gwtproject.event.dom.client.KeyUpEvent.getType(), new KeyUpHandler() {
          @Override
          public void onKeyUp(org.gwtproject.event.dom.client.KeyUpEvent event) {
            flag.flag = true;
          }
        });

    HandlerRegistration pressRegistration = manager.addHandler(
        org.gwtproject.event.dom.client.KeyPressEvent.getType(), new KeyPressHandler() {
          @Override
          public void onKeyPress(org.gwtproject.event.dom.client.KeyPressEvent event) {
            flag.flag = true;
          }
        });

    checkFire(new KeyDownEvent(), downRegistration, flag, "onKeyDown");
    checkFire(new KeyUpEvent(), upRegistration, flag, "onKeyUp");
    checkFire(new KeyPressEvent(), pressRegistration, flag, "onKeyPressed");
  }

  public void testMouseEvents() {
    final Flag flag = new Flag();
    manager = new HandlerManager(flag);

    HandlerRegistration downRegistration = manager.addHandler(
        org.gwtproject.event.dom.client.MouseDownEvent.getType(), new org.gwtproject.event.dom.client.MouseDownHandler() {
          @Override
          public void onMouseDown(org.gwtproject.event.dom.client.MouseDownEvent event) {
            flag.flag = true;
          }
        });
    HandlerRegistration upRegistration = manager.addHandler(
        org.gwtproject.event.dom.client.MouseUpEvent.getType(), new MouseUpHandler() {
          @Override
          public void onMouseUp(org.gwtproject.event.dom.client.MouseUpEvent event) {
            flag.flag = true;
          }
        });

    HandlerRegistration clickRegistration = manager.addHandler(
        org.gwtproject.event.dom.client.ClickEvent.getType(), new org.gwtproject.event.dom.client.ClickHandler() {
          @Override
          public void onClick(org.gwtproject.event.dom.client.ClickEvent event) {
            flag.flag = true;
          }
        });

    HandlerRegistration dblclickRegistration = manager.addHandler(
        org.gwtproject.event.dom.client.DoubleClickEvent.getType(), new DoubleClickHandler() {
          @Override
          public void onDoubleClick(org.gwtproject.event.dom.client.DoubleClickEvent event) {
            flag.flag = true;
          }
        });

    HandlerRegistration outRegistration = manager.addHandler(
        org.gwtproject.event.dom.client.MouseOutEvent.getType(), new MouseOutHandler() {
          @Override
          public void onMouseOut(org.gwtproject.event.dom.client.MouseOutEvent event) {
            flag.flag = true;
          }
        });
    HandlerRegistration overRegistration = manager.addHandler(
        org.gwtproject.event.dom.client.MouseOverEvent.getType(), new MouseOverHandler() {
          @Override
          public void onMouseOver(org.gwtproject.event.dom.client.MouseOverEvent event) {
            flag.flag = true;
          }
        });

    HandlerRegistration moveRegistration = manager.addHandler(
        org.gwtproject.event.dom.client.MouseMoveEvent.getType(), new MouseMoveHandler() {
          @Override
          public void onMouseMove(org.gwtproject.event.dom.client.MouseMoveEvent event) {
            flag.flag = true;
          }
        });

    HandlerRegistration wheelRegistration = manager.addHandler(
        org.gwtproject.event.dom.client.MouseWheelEvent.getType(), new MouseWheelHandler() {
          @Override
          public void onMouseWheel(org.gwtproject.event.dom.client.MouseWheelEvent event) {
            flag.flag = true;
          }
        });

    checkFire(new org.gwtproject.event.dom.client.MouseDownEvent(), downRegistration, flag, "onMouseDown");
    checkFire(new MouseUpEvent(), upRegistration, flag, "onMouseUp");
    checkFire(new MouseOutEvent(), outRegistration, flag, "onMouseOut");
    checkFire(new MouseOverEvent(), overRegistration, flag, "onMouseOver");
    checkFire(new MouseMoveEvent(), moveRegistration, flag, "onMouseMove");
    checkFire(new MouseWheelEvent(), wheelRegistration, flag, "onMouseWheel");
    checkFire(new org.gwtproject.event.dom.client.ClickEvent(), clickRegistration, flag, "onClick");
    checkFire(new DoubleClickEvent(), dblclickRegistration, flag,
        "onDoubleClick");
  }

 //  TODO uncomment when button can take the new MouseDownHandler
//  public void testMouseEventCoordinates() {
//    Button b = new Button();
//    RootPanel.get().add(b);
//
//    final Flag flag = new Flag();
//    b.addMouseDownHandler(new MouseDownHandler() {
//      @Override
//      public void onMouseDown(MouseDownEvent event) {
//        assertEquals("", 16, event.getX());
//        assertEquals("", 8, event.getY());
//        flag.flag = true;
//      }
//    });
//
//    int x = b.getAbsoluteLeft() + 16;
//    int y = b.getAbsoluteTop() + 8;
//    NativeEvent event = Document.get().createMouseDownEvent(0, x, y, x, y,
//        false, false, false, false, 1);
//    b.getElement().dispatchEvent(event);
//
//    assertTrue("Never received expected mouse-down event", flag.flag);
//  }

//  TODO uncomment when button can take the new Click handler

//  public void testMultipleDomEventTypesPerEventName() {
//    Button b = new Button();
//    RootPanel.get().add(b);
//
//    final Flag first = new Flag();
//    b.addClickHandler(new ClickHandler() {
//      @Override
//      public void onClick(ClickEvent event) {
//        first.flag = true;
//      }
//    });
//
//    final Flag second = new Flag();
//    b.addDomHandler(new CustomClickHandler() {
//      @Override
//      public void onClick(CustomClickEvent event) {
//        second.flag = true;
//      }
//    }, CustomClickEvent.getType());
//
//    NativeEvent event = Document.get().createClickEvent(0, 0, 0, 0, 0, false, false, false, false);
//    b.getElement().dispatchEvent(event);
//
//    assertTrue("Never received expected click event", first.flag);
//    assertTrue("Never received expected click event", second.flag);
//  }

  private void checkFire(DomEvent<?> event, HandlerRegistration registration,
                         Flag flag, String eventName) {

    flag.flag = false;
    manager.fireEvent(event);
    assertTrue(eventName + " didn't fire.", flag.flag);

    flag.flag = false;
    registration.removeHandler();
    // event.reset(null);
    manager.fireEvent(event);
    assertTrue(eventName + " fired when it shouldn't have.", !flag.flag);
  }
}

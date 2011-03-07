/**
 * Copyright (C) 2008 Ovea <dev@testatoo.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.testatoo.core;

import org.junit.Before;
import org.junit.Test;
import org.testatoo.WebTest;
import org.testatoo.core.component.Button;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.testatoo.cartridge.flex3.Language.workOn;
import static org.testatoo.core.ComponentFactory.component;
import static org.testatoo.core.ComponentFactory.page;

public class ButtonTest extends WebTest {

    @Before
    public void setUp() {
        page().open("component/button/Button.html");
        workOn("FlexApplication");
    }

    @Test
    public void can_find_button_by_id() {
        component(Button.class, "button");

        try {
            component(Button.class, "otherButton");
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("Cannot find component defined by id=otherButton"));
        }
    }

    @Test
    public void exception_thrown_if_component_not_a_button() {
        try {
            component(Button.class, "componentError");
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=componentError is not a Button but a CheckBox"));
        }
    }

    @Test
    public void can_obtain_button_text() {
        assertThat(component(Button.class, "button").text(), is("Button"));
    }

    @Test
    public void can_get_the_icon() {
        assertThat(component(Button.class, "button").icon(), containsString("forward_png"));
    }

    @Test
    public void test_toString() {
        assertThat(component(Button.class, "button").toString(), containsString("class org.testatoo.core.component.Button with state : enabled:true, visible:true, text:Button"));
    }
}

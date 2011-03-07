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
import org.testatoo.core.component.Panel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;
import static org.testatoo.cartridge.flex3.Language.workOn;
import static org.testatoo.core.ComponentFactory.component;
import static org.testatoo.core.ComponentFactory.page;
import static org.testatoo.core.matcher.Matchers.*;

public class PanelTest extends WebTest {

    @Before
    public void setUp() {
        page().open("component/panel/Panel.html");
        workOn("FlexApplication");
    }

    @Test
    public void can_find_panel_by_id() {
        component(Panel.class, "panel");

        try {
            component(Panel.class, "otherPanel");
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("Cannot find component defined by id=otherPanel"));
        }
    }

    @Test
    public void exception_thrown_if_component_not_a_panel() {
        try {
            component(Panel.class, "componentError");
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=componentError is not a Panel but a CheckBox"));
        }
    }

    @Test
    public void can_test_title() {
        assertThat(component(Panel.class, "panel"), has(title("PanelTitle")));
    }

    @Test
    public void test_toString() {
        assertThat(component(Panel.class, "panel").toString(), is("class org.testatoo.core.component.Panel with state : enabled:true, visible:true, title:PanelTitle"));
    }
}

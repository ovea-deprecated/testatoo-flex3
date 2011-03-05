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

package org.testatoo.cartridge.core;

import org.junit.Before;
import org.junit.Test;
import org.testatoo.cartridge.WebTest;
import org.testatoo.core.ComponentException;
import org.testatoo.core.component.TextField;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.testatoo.cartridge.flex3.Language.workOn;
import static org.testatoo.core.ComponentFactory.component;
import static org.testatoo.core.ComponentFactory.page;
import static org.testatoo.core.Language.on;
import static org.testatoo.core.Language.type;
import static org.testatoo.core.matcher.Matchers.*;

public class TextFieldTest extends WebTest {

    @Before
    public void setUp() {
        page().open("component/textfield/TextField.html");
        workOn("FlexApplication");
    }

    @Test
    public void can_find_textfield_by_id() {
        component(TextField.class, "textfield");

        try {
            component(TextField.class, "textfield_id");
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("Cannot find component defined by id=textfield_id"));
        }
    }

    @Test
    public void exception_thrown_if_component_not_a_textField() {
        try {
            component(TextField.class, "componentError");
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=componentError is not a TextField but a CheckBox"));
        }
    }

    @Test
    public void can_type() {
        assertThat(component(TextField.class, "textfield"), has(value("")));
        type("Jean Pierre", on(component(TextField.class, "textfield")));
        assertThat(component(TextField.class, "textfield"), has(value("Jean Pierre")));
    }

    @Test
    public void can_obtain_the_label() {
        assertThat(component(TextField.class, "textfield"), has(label("Label 1")));
        assertThat(component(TextField.class, "textfield_disabled"), has(label("Label 2")));
        assertThat(component(TextField.class, "textfield_hidden"), has(label("Label 3")));
    }

    @Test
    public void test_max_length() {
        assertThat(component(TextField.class, "textfield").maxLength(), is(25));
    }

    @Test
    public void test_toString() {
        type("myValue", on(component(TextField.class, "textfield")));
        assertThat(component(TextField.class, "textfield").toString(), is("class org.testatoo.core.component.TextField with state" +
                " : enabled:true, visible:true, value:myValue, label:Label 1, maxLength:25"));
    }
}

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
import org.testatoo.core.component.PasswordField;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;
import static org.testatoo.cartridge.flex3.Language.workOn;
import static org.testatoo.core.ComponentFactory.component;
import static org.testatoo.core.ComponentFactory.page;
import static org.testatoo.core.Language.on;
import static org.testatoo.core.Language.type;
import static org.testatoo.core.matcher.Matchers.*;

public class PasswordFieldTest extends WebTest {

    @Before
    public void setUp() {
        page().open("component/passwordfield/PasswordField.html");
        workOn("FlexApplication");
    }

    @Test
    public void can_find_passwordfield_by_id() {
        component(PasswordField.class, "password");

        try {
            component(PasswordField.class, "password_id");
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("Cannot find component defined by id=password_id"));
        }
    }

    @Test
    public void exception_thrown_if_component_not_a_passwordField() {
        try {
            component(PasswordField.class, "componentError");
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=componentError is not a PasswordField but a CheckBox"));
        }
    }

    @Test
    public void can_obtain_the_label() {
        assertThat(component(PasswordField.class, "password"), has(label("Label 1")));
        assertThat(component(PasswordField.class, "password_disabled"), has(label("Label 2")));
        assertThat(component(PasswordField.class, "password_hidden"), has(label("Label 3")));
    }

    @Test
    public void can_type() {
        assertThat(component(PasswordField.class, "password"), has(value("")));
        type("myPassword", on(component(PasswordField.class, "password")));
        assertThat(component(PasswordField.class, "password"), has(value("myPassword")));
    }

    @Test
    public void test_max_length() {
        assertThat(component(PasswordField.class, "password").maxLength(), is(20));
    }

    @Test
    public void test_toString() {
        type("myValue", on(component(PasswordField.class, "password")));
        assertThat(component(PasswordField.class, "password").toString(), is("class org.testatoo.core.component.PasswordField with state : enabled:true, visible:true, value:myValue, label:Label 1, maxLength:20"));
    }
}

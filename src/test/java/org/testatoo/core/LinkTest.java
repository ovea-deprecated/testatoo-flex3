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

import org.junit.BeforeClass;
import org.junit.Test;
import org.testatoo.WebTest;
import org.testatoo.core.component.Link;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.testatoo.cartridge.flex3.Language.workOn;
import static org.testatoo.core.ComponentFactory.component;
import static org.testatoo.core.ComponentFactory.page;

public class LinkTest extends WebTest {

    @BeforeClass
    public static void setUp() {
        page().open("component/linkbutton/LinkButton.html");
        workOn("FlexApplication");
    }

    @Test
    public void can_find_link_by_id() {
        component(Link.class, "a_1");

        try {
            component(Link.class, "a_2");
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("Cannot find component defined by id=a_2"));
        }
    }

    @Test
    public void exception_thrown_if_component_not_a_link() {
        try {
            component(Link.class, "radio");
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=radio is not a Link but a Radio"));
        }
    }

    @Test
    public void can_obtain_content() {
        assertThat(component(Link.class, "a_1").text(), is("Link 1 - a type"));
    }

    @Test
    public void can_obtain_reference() {
        // No reference in flex api
        assertThat(component(Link.class, "a_1").reference(), containsString(""));
    }

    @Test
    public void test_toString() {
        assertThat(component(Link.class, "a_1").toString(), is("class org.testatoo.core.component.Link with state : enabled:true, visible:true, text:Link 1 - a type, reference:"));
    }
}

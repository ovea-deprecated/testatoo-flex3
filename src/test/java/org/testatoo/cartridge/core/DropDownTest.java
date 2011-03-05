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
import org.testatoo.core.component.DropDown;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.testatoo.cartridge.flex3.Language.workOn;
import static org.testatoo.core.ComponentFactory.component;
import static org.testatoo.core.ComponentFactory.page;
import static org.testatoo.core.matcher.Matchers.*;

public class DropDownTest extends WebTest {

    @Before
    public void setUp() {
        page().open("component/dropdown/DropDown.html");
        workOn("FlexApplication");
    }

    @Test
    public void can_find_dropdown_by_id() {
        component(DropDown.class, "dropdown");

        try {
            component(DropDown.class, "otherDropdown");
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("Cannot find component defined by id=otherDropdown"));
        }
    }

    @Test
    public void can_obtain_the_label() {
        assertThat(component(DropDown.class, "dropdown"), has(label("States:")));
        assertThat(component(DropDown.class, "dropdown_disabled"), has(label("USA States:")));
    }

    @Test
    public void can_select_value() {
        component(DropDown.class, "dropdown").select("Oklahoma");
        assertThat(component(DropDown.class, "dropdown"), has(selectedValue("Oklahoma")));
    }

    @Test
    public void can_list_values() {
        assertThat(component(DropDown.class, "dropdown").values().size(), is(51));
    }

    @Test
    public void test_toString() {
        assertThat(component(DropDown.class, "dropdown").toString(), is("class org.testatoo.core.component.DropDown with state : enabled:true, visible:true, " +
                "values:[Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware, District of Columbia, Florida, Georgia, Hawaii, " +
                "Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana, Maine, Maryland, Massachusetts, Michigan, Minnesota, Mississippi, Missouri, " +
                "Montana, Nebraska, Nevada, New Hampshire, New Jersey, New Mexico, New York, North Carolina, North Dakota, Ohio, Oklahoma, Oregon, " +
                "Pennsylvania, Rhode Island, South Carolina, South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia, Wisconsin, " +
                "Wyoming], selectedValues:[Alabama], label:States:"));
    }
}

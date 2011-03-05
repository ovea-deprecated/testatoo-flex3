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
import org.testatoo.core.Selection;
import org.testatoo.core.component.datagrid.Column;
import org.testatoo.core.component.datagrid.DataGrid;
import org.testatoo.core.component.datagrid.Row;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;
import static org.testatoo.cartridge.flex3.Language.workOn;
import static org.testatoo.core.ComponentFactory.component;
import static org.testatoo.core.ComponentFactory.page;
import static org.testatoo.core.matcher.Matchers.*;

public class DataGridTest extends WebTest {

    @Before
    public void setUp() {
        page().open("component/datagrid/DataGrid.html");
        workOn("FlexApplication");
    }

    @Test
    public void can_find_datagrid_by_id() {
        component(DataGrid.class, "datagrid");

        try {
            component(DataGrid.class, "otherGrid");
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("Cannot find component defined by id=otherGrid"));
        }
    }

    @Test
    public void exception_thrown_if_component_not_a_datagrid() {
        try {
            component(DataGrid.class, "gridError");
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=gridError is not a DataGrid but a Button"));
        }
    }

    @Test
    public void can_test_columns_title() {
        DataGrid datagrid = component(DataGrid.class, "datagrid");

        assertThat(datagrid.columns().size(), is(5));
        Selection<Column> columns = component(DataGrid.class, "datagrid").columns();

        assertThat(columns.get(0).title(), is("Company"));
        assertThat(columns.get(1).title(), is("Price"));
        assertThat(columns.get(2).title(), is("Change"));
        assertThat(columns.get(3).title(), is("% Change"));
        assertThat(columns.get(4).title(), is("Last Updated"));

        assertThat(datagrid.column(1).title(), is("Company"));
        assertThat(datagrid.column(2).title(), is("Price"));
        assertThat(datagrid.column(3).title(), is("Change"));
        assertThat(datagrid.column(4).title(), is("% Change"));
        assertThat(datagrid.column(5).title(), is("Last Updated"));
    }

    @Test
    public void can_test_column_cells() {
        DataGrid datagrid = component(DataGrid.class, "datagrid");
        Selection<Column> columns = datagrid.columns();

        assertThat(columns.get(0).cells().size(), is(26));
        assertThat(columns.get(1).cells().size(), is(26));
        assertThat(columns.get(2).cells().size(), is(26));

        assertThat(columns.get(0).cells().get(0).value(), is("3m Co"));
        assertThat(columns.get(0).cells().get(1).value(), is("Alcoa Inc"));
        assertThat(columns.get(0).cells().get(2).value(), is("Altria Group Inc"));

        assertThat(columns.get(1).cells().get(0).value(), is("$71.72"));
        assertThat(columns.get(1).cells().get(1).value(), is("$29.01"));
        assertThat(columns.get(1).cells().get(2).value(), is("$83.81"));

        assertThat(columns.get(2).cells().get(0).value(), is("0.02"));
        assertThat(columns.get(2).cells().get(1).value(), is("0.42"));
        assertThat(columns.get(2).cells().get(2).value(), is("0.28"));

        assertThat(datagrid.column(3).cell(6).value(), is("-0.48"));
    }

    @Test
    public void can_test_row_cell() {
        DataGrid datagrid = component(DataGrid.class, "datagrid");
        Selection<Row> rows = datagrid.rows();

        assertThat(rows.size(), is(26));

        assertThat(rows.get(0).cells().size(), is(5));
        assertThat(rows.get(1).cells().size(), is(5));
        assertThat(rows.get(2).cells().size(), is(5));

        assertThat(rows.get(0).cells().get(0).value(), is("3m Co"));
        assertThat(rows.get(0).cells().get(1).value(), is("$71.72"));
        assertThat(rows.get(0).cells().get(2).value(), is("0.02"));
        assertThat(rows.get(0).cells().get(3).value(), is("0.03%"));
        assertThat(rows.get(0).cells().get(4).value(), is("09/01/2009"));

        assertThat(rows.get(1).cells().get(0).value(), is("Alcoa Inc"));
        assertThat(rows.get(1).cells().get(1).value(), is("$29.01"));
        assertThat(rows.get(1).cells().get(2).value(), is("0.42"));
        assertThat(rows.get(1).cells().get(3).value(), is("1.47%"));
        assertThat(rows.get(1).cells().get(4).value(), is("09/01/2009"));

        assertThat(rows.get(2).cells().get(0).value(), is("Altria Group Inc"));
        assertThat(rows.get(2).cells().get(1).value(), is("$83.81"));
        assertThat(rows.get(2).cells().get(2).value(), is("0.28"));
        assertThat(rows.get(2).cells().get(3).value(), is("0.34%"));
        assertThat(rows.get(2).cells().get(4).value(), is("09/01/2009"));

        assertThat(datagrid.row(1).cell(1).value(), is("3m Co"));
        assertThat(datagrid.row(1).cell(2).value(), is("$71.72"));
        assertThat(datagrid.row(1).cell(3).value(), is("0.02"));
        assertThat(datagrid.row(1).cell(4).value(), is("0.03%"));
        assertThat(datagrid.row(1).cell(5).value(), is("09/01/2009"));

        assertThat(datagrid.row(2).cell(1).value(), is("Alcoa Inc"));
        assertThat(datagrid.row(2).cell(2).value(), is("$29.01"));
        assertThat(datagrid.row(2).cell(3).value(), is("0.42"));
        assertThat(datagrid.row(2).cell(4).value(), is("1.47%"));
        assertThat(datagrid.row(2).cell(5).value(), is("09/01/2009"));

        assertThat(datagrid.row(3).cell(1).value(), is("Altria Group Inc"));
        assertThat(datagrid.row(3).cell(2).value(), is("$83.81"));
        assertThat(datagrid.row(3).cell(3).value(), is("0.28"));
        assertThat(datagrid.row(3).cell(4).value(), is("0.34%"));
        assertThat(datagrid.row(3).cell(5).value(), is("09/01/2009"));

        assertThat(datagrid.row(6).cell(1).value(), is("AT&T Inc."));
        assertThat(datagrid.row(6).cell(2).value(), is("$31.61"));
        assertThat(datagrid.row(6).cell(3).value(), is("-0.48"));
    }

    @Test
    public void test_toString() {
        DataGrid grid1 = component(DataGrid.class, "datagrid");
        assertThat(grid1.toString(), is("class org.testatoo.core.component.datagrid.DataGrid with state : enabled:true, visible:true, column(s):5, row(s):26"));
    }

}

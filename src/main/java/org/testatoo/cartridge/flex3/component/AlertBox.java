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

package org.testatoo.cartridge.flex3.component;

import org.testatoo.core.Evaluator;

import java.util.UUID;

public class AlertBox extends org.testatoo.core.component.AlertBox {

    public static final String ID = "_$ALERTBOX$_";

    public AlertBox(Evaluator evaluator) {
        super(evaluator, ID);
    }

    @Override
    public Boolean isVisible() {
        return true;
    }

    @Override
    public Boolean isEnabled() {
        return true;
    }
}

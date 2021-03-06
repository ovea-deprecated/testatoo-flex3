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

package org.testatoo.cartridge.flex3;

import org.testatoo.cartridge.flex3.evaluator.selenium.SeleniumFlexEvaluator;
import org.testatoo.core.EvaluatorHolder;

public class Language extends org.testatoo.core.Language {

    public static void workOn(String flexObjectIdentifier) {
        ((SeleniumFlexEvaluator) EvaluatorHolder.get()).workOn(flexObjectIdentifier);
    }

}

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

import org.testatoo.cartridge.flex3.FlexEvaluator;
import org.testatoo.core.component.*;

import java.lang.reflect.InvocationTargetException;

/**
 * @author David Avenante
 */
public class ComponentFactory {

    public static Page page() {
        return component(Page.class, "html");
    }

    public static <T extends Component> T component(Class<T> componentType, String id) {
        Class cmpType = componentType;

        if (componentType.equals(AlertBox.class) || componentType.equals(org.testatoo.cartridge.flex3.component.AlertBox.class))
            return (T) new org.testatoo.cartridge.flex3.component.AlertBox(evaluator());

        try {
            return (T) cmpType.getConstructor(FlexEvaluator.class, String.class).newInstance(evaluator(), id);
        } catch (Exception e) {
            if (e.getCause() instanceof ComponentException)
                throw (ComponentException) e.getCause();
            try {
                return componentType.getConstructor(Evaluator.class, String.class).newInstance(evaluator(), id);
            } catch (InvocationTargetException ite) {
                throw new ComponentException(ite.getTargetException().getMessage(), ite.getTargetException());
            } catch (Exception e1) {
                throw new ComponentException(e1.getMessage(), e1);
            }
        }
    }

    private static FlexEvaluator evaluator() {
        return EvaluatorHolder.get();
    }

}

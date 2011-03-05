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

package {
import mx.controls.Label;
import mx.controls.listClasses.*;

public class VariationRenderer extends Label {

    private const POSITIVE_COLOR:uint = 0x000000; // Black
    private const NEGATIVE_COLOR:uint = 0xFF0000; // Red

    override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void {
        super.updateDisplayList(unscaledWidth, unscaledHeight);

        setStyle("color", (data.variation <= 0) ? NEGATIVE_COLOR : POSITIVE_COLOR);
    }
}
}

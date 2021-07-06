/*
 * Copyright 2020 Dmitry Spikhalskiy. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spikhalskiy.exceptiontracer;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;

public class ExceptionTracer {
    public static  <T> void traceOnConstruction(Class<T> clazzToTrack) {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(clazzToTrack)
                .visit(Advice.to(LoggingAdvice.class).on(ElementMatchers.isConstructor()))
                .make()
                .load(clazzToTrack.getClassLoader(),
                        ClassReloadingStrategy.fromInstalledAgent());
    }
}

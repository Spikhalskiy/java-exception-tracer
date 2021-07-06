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

import net.bytebuddy.asm.Advice;

public class LoggingAdvice {
    @Advice.OnMethodExit(suppress = Throwable.class)
    public static void exit(@Advice.This Object thisObject) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();
        //we skip the first element because it will be inside this LoggingAdvice class and shouldn't be interesting
        //for the user
        for (int i = 1; i < stackTrace.length; i++) {
            sb.append("\n").append(stackTrace[i]);
        }

        System.out.println("[" + Thread.currentThread().getName() +"]" + " Created an instance of " +
                thisObject.getClass() + ", stacktrace: " + sb);
    }
}






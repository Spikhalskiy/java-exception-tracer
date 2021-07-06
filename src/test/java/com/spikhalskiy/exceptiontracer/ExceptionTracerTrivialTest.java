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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExceptionTracerTrivialTest {
    /**
     * We completely ignored the interrupted exception in the child thread, but ExceptionTracer still printed it's
     * stacktrace in console with the name of the thread it was created in.
     */
    @Test
    public void test() throws InterruptedException {
        ExceptionTracer.traceOnConstruction(InterruptedException.class);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //some evil dev ignores the interrupted exception
                }
            }
        }, "some-thread-name");
        thread.start();
        thread.interrupt();
        thread.join();
    }
}

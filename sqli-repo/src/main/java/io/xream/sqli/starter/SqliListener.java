/*
 * Copyright 2020 io.xream.sqli
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.xream.sqli.starter;

import io.xream.sqli.api.NativeSupport;
import io.xream.sqli.parser.ParserListener;
import io.xream.sqli.repository.exception.BeanUninitializedException;

/**
 * @Author Sim
 */
public class SqliListener {

    private static SqliListener instance;
    private SqliListener(){}

    private static boolean initialized = false;

    public static void onBeanCreated(InitPhaseable initPhaseable){
        initialized |= initPhaseable.init();
    }

    public static void onStarted(NativeSupport nativeSupport){
        if (instance != null)
            return;

        if (! initialized)
            throw new BeanUninitializedException("to confirm all bean initialized, please call SqliListener.onBeanCreated(...) at leaset one time");

        instance = new SqliListener();

        HealthChecker.onStarted(nativeSupport);
        ParserListener.onStarted();
    }
}

/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 */

package org.jnosql.demo.se;


import jakarta.nosql.mapping.document.DocumentTemplate;
import jakarta.nosql.document.DocumentQuery;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.nosql.document.DocumentQuery.select;

public class App {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Hero ironMan = Hero.builder().withRealName("Tony Stark").withName("iron_man")
                    .withAge(34).withPowers(Collections.singleton("rich")).build();
            DocumentTemplate template = container.select(DocumentTemplate.class).get();

            template.insert(ironMan);

            DocumentQuery query = select().from("Hero").where("name").eq("iron_man").build();
            List<Hero> heroes = template.<Hero>select(query).collect(Collectors.toList());
            System.out.println(heroes);

        }
    }

    private App() {
    }
}

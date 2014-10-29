arquillian-guide
==========

Test project based on Arquillian Guides

<h1>Generating the JPA 2 Metamodel</h1>

1)  Use JDK 6

    <build>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.3.2</version>
                <configuration>
                    <source>1.6</source>
                    <target>1.6</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

2) Add the Hibernate JPA metamodel generator

    <plugin>
        <groupId>org.bsc.maven</groupId>
        <artifactId>maven-processor-plugin</artifactId>
        <version>2.2.4</version>
        <executions>
            <execution>
                <id>process</id>
                <phase>generate-sources</phase>
                <goals>
                    <goal>process</goal>
                </goals>
                <configuration>
                    <processors>
                        <processor>org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor</processor>
                    </processors>
                </configuration>
            </execution>
        </executions>
        <dependencies>
            <dependency>
                <groupId>org.hibernate</groupId>
                <artifactId>hibernate-jpamodelgen</artifactId>
                <version>4.3.6.Final</version>
            </dependency>
        </dependencies>
    </plugin>

3) Create the a file name .factorypath at the root of the project

    <factorypath>
        <factorypathentry kind="VARJAR" enabled="true" runInBatchMode="false" id="M2_REPO/org/hibernate/hibernate-jpamodelgen/1.2.0.Final/hibernate-jpamodelgen-1.2.0.Final.jar"/>
        <factorypathentry kind="VARJAR" enabled="true" runInBatchMode="false" id="M2_REPO/org/hibernate/javax/persistence/hibernate-jpa-2.0-api/1.0.0.Final/hibernate-jpa-2.0-api-1.0.0.Final.jar"/>
    </factorypath>

4) Right click on the project and select Properties. Expand the Java Compiler node in the settings tree and select Annotation Processing.

<ul>
<li>Enable project specific settings</li>
<li>Enable annotation processing</li>
<li>Set the "Generated source directory" to "target/generated-sources/annotations"</li>
<li>Apply and accept a full build</li>
<li>Disable annotation processing</li>
<li>Apply and skip a full build</li>
<li>Enable annotation processing</li>
<li>Apply and accept a full build</li>
</ul>

<h1>Generating WADL</h1>
mvn compile com.sun.jersey.contribs:maven-wadl-plugin:generate

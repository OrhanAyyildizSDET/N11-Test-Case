# N11 Automated Test Case

This test case was written using Cucumber-Selenium frameworks and JAVA language. I used the POM structure for the dynamic project. I have a hook package that placed a Hook class for instantiating some methods and variables before and after the cucumber scenario. I have a page package for locators and a package for step definitions. Also, ı have a util package for singleton Driver and reusable methods. I used the configuration.properties file for baseurl and user information and for reading it ReaderConfig class.

## Installation

You need some dependencies and plugins to run project.

```bash
<dependencies>
        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.10.0</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>5.4.1</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>7.13.0</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>7.13.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>net.masterthought</groupId>
                <artifactId>maven-cucumber-reporting</artifactId>
                <version>5.0.0</version>
                <executions>
                    <execution>
                        <id>execution</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <projectName>cucumber-framework</projectName>
                            <outputDirectory>${project.build.directory}</outputDirectory>
                            <!--                            <cucumberOutput>${project.build.directory}</cucumberOutput>-->
                            <inputDirectory>${project.build.directory}</inputDirectory>
                            <jsonFiles>
                                <param>**/json-reports/*.json</param>
                            </jsonFiles><classificationFiles>->
                            <param>sample.properties</param>
                            <param>other.properties</param>
                        </classificationFiles>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

## Usage

```python
Just run the Runner class. And if you add more scenarios you need to change the "@n11Favorite" tag. 

# Cucumber HTML report.
it is automatically created when you run the scenario and in every "assert" and "page 
transition" it took the screenshot and attaches the report. If scenarios failed
again it took the screenshot automatically when the scenario fails and attaches the report.

# Dynamic Scenario
You can change some parts of the scenario, "page number, product name, product list number, etc."

![N11 Happy Scenario test case](https://github.com/OrhanAyyildizSDET/N11-Test-Case/assets/100473852/eabc60f2-cfb5-42f2-afdc-2ae29cb4d00d)


```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

https://github.com/OrhanAyyildizSDET

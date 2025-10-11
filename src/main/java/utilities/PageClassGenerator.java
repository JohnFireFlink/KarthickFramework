package utilities;

import java.nio.file.*;
import org.json.JSONObject;

public class PageClassGenerator {

    public static void main(String[] args) throws Exception {

        // Path to your JSON file
        String jsonPath = "src/test/resources/Locators.json";

        // Target package and directory
        String targetPackage = "webPages";
        String targetDir = "src/main/java/" + targetPackage.replace(".", "/");

        // Read JSON
        String jsonContent = Files.readString(Paths.get(jsonPath));
        JSONObject pages = new JSONObject(jsonContent);

        // Create target directory if missing
        Files.createDirectories(Paths.get(targetDir));

        for (String pageName : pages.keySet()) {
            JSONObject pageLocators = pages.getJSONObject(pageName);

            StringBuilder classContent = new StringBuilder();
            classContent.append("package ").append(targetPackage).append(";\n\n")
                        .append("import java.util.ArrayList;\n")
                        .append("import org.openqa.selenium.By;\n\n")
                        .append("public class ").append(pageName).append(" {\n\n");

            for (String elementName : pageLocators.keySet()) {
                JSONObject locators = pageLocators.getJSONObject(elementName);

                classContent.append("    public ArrayList<By> ").append(elementName).append("() {\n")
                            .append("        ArrayList<By> locators = new ArrayList<>();\n");

                for (String type : locators.keySet()) {
                    String value = locators.getString(type).replace("']", ""); // clean malformed bracket if needed

                    switch (type.toLowerCase()) {
                        case "id":
                            classContent.append("        locators.add(By.id(\"").append(value).append("\"));\n");
                            break;
                        case "name":
                            classContent.append("        locators.add(By.name(\"").append(value).append("\"));\n");
                            break;
                        case "xpath":
                            classContent.append("        locators.add(By.xpath(\"").append(value).append("\"));\n");
                            break;
                        case "css":
                            classContent.append("        locators.add(By.cssSelector(\"").append(value).append("\"));\n");
                            break;
                        default:
                            classContent.append("        // Unknown locator type: ").append(type).append("\n");
                    }
                }

                classContent.append("        return locators;\n")
                            .append("    }\n\n");
            }

            classContent.append("}\n");

            // Write class file
            Path filePath = Paths.get(targetDir, pageName + ".java");
            Files.writeString(filePath, classContent.toString());
            
        }

    }
}

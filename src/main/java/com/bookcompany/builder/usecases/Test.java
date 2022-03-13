package com.bookcompany.builder.usecases;

import com.bookcompany.builder.model.Ingredient;
import com.bookcompany.builder.model.Recipe;
import com.bookcompany.builder.repository.Repository;
import com.bookcompany.builder.repository.RepositoryImpl;
import com.bookcompany.builder.service.Service;
import com.bookcompany.builder.service.ServiceImpl;
import lombok.NoArgsConstructor;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.*;

@NoArgsConstructor
public class Test {
    Service service = new ServiceImpl();
    Service<Ingredient> ingredientService = new ServiceImpl<Ingredient>();
    Service<Recipe> recipeService = new ServiceImpl<Recipe>();
    private static final String INPUT_PATH = "src/data/input/";
    private static final String OUTPUT_PATH = "src/data/output/";
    Scanner myObj = new Scanner(System.in);
    Scanner input = new Scanner(System.in);
    String report = "";

    public void testMode(){

    }

    public void listChoises(){
        System.out.println("***************************************************");
        System.out.println("Choose from these choices");
        System.out.println("-------------------------");
        System.out.println("1 - Load recipe file ");
        System.out.println("2 - Delete all saved recipes");
        System.out.println("3 - List all ingredients");
        System.out.println("4 - List vegetarian ingredients");
        System.out.println("5 - List recipes ordered by name");
        System.out.println("6 - List recipes ordered by smallest preparation time");
        System.out.println("7 - List vegetarian recipes");
        System.out.println("8 - List recipes containing ingredient");
        System.out.println("9 - List recipes within range of calories");
        System.out.println("10 - Export reports to text document");
        System.out.println("11 - Export reports to text document");
        System.out.println("12 - Export reports to text document");
        System.out.println("13 - Export reports to text document");
    }

    public void readUserFile(){
        System.out.print("Enter recipe file name :");
        String input = myObj.nextLine();
        try {
            this.readFile(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void doTransactions() throws Exception {
        List<String> name = new ArrayList<>();
        List<Recipe> names = new ArrayList<Recipe>();
        List<Recipe> names1 = new ArrayList<Recipe>();
        List<Recipe> names2 = new ArrayList<Recipe>();
        List<Recipe> recipeList = new ArrayList<Recipe>();

        this.readFile("ingredients.txt");
        System.out.println("***************************************************");
        System.out.println("Ingredients loaded successfully from 'ingredients.txt' file!");
        System.out.println("Default Recipe File: 'my_recipes.txt");
        System.out.println("Enter character D to load default recipe fail");
        System.out.println("Or enter custom recipe file to load from : ");

        String input = myObj.nextLine();
        if(input.equals("D")){
            input="my_recipes.txt";
        }
        this.readFile(input);
        System.out.println("Recipes loaded successfully from "+input+" file!");
        this.listChoises();
        System.out.print("Enter choice: ");


        while(true) {
            System.out.println("Enter option or 00 to show the menu or 77 to exit");
            Integer selection = myObj.nextInt();
            if(selection==00){
                this.listChoises();
            }else if(selection==77){
                break;
            }

            if (selection == 1) {
                System.out.println("Recipe file already loaded. Please restart application");
                //this.readUserFile();
            }
            if (selection == 2) {
                this.deleteRecipes();
                System.out.println("Deleted recipes!");
            }
            if (selection == 3) {
                System.out.println(this.listAllIngredients());
            }
            if (selection == 4) {
                System.out.println(this.listVegetarianIngredients());
            }
            if (selection == 5) {
                System.out.println(this.listRecipesOrdered());
            }
            if (selection == 6) {
                System.out.println(this.listRecipesMinTime());
            }
            if (selection == 7) {
                System.out.println(this.vegetarianRecipes());
            }
            if (selection == 8) {
                System.out.print("Enter ingredient you want to find recipes with:  ");
                input = myObj.nextLine();
                System.out.println(this.searchRecipes(input));
            }
            if (selection == 9) {
                System.out.println("Enter Two Numbers " + "(Press Enter after each):");
                int n1, n2;
                n1 = myObj.nextInt();
                n2 = myObj.nextInt();
                System.out.println(this.caloriesRecipes(n1, n2));
            }
            if (selection == 10) {
                this.exportReport();
                System.out.println("Report created!");
            }
            if (selection == 11) {

            }
            if (selection == 12) {

            }
            if (selection == 13) {

            }

        }


        }

        public void deleteRecipes() throws IOException {
            this.createFile("my_recipes.txt","");
        }
        //3 - List all ingredients
        public String listAllIngredients() throws Exception {
            return this.getStrings(ingredientService.read());
        }

        //4 - List vegetarian ingredients
        public String listVegetarianIngredients() throws Exception {
            List<Ingredient> vegetarianIngredients = new ArrayList<Ingredient>();
            for (Ingredient ingredient : ingredientService.read()) {
                if (ingredient.getVegetarian() == 'v') {
                    vegetarianIngredients.add(ingredient);
                }
            }
            return this.getStrings(vegetarianIngredients);
        }

        //5 - List recipes ordered by name
        public String listRecipesOrdered() throws Exception {
            List<String> name = new ArrayList<>();
            List<Recipe> orderedRecipes = new ArrayList<Recipe>();
            for (Recipe recipe : recipeService.read()) {
                name.add(recipe.getName());
            }
            Collections.sort(name);
            for(String v : name){
                orderedRecipes.add(recipeService.read(v));
            }
            return this.getRecipeStrings(orderedRecipes);
        }

        //6 - List recipes ordered by smallest preparation time
        public String listRecipesMinTime() throws Exception {
            List<String> name = new ArrayList<>();
            List<Recipe> minTimeRecipes = new ArrayList<Recipe>();
            List<Integer> times = new ArrayList<Integer>();
            for (Recipe recipe : recipeService.read()) {
                times.add(recipe.getTime());
            }
            Collections.sort(times);
            for (Integer time : times) {
                for (Recipe recipe : recipeService.read()) {
                    if (time == recipe.getTime()) {
                        name.add(recipe.getName());
                    }
                }
            }

            for(String v : name){
                minTimeRecipes.add(recipeService.read(v));
            }
            return this.getRecipeStrings(minTimeRecipes);
        }

        //7 - List vegetarian recipes
        public String vegetarianRecipes() throws Exception{
            List<String> name = new ArrayList<>();
            List<Recipe> vegetarianRecipes = new ArrayList<Recipe>();
            List<Recipe> vegetarianRecipeNames = new ArrayList<Recipe>();
            vegetarianRecipes =  recipeService.read();
            int i = 0;
            for(Recipe recipe : recipeService.read()){
                int count = 0;
                List<Ingredient> ingredientList = vegetarianRecipes.get(i).getIngredientList();
                for(Ingredient ingredient : ingredientList){
                    if(ingredient.getVegetarian().equals('n')){
                        count++;
                    }
                }
                if(count==0){
                    vegetarianRecipeNames.add(recipeService.read(recipe.getName()));
                }
                i++;
            }
            for(String v : name){
                vegetarianRecipes.add(recipeService.read(v));
            }
            String answer = this.getRecipeStrings(vegetarianRecipes);
            report += answer;
            return answer;
        }

        //8 - List recipes containing ingredient
        public String searchRecipes(String input) throws Exception {
            List<Recipe> ingredientRecipes = new ArrayList<Recipe>();
            ingredientRecipes =  recipeService.read();
            int i = 0;
            for(Recipe recipe : recipeService.read()){
                int count = 0;
                List<Ingredient> ingredientList = ingredientRecipes.get(i).getIngredientList();
                for(Ingredient ingredient : ingredientList){
                    if(ingredient.getName().equals(input)){
                        count++;
                    }
                }
                if(count>0){
                    ingredientRecipes.add(recipeService.read(recipe.getName()));
                }
                i++;
            }
            if(ingredientRecipes==null){
                System.out.println("0 recipes");
            }
            String answer = this.getRecipeStrings(ingredientRecipes);
            report += answer;
            return answer;
        }

        //9 - List recipes within range of calories
        public String caloriesRecipes(Integer from , Integer to) throws Exception {
            List<Recipe> recipeList = new ArrayList<Recipe>();
            List<Recipe> recipeNames = new ArrayList<Recipe>();
            recipeList =  recipeService.read();
            int i = 0;
            for(Recipe recipe : recipeService.read()){
                int count = 0;
                List<Ingredient> ingredientList = recipeList.get(i).getIngredientList();
                for(Ingredient ingredient : ingredientList){
                    count = count + ingredient.getCalories();
                }
                if(from<=count&&count<=to){
                    recipeNames.add(recipeService.read(recipe.getName()));
                }
                i++;
            }
            String answer = this.getRecipeStrings(recipeNames);
            report += answer;
            return answer;
        }

        //10 Output to new file
        public void exportReport() throws IOException {
            this.createFile("reports.txt",report);
        }

    public String getString(Ingredient ingredient) {
        String s = "";
        s = ingredient.getName() + ",";
        s = s + String.valueOf(ingredient.getCost()) + ",";
        s = s + String.valueOf(ingredient.getCalories()) + ",";
        s = s + String.valueOf(ingredient.getVegetarian());
        return s;
    }

    public String getString(Recipe recipe) {
        String s = "";
        s = recipe.getName() + ",";
        s = s + recipe.getTime().toString();
        int x = recipe.getIngredientList().size();
        int z = 0;
        for (Ingredient ingredient : recipe.getIngredientList()) {
            if (x == z) {
                s = s + ingredient.getName();
            } else {
                s = s + ingredient.getName() + ",";
            }
            z++;
        }
        return s;
    }

    public String getStrings(List<Ingredient> ingredientList) {
        String s = "";
        for (Ingredient ingredient : ingredientList) {
            s = s + getString(ingredient) + "\n";
        }
        return s;
    }

    public String getRecipeStrings(List<Recipe> recipeList) {
        String s = "";
        for (Recipe recipe : recipeList) {
            s = s + getString(recipe) + "\n";
        }
        return s;
    }

    public boolean readFile(String filename) throws Exception{
        List<String> lines = new ArrayList();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_PATH+filename)));

        while(true) {
            String s = in.readLine();
            if (s == null) {
                in.close();
                break;
            }
            List<String> lineContent = Arrays.asList(s.split(","));

            if(filename.equals("ingredients.txt")){
                Ingredient ingredient = new Ingredient(lineContent.get(0), Double.parseDouble(lineContent.get(1)), Integer.parseInt(lineContent.get(2)), lineContent.get(3).charAt(0));
                ingredientService.create(ingredient);
            }else{
                boolean recipeExists=false;
                for(Recipe recipe : recipeService.read()){
                    recipeExists = recipe.getName().equals(lineContent.get(0)) && recipe.getTime().equals(lineContent.get(1));
                }
                if(!recipeExists) {
                    List<Ingredient> ingredientList = new ArrayList<Ingredient>();
                    for(String s2 : lineContent.subList(2, lineContent.size())){
                        for(Ingredient ingredient : ingredientService.read()){
                            if (s2.equals(ingredient.getName())){
                                ingredientList.add(ingredient);
                            }
                        }
                    }
                    Recipe recipe = new Recipe(lineContent.get(0), Integer.parseInt(lineContent.get(1)), ingredientList);
                    recipeService.create(recipe);
                }else{
                    System.out.println("Error: Recipe '"+lineContent.get(0)+"' already exists!");
                }
            }
            lines.add(s);
        }
        return true;
    }

    public boolean createFile(String filename, String text) throws IOException {
        FileWriter out = new FileWriter(OUTPUT_PATH+filename);

        for(int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (c == 'e') {
                c = 'U';
            }

            out.write(c);
        }

        out.close();
        return true;
    }
}



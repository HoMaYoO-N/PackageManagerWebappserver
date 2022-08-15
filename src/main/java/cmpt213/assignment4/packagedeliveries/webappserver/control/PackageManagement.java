package cmpt213.assignment4.packagedeliveries.webappserver.control;

import cmpt213.assignment4.packagedeliveries.webappserver.gson.extras.RuntimeTypeAdapterFactory;
import cmpt213.assignment4.packagedeliveries.webappserver.model.Book;
import cmpt213.assignment4.packagedeliveries.webappserver.model.Electronic;
import cmpt213.assignment4.packagedeliveries.webappserver.model.Package;
import cmpt213.assignment4.packagedeliveries.webappserver.model.Perishable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class PackageManagement {

    public static ArrayList<Package> packageList;
    private static File theFile = new File("list.json");

    //start of JSON handling
    /**
     * Reads the content from json file and loads the packageList accordingly.
     */
    public static ArrayList<Package> ReadFromJSON()
    {
        try
        {
            FileReader fileReader = new FileReader(theFile);
            Type type = new TypeToken<ArrayList<Package>>(){}.getType();
            RuntimeTypeAdapterFactory<Package> packageAdapterFactory = RuntimeTypeAdapterFactory
                    .of(Package.class, "type")
                    .registerSubtype(Book.class,"Book")
                    .registerSubtype(Perishable.class, "Perishable")
                    .registerSubtype(Electronic.class,"Electronic");


            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                            new TypeAdapter<LocalDateTime>() {
                                @Override
                                public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
                                    jsonWriter.value(localDateTime.toString());
                                }
                                @Override
                                public LocalDateTime read(JsonReader jsonReader) throws IOException {
                                    return LocalDateTime.parse(jsonReader.nextString());
                                }
                            }).registerTypeAdapterFactory(packageAdapterFactory)
                    .create();
            //fileReader.close();
            //an issue is that the type variable from json does not get loaded to packagelist so we have to manually handle it.
            packageList = gson.fromJson(fileReader, type);
            for (int i = 0; i < packageList.size();i++)
            {
                String className = String.valueOf(packageList.get(i).getClass());
                if(className.contains("Book"))
                    packageList.get(i).setType("Book");
                if(className.contains("Electronic"))
                    packageList.get(i).setType("Electronic");
                if(className.contains("Perishable"))
                    packageList.get(i).setType("Perishable");
            }
            return packageList;

        } catch (FileNotFoundException e) {
            System.err.println("Error in creating a FileReader object.");
        } catch (IOException e) {
            System.err.println("Error in closing the file.");
        }
        return null;
    }

    public static void WriteToJSON()
    {
        try {
            FileWriter fileWriter = new FileWriter(theFile);
            RuntimeTypeAdapterFactory<Package> packageAdapterFactory = RuntimeTypeAdapterFactory
                    .of(Package.class, "type")
                    .registerSubtype(Book.class, "Book")
                    .registerSubtype(Perishable.class, "Perishable")
                    .registerSubtype(Electronic.class,"Electronic");
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                            new TypeAdapter<LocalDateTime>() {
                                @Override
                                public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
                                    jsonWriter.value(localDateTime.toString());
                                }
                                @Override
                                public LocalDateTime read(JsonReader jsonReader) throws IOException {
                                    return LocalDateTime.parse(jsonReader.nextString());
                                }
                            }).registerTypeAdapterFactory(packageAdapterFactory)
                    .create();
            gson.toJson(packageList,fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error in writing the file.");
        }
    }

    //end of JSON handling

    //start of options handling


    /**
     * Option4: a package is overdue if today's DateTime is more than package's getExpectedDeliveryDate.
     */
    public static ArrayList<Package> ListOverduePackages(ArrayList<Package> packageList)
    {
        ArrayList<Package> container = new ArrayList<>();
        for(int i = 0; i < packageList.size(); i++)
            if(LocalDateTime.now().compareTo(packageList.get(i).getExpectedDeliveryDate()) > 0 && packageList.get(i).getIsDelivered() == false)
                container.add(packageList.get(i));
        Collections.sort(container,Collections.reverseOrder());
        return container;
    }

    /**
     * Option5: a package is upcoming if today's DateTime is less or equal than package's getExpectedDeliveryDate.
     */
    public static ArrayList<Package> ListUpcomingPackages(ArrayList<Package> packageList)
    {
        ArrayList<Package> container = new ArrayList<>();
        for(int i = 0; i < packageList.size(); i++)
            if(LocalDateTime.now().compareTo(packageList.get(i).getExpectedDeliveryDate()) <= 0 && packageList.get(i).getIsDelivered() == false)
                container.add(packageList.get(i));
        Collections.sort(container,Collections.reverseOrder());
        return container;
    }


    //end of options handling.

    //start of main logic of the program

    /**
     * The function running the main logic of the program. Holds the arraylist of packages, initiates readin/writing to json ,and passes to Communicator class to do operations (1 to 6) on that list.
     */
    public static void ReadFromJSONorCreatePackageList() {
        if (theFile.exists()) {
            packageList = ReadFromJSON();
            Collections.sort(packageList);
        }
        else
            packageList = new ArrayList<Package>();
    }
}
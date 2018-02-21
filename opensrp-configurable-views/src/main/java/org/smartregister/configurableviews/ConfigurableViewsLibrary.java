package org.smartregister.configurableviews;


import org.smartregister.Context;
import org.smartregister.configurableviews.jsonspec.JsonSpecHelper;
import org.smartregister.configurableviews.repository.ConfigurableViewsRepository;
import org.smartregister.repository.Repository;

/**
 * Created by ndegwamartin on 16/02/2018.
 */

public class ConfigurableViewsLibrary {


    private static ConfigurableViewsLibrary instance;
    private static Context context;
    private String password;

    private Repository repository;
    private ConfigurableViewsRepository configurableViewsRepository;
    private static JsonSpecHelper jsonSpecHelper;

    public static void init(Context context_, Repository repository) {
        if (instance == null) {
            context = context_;
            instance = new ConfigurableViewsLibrary(context, repository);
        }
    }

    public static ConfigurableViewsLibrary getInstance() {
        if (instance == null) {
            throw new IllegalStateException(" Instance does not exist!!! Call " + ConfigurableViewsLibrary.class.getName() + ".init method in the onCreate method of your Application class ");
        }
        return instance;
    }

    private ConfigurableViewsLibrary(Context context, Repository repository) {
        ConfigurableViewsLibrary.context = context;
        this.repository = repository;
        //Initialize JsonSpec Helper
        ConfigurableViewsLibrary.jsonSpecHelper = new JsonSpecHelper(context.applicationContext());
    }


    public ConfigurableViewsRepository getConfigurableViewsRepository() {
        if (configurableViewsRepository == null) {
            configurableViewsRepository = new ConfigurableViewsRepository(getRepository());
        }
        return configurableViewsRepository;
    }

    public Repository getRepository() {
        return repository;
    }

    public static JsonSpecHelper getJsonSpecHelper() {
        return getInstance().jsonSpecHelper;
    }

    public static Context getContext() {
        return context;
    }

    public String getPassword() {
        if (password == null) {
            String username = getContext().userService().getAllSharedPreferences().fetchRegisteredANM();
            password = getContext().userService().getGroupId(username);
        }
        return password;
    }
}
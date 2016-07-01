package org.erickzarat.android.facebookrecipes.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zarathos on 30/06/16
 */
public class RecipeClient {
    private Retrofit retrofit;
    private final static String BASE_URL = "http://food2fork.com/api/";

    public RecipeClient() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public RecipeService getRecipeService(){
        return this.retrofit.create(RecipeService.class);
    }
}
//package com.greenfox.gitinder.Model;
//
//
//import dagger.Module;
//import dagger.Provides;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//@Module
//public class ApiBuilder {
//
//
//    @Provides
//   public DbClient appBuilder() {
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl("https://api.themoviedb.org/3/")
//                .addConverterFactory(GsonConverterFactory.create());
//
//        Retrofit retrofit = builder.build();
//        DbClient dbClient = retrofit.create(DbClient.class);
//
//        return dbClient;
//    }
//
//
//}

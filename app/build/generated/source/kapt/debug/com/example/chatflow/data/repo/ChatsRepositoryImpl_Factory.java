package com.example.chatflow.data.repo;

import com.example.chatflow.data.local.AppDatabase;
import com.example.chatflow.data.local.ChatDao;
import com.example.chatflow.data.remote.ApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ChatsRepositoryImpl_Factory implements Factory<ChatsRepositoryImpl> {
  private final Provider<ApiService> apiProvider;

  private final Provider<ChatDao> daoProvider;

  private final Provider<AppDatabase> dbProvider;

  public ChatsRepositoryImpl_Factory(Provider<ApiService> apiProvider,
      Provider<ChatDao> daoProvider, Provider<AppDatabase> dbProvider) {
    this.apiProvider = apiProvider;
    this.daoProvider = daoProvider;
    this.dbProvider = dbProvider;
  }

  @Override
  public ChatsRepositoryImpl get() {
    return newInstance(apiProvider.get(), daoProvider.get(), dbProvider.get());
  }

  public static ChatsRepositoryImpl_Factory create(Provider<ApiService> apiProvider,
      Provider<ChatDao> daoProvider, Provider<AppDatabase> dbProvider) {
    return new ChatsRepositoryImpl_Factory(apiProvider, daoProvider, dbProvider);
  }

  public static ChatsRepositoryImpl newInstance(ApiService api, ChatDao dao, AppDatabase db) {
    return new ChatsRepositoryImpl(api, dao, db);
  }
}

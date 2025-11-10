package com.example.chatflow.di;

import com.example.chatflow.domain.repository.ChatRepository;
import com.example.chatflow.domain.usecase.ObserveCachedChatsUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class UseCaseModule_ProvideObserveCachedFactory implements Factory<ObserveCachedChatsUseCase> {
  private final Provider<ChatRepository> repoProvider;

  public UseCaseModule_ProvideObserveCachedFactory(Provider<ChatRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public ObserveCachedChatsUseCase get() {
    return provideObserveCached(repoProvider.get());
  }

  public static UseCaseModule_ProvideObserveCachedFactory create(
      Provider<ChatRepository> repoProvider) {
    return new UseCaseModule_ProvideObserveCachedFactory(repoProvider);
  }

  public static ObserveCachedChatsUseCase provideObserveCached(ChatRepository repo) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideObserveCached(repo));
  }
}

package com.example.chatflow.di;

import com.example.chatflow.domain.repository.ChatRepository;
import com.example.chatflow.domain.usecase.ObserveApiTimeMsUseCase;
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
public final class UseCaseModule_ProvideObserveApiTimeMsFactory implements Factory<ObserveApiTimeMsUseCase> {
  private final Provider<ChatRepository> repoProvider;

  public UseCaseModule_ProvideObserveApiTimeMsFactory(Provider<ChatRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public ObserveApiTimeMsUseCase get() {
    return provideObserveApiTimeMs(repoProvider.get());
  }

  public static UseCaseModule_ProvideObserveApiTimeMsFactory create(
      Provider<ChatRepository> repoProvider) {
    return new UseCaseModule_ProvideObserveApiTimeMsFactory(repoProvider);
  }

  public static ObserveApiTimeMsUseCase provideObserveApiTimeMs(ChatRepository repo) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideObserveApiTimeMs(repo));
  }
}

package com.example.chatflow.di;

import com.example.chatflow.domain.repository.ChatRepository;
import com.example.chatflow.domain.usecase.SyncChatsOnceUseCase;
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
public final class UseCaseModule_ProvideSyncOnceFactory implements Factory<SyncChatsOnceUseCase> {
  private final Provider<ChatRepository> repoProvider;

  public UseCaseModule_ProvideSyncOnceFactory(Provider<ChatRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public SyncChatsOnceUseCase get() {
    return provideSyncOnce(repoProvider.get());
  }

  public static UseCaseModule_ProvideSyncOnceFactory create(Provider<ChatRepository> repoProvider) {
    return new UseCaseModule_ProvideSyncOnceFactory(repoProvider);
  }

  public static SyncChatsOnceUseCase provideSyncOnce(ChatRepository repo) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideSyncOnce(repo));
  }
}

package com.example.chatflow.di;

import com.example.chatflow.domain.repository.ChatRepository;
import com.example.chatflow.domain.usecase.GetPagedChatsUseCase;
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
public final class UseCaseModule_ProvideGetPagedChatsFactory implements Factory<GetPagedChatsUseCase> {
  private final Provider<ChatRepository> repoProvider;

  public UseCaseModule_ProvideGetPagedChatsFactory(Provider<ChatRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public GetPagedChatsUseCase get() {
    return provideGetPagedChats(repoProvider.get());
  }

  public static UseCaseModule_ProvideGetPagedChatsFactory create(
      Provider<ChatRepository> repoProvider) {
    return new UseCaseModule_ProvideGetPagedChatsFactory(repoProvider);
  }

  public static GetPagedChatsUseCase provideGetPagedChats(ChatRepository repo) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideGetPagedChats(repo));
  }
}
